package com.myproject.board.service;

import java.util.List;

import com.myproject.board.vo.TmpBoardVO;

public interface TBoardService{
	
	public void write (TmpBoardVO tmpboardVO) throws Exception;
	
	public List<TmpBoardVO> list(TmpBoardVO tmpboardVO) throws Exception;
	
	public List<TmpBoardVO> detaillist(String bno) throws Exception;
	
	public List<TmpBoardVO> delete(String bno) throws Exception;
}