package com.daily.www.file.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("fileVO")
@Data
public class FileVO {
	private String uuid;
	private String file_o_name;
	private String file_path;
	private String file_s_name;
	private int product_id;
}
