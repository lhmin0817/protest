package com.mypage.project.member.controller;

import com.mypage.project.member.dto.MemberFormDto;
import com.mypage.project.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/login")
	public String login() {
		return "memberLogin";
	}
	
	@GetMapping("/inroll")
	public String inroll( MemberFormDto memberFormDto ) {
		return "memberInroll";
	}
	
	@PostMapping("/inroll")
	public String inroll(Model model, @Valid MemberFormDto memberFormDto,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "memberInroll";
		}
		
		if(!memberFormDto.getPassword().equals(memberFormDto.getPassword())) {
			bindingResult.rejectValue("mPasswordConf", "notSamePass","비밀번호가 일치하지 않습니다.");
			return "memberInroll";
		}
		
		try {
			
			this.memberService.save(memberFormDto);
			
		}catch(Exception e) {
			model.addAttribute("save_error", "아이디 혹은 이메일 중복.");
			return "memberInroll";
		}
		
		return "redirect:/";
	}
	
	
}
