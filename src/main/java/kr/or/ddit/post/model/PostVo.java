package kr.or.ddit.post.model;

import java.util.Date;

public class  PostVo{
	private int pt_no;
	private int bd_no;
	private int id;
	private int pt_seq;
	private int pt_group;
	private String pt_content;
	private String pt_title;
	private Date pt_date;
	private int pt_remove;
	private String std_id;
	private int rn;
	

	public PostVo(){
		
	}
	
	public int getRn() {
		return rn;
	}
	
	public void setRn(int rn) {
		this.rn = rn;
	}
	
	public String getStd_id() {
		return std_id;
	}
	
	public void setStd_id(String std_id) {
		this.std_id = std_id;
	}
	
	public int getPt_no() {
		return pt_no;
	}
	public void setPt_no(int pt_no) {
		this.pt_no = pt_no;
	}
	public int getBd_no() {
		return bd_no;
	}
	public void setBd_no(int bd_no) {
		this.bd_no = bd_no;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPt_seq() {
		return pt_seq;
	}
	public void setPt_seq(int pt_seq) {
		this.pt_seq = pt_seq;
	}
	public int getPt_group() {
		return pt_group;
	}
	public void setPt_group(int pt_group) {
		this.pt_group = pt_group;
	}
	public String getPt_content() {
		return pt_content;
	}
	public void setPt_content(String pt_content) {
		this.pt_content = pt_content;
	}
	public String getPt_title() {
		return pt_title;
	}
	public void setPt_title(String pt_title) {
		this.pt_title = pt_title;
	}
	public Date getPt_date() {
		return pt_date;
	}
	public void setPt_date(Date pt_date) {
		this.pt_date = pt_date;
	}
	public int getPt_remove() {
		return pt_remove;
	}
	public void setPt_remove(int pt_remove) {
		this.pt_remove = pt_remove;
	}

	@Override
	public String toString() {
		return "PostVo [pt_no=" + pt_no + ", bd_no=" + bd_no + ", id=" + id
				+ ", pt_seq=" + pt_seq + ", pt_group=" + pt_group
				+ ", pt_content=" + pt_content + ", pt_title=" + pt_title
				+ ", pt_date=" + pt_date + ", pt_remove=" + pt_remove + "]";
	}
	
	
}
