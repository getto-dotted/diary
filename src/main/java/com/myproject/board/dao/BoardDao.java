package com.myproject.board.dao;

import java.util.List;

import com.myproject.board.vo.BoardVO;

public interface BoardDao{
	
	//게시글 작성
	public void write(BoardVO boardVO) throws Exception;
	
	//게시글 조회
	public List<BoardVO> list(BoardVO boardVO) throws Exception;
	
	//목록 선택하여 내용 조회
	//public List<BoardVO> detaillist(String bno) throws Exception;
}