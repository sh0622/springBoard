package kr.or.ddit.recomment.model;

import java.util.Date;

public class RecommentVo {
	private int re_no;
	private int pt_no;
	private int id;
	private String re_content; 
	private int re_remove;
	private Date re_date;
	private String std_id;
	

	public void setStd_id(String std_id) {
		this.std_id = std_id;
	}
	
	public String getStd_id() {
		return std_id;
	}

	public RecommentVo(){
		
	}
	
	public int getRe_no() {
		return re_no;
	}
	public void setRe_no(int re_no) {
		this.re_no = re_no;
	}
	public int getPt_no() {
		return pt_no;
	}
	public void setPt_no(int pt_no) {
		this.pt_no = pt_no;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRe_contnet() {
		return re_content;
	}
	public void setRe_contnet(String re_content) {
		this.re_content = re_content;
	}
	public int getRe_remove() {
		return re_remove;
	}
	public void setRe_remove(int re_remove) {
		this.re_remove = re_remove;
	}
	public Date getRe_date() {
		return re_date;
	}
	public void setRe_date(Date re_date) {
		this.re_date = re_date;
	}
	@Override
	public String toString() {
		return "RecommentVo [re_no=" + re_no + ", pt_no=" + pt_no + ", id="
				+ id + ", re_contnet=" + re_content + ", re_remove="
				+ re_remove + ", re_date=" + re_date + "]";
	}
	
	
}
