package com.mypage.project.board.controller;

import java.security.Principal;
import java.util.List;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mypage.project.board.Board;
import com.mypage.project.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/")
	public String mainPage(Model model) {
				
		List<Board> boardList = this.boardService.getboardList();
		
		
		model.addAttribute("boardList",boardList);
		
		return "mainPage";
		
	}
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/board/write")
	public String mainPage(Board board) {
		return "boardWrite";
	}
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/board/write")
	public String mainPage(Board board,Principal principal) {
		
		try {
			
			this.boardService.save(board, principal.getName());
			
		}catch(Exception e) {
			return "redirect:/board/write";
		}
		
		
		return "redirect:/";
	}
	
}
