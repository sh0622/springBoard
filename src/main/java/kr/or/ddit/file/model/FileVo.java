package kr.or.ddit.file.model;

import java.util.Date;


public class FileVo {
	private int file_no;
	private int pt_no;
	private String upload_name;
	private String file_route;
	private String file_name;
	private Date file_date;
	
	public FileVo(){}
	
	public int getFile_no() {
		return file_no;
	}
	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}
	public int getPt_no() {
		return pt_no;
	}
	public void setPt_no(int pt_no) {
		this.pt_no = pt_no;
	}
	public String getUpload_name() {
		return upload_name;
	}
	public void setUpload_name(String upload_name) {
		this.upload_name = upload_name;
	}
	public String getFile_route() {
		return file_route;
	}
	public void setFile_route(String file_route) {
		this.file_route = file_route;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public Date getFile_date() {
		return file_date;
	}
	public void setFile_date(Date file_date) {
		this.file_date = file_date;
	}

	@Override
	public String toString() {
		return "FileVo [file_no=" + file_no + ", pt_no=" + pt_no
				+ ", upload_name=" + upload_name + ", file_route=" + file_route
				+ ", file_name=" + file_name + ", file_date=" + file_date + "]";
	}
	
	
}
