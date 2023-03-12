package com.mypage.project.board.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mypage.project.board.Board;
import com.mypage.project.board.repository.BoardRepository;
import com.mypage.project.member.entity.Member;
import com.mypage.project.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	private final MemberRepository memberRepository;
	
	
	public List<Board> getboardList(){
		return this.boardRepository.findAll();
	}
	
	public void save(Board board , String email) {
		
		board.setCreateDate(LocalDateTime.now());
		
		Optional<Member> board_db = this.memberRepository.findByEmail(email);
		
		board.setAuthor(board_db.get());
		
		this.boardRepository.save(board);
		
	}
	
	
	
	
	
}
