package com.daily.www.file;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("fileVO")
@Data
public class FileVO {
	private String uuid;
	private String file_data;
	private String file_path;
	private int product_id;
}
