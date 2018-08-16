package kr.or.ddit.file.view;

public class FileUtil {
	// 파일 업로드 경로
	public final static String fileUploadPath = "D:\\A_TeachingMaterial\\7.JspSpring\\fileUpload";

	/**
	 * Method : getFileName 최초작성일 : 2018. 7. 22. 작성자 : PC12 변경이력 :
	 * 
	 * @param contentDispostion
	 * @return Method 설명 : part의 Content-Disposition header로부터 업로드 파일명을 리턴한다. ex
	 *         ) form-data; name="uploadFile"; filename="cony.png" return :
	 *         sally.png
	 */
	public static String getFileName(String contentDispostion) {
		String[] fileNames = contentDispostion.split("; ");
		String file = "";

		for (String fileStr : fileNames) {
			if (fileStr.startsWith("filename")) { // filename으로 시작하면 true

				String[] fileValue = fileStr.split("="); // filename="cony.png"
															// 를 '=' 단위로 잘라서 저장
				file = fileValue[1].replaceAll("\"", "");// [0] = filename , [1]
															// = "cony.png"
			}
		}

		return file;
	}
}
