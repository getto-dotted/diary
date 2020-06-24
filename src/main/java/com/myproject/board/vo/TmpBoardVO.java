package com.myproject.board.vo;

import java.sql.Date;

public class TmpBoardVO{
	
	private int bno;
	private String title;
	private String content;	
	private String filepath;
	private Date write_date;
	private Date regdate;
	private String font;
	private String fontsize;
	private String fontcolor;
	
	public TmpBoardVO() {	}
	
	public TmpBoardVO(int bno, String title, String content, String filepath, Date write_date, Date regdate,
			String font, String fontsize, String fontcolor) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.filepath = filepath;
		this.write_date = write_date;
		this.regdate = regdate;
		this.font = font;
		this.fontsize = fontsize;
		this.fontcolor = fontcolor;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}	

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	

	public Date getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public String getFontsize() {
		return fontsize;
	}

	public void setFontsize(String fontsize) {
		this.fontsize = fontsize;
	}

	public String getFontcolor() {
		return fontcolor;
	}

	public void setFontcolor(String fontcolor) {
		this.fontcolor = fontcolor;
	}
	
	
}