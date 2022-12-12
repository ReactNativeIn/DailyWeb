package com.daily.www.board.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("boardVO")
@Data
public class BoardVO {
	private int board_id;
	private String b_state;
	private String b_title;
	private Date b_enroll;
	private String b_content;
	private String id;
}
