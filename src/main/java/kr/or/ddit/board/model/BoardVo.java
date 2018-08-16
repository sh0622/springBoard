package kr.or.ddit.board.model;

import java.util.Date;

public class BoardVo {
	private int bd_no;
	private int id;
	private String bd_name;
	private int bd_ck;
	private Date bd_date;
	
	public BoardVo(){
		
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
	public String getBd_name() {
		return bd_name;
	}
	public void setBd_name(String bd_name) {
		this.bd_name = bd_name;
	}
	public int getBd_ck() {
		return bd_ck;
	}
	public void setBd_ck(int bd_ck) {
		this.bd_ck = bd_ck;
	}
	public Date getBd_date() {
		return bd_date;
	}
	public void setBd_date(Date bd_date) {
		this.bd_date = bd_date;
	}
	@Override
	public String toString() {
		return "BoardVo [bd_no=" + bd_no + ", id=" + id + ", bd_name="
				+ bd_name + ", bd_ck=" + bd_ck + ", bd_date=" + bd_date + "]";
	}
	
	
}
