package com.myproject.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myproject.board.dao.TBoardDao;
import com.myproject.board.vo.TmpBoardVO;

@Service
public class TBoardServiceImple implements TBoardService{
	
	@Inject
	private TBoardDao dao;
	
	//게시글 작성
	@Override
	public void write (TmpBoardVO tmpboardVO) throws Exception{
		dao.write(tmpboardVO);
	}
	
	//게시글 조회
	public List<TmpBoardVO> list(TmpBoardVO tmpboardVO) throws Exception{
		return dao.list(tmpboardVO);
	}
	
	//게시글 조회
	
	public List<TmpBoardVO> detaillist(String bno) throws Exception{ 
		return	dao.detaillist(bno); 
	}

	//글작성 진입시 첫글
	public List<TmpBoardVO> main(TmpBoardVO tmpboardVO) throws Exception{
		return dao.main(tmpboardVO);
	}

	//게시글 삭제
	public List<TmpBoardVO> delete(String bno) throws Exception{
		return dao.delete(bno);
	}
	 
}