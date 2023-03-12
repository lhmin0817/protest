package com.mypage.project.member.service;

import com.mypage.project.constant.Role;
import com.mypage.project.member.dto.MemberFormDto;
import com.mypage.project.member.entity.Member;
import com.mypage.project.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(MemberFormDto memberFormDto) {

        Member member = new Member();

        member.setName(memberFormDto.getName());
        member.setPassword(this.passwordEncoder.encode(memberFormDto.getPassword()));
        member.setEmail(memberFormDto.getEmail());
        member.setAddr(memberFormDto.getAddr());

        this.memberRepository.save(member);

    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        System.out.println(email);

        Optional<Member> _Member=this.memberRepository.findByEmail(email);

        if(_Member.isEmpty()) {

            System.out.println("비어있음");
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");

        }

        Member member = _Member.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if("admin".equals(email)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        }

        return new User(member.getEmail(),member.getPassword(),authorities);
    }

}