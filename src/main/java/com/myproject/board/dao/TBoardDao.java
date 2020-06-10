package com.myproject.board.dao;

import java.util.List;

import com.myproject.board.vo.TmpBoardVO;

public interface TBoardDao{
	
	//게시글 작성
	public void write(TmpBoardVO tmpboardVO) throws Exception;
	
	//게시글 조회 좌측 목록
	public List<TmpBoardVO> list(TmpBoardVO tmpboardVO) throws Exception;
	
	//목록 선택하여 내용 조회
	public List<TmpBoardVO> detaillist(String bno) throws Exception;
	
	//게시글 삭제
	public List<TmpBoardVO> delete(String bno) throws Exception;
}