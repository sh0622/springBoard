package kr.or.ddit.post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.post.dao.PostDao;
import kr.or.ddit.post.dao.PostDaoInf;
import kr.or.ddit.post.model.PostVo;

@Service("postService")
public class PostService implements PostServiceInf {
	
	//PostDaoInf postDao = new PostDao();
	@Resource(name="postDao")
	private PostDaoInf postDao;
	
	/** 
	 * Method : getBoardList
	 * 최초작성일 : 2018. 7. 18. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 번호에 맞는 게시판 글 조회
	 */
	
	@Override
	public List<PostVo> getPostList(int bd_no) {
		return postDao.getPostList(bd_no);
	}


	/** 
	 * Method : getPostPageList
	 * 최초작성일 : 2018. 7. 18. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @param map
	 * @return 
	 * Method 설명 : 게시판 글 페이지네이션한 리스트 반환
	 */
	@Override
	public Map<String,Object> getPostPageList(Map<String, Integer> map) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<PostVo> postPageList = postDao.getPostPageList(map);
		resultMap.put("postPageList", postPageList);
		
		//int pt_remove = map.get("pt_remove"); 
		int bd_no = map.get("bd_no");
		
		int totCnt = postDao.getPostTotCnt(bd_no);
		resultMap.put("totCnt", totCnt);
		
		int page = map.get("page");
		int pageSize = map.get("pageSize");
		
		resultMap.put("pageNavi", makePageNavi(page, pageSize, totCnt, bd_no));
		
		return resultMap;
	}

	/** 
	 * Method : getPostTotCnt
	 * 최초작성일 : 2018. 7. 18. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 게시판 글 전체 건수 반환
	 */
	public String makePageNavi(int page, int pageSize, int totCnt, int bd_no) {
		int cnt = totCnt / pageSize;	//몫    2
		int mod = totCnt % pageSize;	//나머지 4
				
		if(mod>0)		
			cnt++;
		
		StringBuffer pageNaviStr = new StringBuffer();
		
		int start = page == 1 ? 1 : 1;
		int end = page == cnt ? page : cnt;
		
		int prevPage = page == 1 ? 1 : page - 1;
		int nextPage = page == cnt ? page : page + 1;
		
		pageNaviStr.append("<li> <a href=\"/post/list?bd_no="+bd_no+"&page="+ start +"&pageSize="+ pageSize +"\" aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span></a> </li>");
		pageNaviStr.append("<li> <a href=\"/post/list?bd_no="+bd_no+"&page="+ prevPage +"&pageSize="+ pageSize +"\" aria-label=\"Previous\"> <span aria-hidden=\"true\">&lt;</span></a> </li>");
		
		for (int i = 1; i <= cnt; i++) {
			String actvieClass = "";
			
			if(i == page)
				actvieClass = "class=\"active\"";
			
			pageNaviStr.append("<li " + actvieClass +"> <a href=\"/post/list?bd_no="+bd_no+"&page=" + i + "&pageSize="+ pageSize + "\">" + i + "</a></li>");
		}

		pageNaviStr.append("<li> <a href=\"/post/list?bd_no="+bd_no+"&page="+ nextPage +"&pageSize="+ pageSize +"\" aria-label=\"Next\"><span aria-hidden=\"true\">&gt;</span></a></li>");
		pageNaviStr.append("<li> <a href=\"/post/list?bd_no="+bd_no+"&page="+ end +"&pageSize="+ pageSize +"\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li>");
		
		return pageNaviStr.toString();
	}


	@Override
	public PostVo getPostDetailList(int pt_no) {
		return postDao.getPostDetailList(pt_no);
	}


	@Override
	public int insertPost(PostVo postVo) {
		return postDao.insertPost(postVo);
	}


	@Override
	public int removePost(PostVo postVo) {
		return postDao.removePost(postVo);
	}


	@Override
	public int postUpdate(PostVo postVo) {
		return postDao.postUpdate(postVo);
	}


	@Override
	public int postChild(PostVo postVo) {
		return postDao.postChild(postVo);
	}


	@Override
	public int getPostTotCnt(int bd_no) {
		return postDao.getPostTotCnt(bd_no);
	}




}
