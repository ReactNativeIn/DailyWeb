package com.daily.www.review;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("reviewVO")
@Data
public class ReviewVO {
	private int review_id;
	private String r_title;
	private String r_content;
	private String r_file_path;
	private Date r_enroll;
	private int score;
	private int payment_id;
	private String id;
	
}
