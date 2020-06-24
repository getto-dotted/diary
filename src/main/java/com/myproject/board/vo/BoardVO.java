package com.myproject.board.vo;

import java.sql.Date;

public class BoardVO{
	
	private int bno;
	private String title;
	private String content;
	private String writer;
	private String filepath;
	private Date write_date;
	private Date regdate;
	private String font;
	private String fontsize;
	private String fontcolor;
	
	public BoardVO() {}
	
	

	public BoardVO(int bno, String title, String content, String writer, String filepath, Date write_date, Date regdate,
			String font, String fontsize, String fontcolor) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
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



	public String getWriter() {
		return writer;
	}



	public void setWriter(String writer) {
		this.writer = writer;
	}



	public String getFilepath() {
		return filepath;
	}



	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}



	public Date getWrite_date() {
		return write_date;
	}



	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}



	public Date getRegdate() {
		return regdate;
	}



	public void setRegdate(Date regdate) {
		this.regdate = regdate;
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



	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", filepath=" + filepath + ", regdate=" + regdate +", font="+font+", fontsize"+fontsize+", fontcolor="+fontcolor+ "]";
	}
}