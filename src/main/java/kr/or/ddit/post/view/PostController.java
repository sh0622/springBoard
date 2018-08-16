package kr.or.ddit.post.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceInf;
import kr.or.ddit.file.model.FileVo;
import kr.or.ddit.file.service.FileTableServiceInf;
import kr.or.ddit.file.view.FileUtil;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.PostServiceInf;
import kr.or.ddit.recomment.model.RecommentVo;
import kr.or.ddit.recomment.service.RecommentServiceInf;
import kr.or.ddit.student.model.StudentVo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RequestMapping("/post")
@Controller("postController")
public class PostController {

	@Resource(name = "postService")
	private PostServiceInf postService;

	@Resource(name = "boardService")
	private BoardServiceInf boardService;

	@Resource(name = "recommentService")
	private RecommentServiceInf recommentService;

	@Resource(name = "fileTableService")
	private FileTableServiceInf fileTableService;

	// 게시물 리스트
	@RequestMapping("/list")
	public String postList(HttpSession session, BoardVo bodVo, Model model,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

		int bd_no = bodVo.getBd_no();

		session.setAttribute("bd_no", bd_no);

		BoardVo boardVo = boardService.getBdNoVo(bd_no);
		model.addAttribute("boardVo", boardVo);

		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("page", page);
		paramMap.put("pageSize", pageSize);
		paramMap.put("bd_no", bd_no);

		Map<String, Object> resultMap = postService.getPostPageList(paramMap);

		model.addAllAttributes(resultMap);

		return "post/postList";
	}

	// 게시물 상세보기
	@RequestMapping("/detail")
	public String postDetail(PostVo postVo, BoardVo boardVo, Model model,
			HttpSession session, HttpServletRequest request) {

		int pt_no = postVo.getPt_no();

		PostVo pv = postService.getPostDetailList(pt_no);
		model.addAttribute("postVo", pv);

		String bd_name = boardVo.getBd_name();
		model.addAttribute("bd_name", bd_name);

		StudentVo sutdentVo = (StudentVo)session.getAttribute("studentVo");
//		session.setAttribute("sutdentVo", sutdentVo);

		int std_id = sutdentVo.getId();

		int postId = pv.getId();

		int ck = 0;

		if (std_id == postId) {
			ck = 1;
		}

		request.setAttribute("memCheck", ck);

		List<RecommentVo> recommentList = recommentService.getRecommentList(pt_no);
		model.addAttribute("recommentList", recommentList);

		List<FileVo> fileList = fileTableService.getFileList(pt_no);
		model.addAttribute("fileList", fileList);

		return "post/postDetail";
	}

