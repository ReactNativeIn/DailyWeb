package com.daily.www.member;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("memberVO")
@Data
public class MemberVO {
	private String id;
	private String password;
	private String name;
	private Date birthday;
	private String email;
	private String nickname;
	private int point;
	private Date joinDate;
}
