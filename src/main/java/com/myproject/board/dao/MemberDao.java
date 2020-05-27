package com.myproject.board.dao;

import java.util.List;

import com.myproject.board.vo.MemberVO;

public interface MemberDao{
	//회원가입
	public void save(MemberVO memberVO) throws Exception;
	
	//회원정보 수정
	public int update(MemberVO memberVO) throws Exception;
	
	//회원탈퇴
	public int delete(String member_no) throws Exception;
	
	//로그인
	public List<MemberVO> login(MemberVO membervo) throws Exception;
}