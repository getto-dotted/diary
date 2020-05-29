package com.myproject.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myproject.board.dao.MemberDao;
import com.myproject.board.vo.MemberVO;

@Service
public class MemberServiceImple implements MemberService{
	
	@Inject
	private MemberDao dao;
	
	//회원가입
	@Override
	public void save(MemberVO memberVO) throws Exception{
		dao.save(memberVO);
	}
	
	//회원정보 수정
	public int update(MemberVO memberVO) throws Exception{
		return dao.update(memberVO);
	}
	
	//회원정보 탈퇴
	public int delete(MemberVO memberVO) throws Exception{
		return dao.delete(memberVO);
	}
	
	//로그인
	public List<MemberVO> login(MemberVO memberVO) throws Exception{
		return dao.login(memberVO);
	}
	
	//회원조회
	public int isMember(MemberVO memberVO) throws Exception{
		return dao.isMember(memberVO);
	}
	
	//회원정보조회
	public List<MemberVO> memberinfo(MemberVO memberVO) throws Exception{ 
		return dao.memberinfo(memberVO);  
	}
}