package com.mypage.project.board;

import java.time.LocalDateTime;

import com.mypage.project.member.entity.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Board {
	
	@Id
	@Column(name="board_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String subject;
	
	private String content;
	
	private LocalDateTime createDate;
	
	private LocalDateTime modifyDate;
	
	@ManyToOne
	private Member author;
	
}