	// 게시물 등록
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String postAdd(BoardVo bodVo, Model model) {
		int bd_no = bodVo.getBd_no();
		model.addAttribute("bd_no", bd_no);

		String bd_name = bodVo.getBd_name();
		model.addAttribute("bd_name", bd_name);

		return "post/postAdd";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String postAddPost(BoardVo bodVo, Model model, HttpSession session,
			HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam("title") String title,
			@RequestParam("smarteditor") String smarteditor,
			@RequestParam("file_cnt") int file_cnt,
			@RequestParam("uploadFile") List<MultipartFile> files
			) throws IOException,
			ServletException {

		int bd_no = bodVo.getBd_no();

		StudentVo sv = (StudentVo) session.getAttribute("studentVo");

		PostVo postvo = new PostVo();
		postvo.setPt_content(smarteditor);
		postvo.setPt_title(title);
		postvo.setId(sv.getId());
		postvo.setBd_no(bd_no);

		int resultCnt = postService.insertPost(postvo);
		model.addAttribute("resultCnt", resultCnt);

		int posttotCnt = 0;

		// 디레토리가 없다면 생성
		File dir = new File(FileUtil.fileUploadPath);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		// 파일저장
		if (resultCnt > 0) {
			for(MultipartFile file : files){
				try{
					String origName;
					
					origName = new String(file.getOriginalFilename().getBytes("8859_1"), "UTF-8");// 한글꺠짐 방지
					
					// 파일명이 없다면
					if ("".equals(origName)) {
						continue;
					}
					
					// 파일 명 변경(uuid로 암호화)
//					String ext = origName.substring(origName.lastIndexOf('.'));
					
					// 확장자 String
					String upload_name = UUID.randomUUID().toString();
					
					// 설정한 path에 파일저장
					File uploadFile = new File(FileUtil.fileUploadPath + File.separator + origName);
					file.transferTo(uploadFile);
					
					posttotCnt = postService.getPostTotCnt(bd_no);
					
					FileVo fv = new FileVo();
					fv.setFile_name(origName);
					fv.setFile_route(FileUtil.fileUploadPath);
					fv.setUpload_name(upload_name);
					fv.setPt_no(posttotCnt);
					
					fileTableService.insertFile(fv);
					
				}catch(UnsupportedEncodingException e){
					e.printStackTrace();
				}catch(IllegalStateException e){
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		List<FileVo> fileList = fileTableService.getFileList(posttotCnt);
		model.addAttribute("fileList", fileList);
		// 시퀀스 갯수에 맞게 가져와야 함....ㅎㅎㅎ....
		posttotCnt = postService.getPostTotCnt(bd_no);

		model.addAttribute("posttotCnt", posttotCnt);
		model.addAttribute("postVo", postvo);

		return "redirect:/post/detail?pt_no=" + posttotCnt;
	}

	// 답글 등록
	@RequestMapping(value = "/child", method = RequestMethod.GET)
	public String postAdd(PostVo postvo, BoardVo boardVo, StudentVo studentVo,
			Model model) {
		int bd_no = boardVo.getBd_no();
		int pt_no = postvo.getPt_no();
		int pt_group = postvo.getPt_group();
		int id = studentVo.getId();

		model.addAttribute("bd_no", bd_no);
		model.addAttribute("pt_no", pt_no);
		model.addAttribute("pt_group", pt_group);
		model.addAttribute("id", id);

		return "post/postChild";
	}

	@RequestMapping(value = "/child", method = RequestMethod.POST)
	public String postChild(@RequestParam("id") int id,
			@RequestParam("pt_no") int pt_no,
			@RequestParam("bd_no") int bd_no,
			@RequestParam("pt_group") int pt_group,
			@RequestParam("smarteditor") String content,
			@RequestParam("title") String title,
			@RequestParam("file_cnt") int file_cnt,
			@RequestParam("uploadFile") List<MultipartFile> files,
			Model model,
			HttpServletRequest request) {

		PostVo pv = new PostVo();

		pv.setBd_no(bd_no);
		pv.setId(id);
		pv.setPt_seq(pt_no);
		pv.setPt_group(pt_group + 1);
		pv.setPt_title(title);
		pv.setPt_content(content);

		int resultCnt = postService.postChild(pv);

		int posttotCnt = 0;


		// 디레토리가 없다면 생성
		File dir = new File(FileUtil.fileUploadPath);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		// 파일저장
		if (resultCnt > 0) {
			for(MultipartFile file : files){
				System.out.println(file);
				try{
					String origName;
					
					origName = new String(file.getOriginalFilename().getBytes("8859_1"), "UTF-8");// 한글꺠짐 방지
					
					// 파일명이 없다면
					if ("".equals(origName)) {
						continue;
					}
					
					// 확장자 String
					String upload_name = UUID.randomUUID().toString();
					
					// 설정한 path에 파일저장
					File uploadFile = new File(FileUtil.fileUploadPath + File.separator + origName);
					file.transferTo(uploadFile);
					
					posttotCnt = postService.getPostTotCnt(bd_no);
					
					FileVo fv = new FileVo();
					fv.setFile_name(origName);
					fv.setFile_route(FileUtil.fileUploadPath);
					fv.setUpload_name(upload_name);
					fv.setPt_no(posttotCnt);
					
					fileTableService.insertFile(fv);
					
				}catch(UnsupportedEncodingException e){
					e.printStackTrace();
				}catch(IllegalStateException e){
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		List<FileVo> fileList = fileTableService.getFileList(posttotCnt);
		model.addAttribute("fileList", fileList);
		// 시퀀스 갯수에 맞게 가져와야 함....ㅎㅎㅎ....
		posttotCnt = postService.getPostTotCnt(bd_no);

		return "redirect:/post/detail?pt_no=" + posttotCnt;
	}

	// 글 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String postDelete(@RequestParam("pt_no") int pt_no, Model model) {

		model.addAttribute("pt_no", pt_no);

		return "post/detail";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String postDeletePost(@RequestParam("pt_no") int pt_no,
			Model model) {

		PostVo postVo = postService.getPostDetailList(pt_no);

		if (postVo.getPt_remove() == 0) {
			int remCnt = postService.removePost(postVo);
			model.addAttribute("remCnt", remCnt);
		}

		return "redirect:/post/list?bd_no=" + postVo.getBd_no();
	}

	// 글 수정
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String postUpdate(@RequestParam("pt_no") int pt_no, Model model) {

		PostVo postVo = postService.getPostDetailList(pt_no);
		model.addAttribute("postVo", postVo);
		
		List<FileVo> fileList = fileTableService.getFileList(pt_no);
		model.addAttribute("fileList", fileList);

		return "post/postUpdate";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String postUpdatePost(@RequestParam("title") String title,
			@RequestParam("smarteditor") String content,
			@RequestParam("bd_no") int bd_no,
			PostVo postVo,
			HttpServletRequest request,
			@RequestParam("file_cnt") int file_cnt,
			@RequestParam("pt_no") int pt_no,
			@RequestParam("uploadFile") List<MultipartFile> files,
			Model model) {

		PostVo postvo = new PostVo();
		postvo.setPt_title(title);
		postvo.setPt_content(content);
		postvo.setPt_no(postVo.getPt_no());

		int updateCnt = postService.postUpdate(postvo);
		
		// 디레토리가 없다면 생성
		File dir = new File(FileUtil.fileUploadPath);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		// 파일저장
		if (updateCnt > 0) {
			for (MultipartFile file : files) {
				try {
					String origName;

					origName = new String(file.getOriginalFilename().getBytes(
							"8859_1"), "UTF-8");// 한글꺠짐 방지

					// 파일명이 없다면
					if ("".equals(origName)) {
						continue;
					}

					// 확장자 String
					String upload_name = UUID.randomUUID().toString();

					// 설정한 path에 파일저장
					File uploadFile = new File(FileUtil.fileUploadPath + File.separator + origName);
					file.transferTo(uploadFile);

					//posttotCnt = postService.getPostTotCnt(bd_no);

					FileVo fv = new FileVo();
					fv.setFile_name(origName);
					fv.setFile_route(FileUtil.fileUploadPath);
					fv.setUpload_name(upload_name);
					fv.setPt_no(pt_no);

					fileTableService.insertFile(fv);

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		List<FileVo> fileList = fileTableService.getFileList(pt_no);
		model.addAttribute("fileList", fileList);
		
		return "redirect:/post/detail?pt_no=" + postVo.getPt_no();
	}
	
	@RequestMapping("/fileDownload")
	public void fileDownload(@RequestParam("file_name") String file_name,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ file_name + "\""); // file 정보 저장
		response.setContentType("application/octet-stream");

		String file = FileUtil.fileUploadPath + File.separator + file_name; // 파일
																			// 경로
																			// 가져옴
		// file 입출력을 위한 준비
		ServletOutputStream sos = response.getOutputStream();

		File f = new File(file);
		FileInputStream fis = new FileInputStream(f); // 파일 읽어오기
		
		byte[] b = new byte[512];
		int len = 0;

		while ((len = fis.read(b)) != -1) {
			sos.write(b, 0, len);
		}

		sos.close();
		fis.close();
	}

}
