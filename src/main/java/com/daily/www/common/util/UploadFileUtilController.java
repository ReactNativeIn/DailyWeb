package com.daily.www.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.daily.www.file.vo.FileVO;

@Controller
@RequestMapping(value = "/util/upload")
public class UploadFileUtilController {
	
	@Resource(name ="uploadPath")
	String uploadPath;
	
	// 파일 업로드(DB 저장 전)
	// @ResponseBody : jsp로 넘어가는 것이 아니라 데이터 자체를 되돌려 주는 것이다.
	// ResponseEntity<String> : 메시지와 에러코드를 같이 돌려준다
	@ResponseBody
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST)
	public ResponseEntity<List<FileVO>> uploadAjax(MultipartFile[] uploadFile) throws Exception {
		System.out.println("util");
		
		for(int i = 0; i < uploadFile.length; i++) {
			System.out.println(uploadFile[i]);
		}
		
		String originalName;
		
		List<FileVO> list = new ArrayList<FileVO>();
		
		for(MultipartFile multipartFile : uploadFile) {
			FileVO fileVO = new FileVO();
			
			originalName = multipartFile.getOriginalFilename();
			
			fileVO.setFile_o_name(originalName); // 오리지날 이름
			
			// UUID 발급
			UUID uuid = UUID.randomUUID();
			
			// uuid를 추가한 파일이름 만들기
			String savedName = uuid.toString() + "_" + originalName;
			fileVO.setUuid(savedName); // uuid_오리지날 이름
			
			// 업로드할 디렉토리 생성
			String savedPath = calculatePath(uploadPath);
			File target = new File(uploadPath + savedPath, savedName);
			
			String extensionName = originalName.substring(originalName.lastIndexOf(".") + 1);
			String	uploadedFileName	= null;

			// 해당 경로에 파일을 저장
			multipartFile.transferTo(target);
			
			
			// 썸네일 파일을 만든다.
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);

			fileVO.setFile_path(uploadedFileName.substring(0,12));
			fileVO.setFile_s_name(uploadedFileName.substring(12));
			
			list.add(fileVO);
		}
		
		return new ResponseEntity<List<FileVO>>(list, HttpStatus.OK);
	}
	
	// 이미지 표시 기능을 하게하는 메서드
	// 이 메서드가 없으면 view에 엑박으로 나타난다.
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody	// view가 아닌 data를 리턴한다.
	@RequestMapping(value = "/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		
		// 서버의 파일을 다운로드하기 위한 스트림
		InputStream 			in		= null;	// java.io
		ResponseEntity<byte[]> 	entity	= null;
		
		try {
			// 넘겨받은 파일이름에서 확장자를 검사한다.
			// 매개변수가 1개인 경우 : substring(10) 	=> 
			//								매개변수 위치부터 뒤의 모든 것을 말한다.
			// 매개변수가 2개인 경우 : substring(1, 5) 	=> 
			//								첫번째 매개변수 위치부터 두번째 매개변수 바로 전까지의 데이터를 말한다.
			String	extensionName	= fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType			= MediaUtils.getMediaType(extensionName);
			
			// 헤더 구성 객체
			HttpHeaders headers		= new HttpHeaders();
			
			// InputStream 생성
			in = new FileInputStream(uploadPath + fileName);
			

			headers.setContentType(mType);
			
			
			// 바이트 배열, 헤더
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity	= new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			if(in != null)
				in.close();	// 사용한 스트림 닫기 
		}
		
		
		return entity;
		
	} 
	
	
	// 썸네일 생성하기
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {
	
		// 이미지을 읽어서 버퍼에 저장
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		
		// 100 필셀 단위의 썸네일을 생성
		// Scalr.resize : 원본 이미지보다 축소하기 위해 사용
		// 높이를 100픽셀로 맞추면 폭은 자동으로 맞춰줌
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
	
		// 썸네일 파일의 이름
		// s_이 붙으면 썸네일 파일, 아니면 원본 파일이다.
		String 	thumbnailName 	= uploadPath + path + File.separator + "s_" + fileName;
	
		File newFile = new File(thumbnailName);
		String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		// 썸네일 파일 생성
		ImageIO.write(destImg, extensionName.toUpperCase(), newFile);
		
		// 썸네일 파일 이름을 리턴한다.
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	// 년/월/일 폴더 생성
	private static String calculatePath(String uploadPath) {
		
		// 년월일 정보를 얻기 위해서 캘린더의 인스턴스를 가져온다.
		Calendar cal = Calendar.getInstance();
		
		// 년도를 구해서 변수에 저장한다.
		String	yearPath	= File.separator + cal.get(Calendar.YEAR);
		
		// 월을 구해서 변수에 저장한다.
		String	monthPath	= yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		
		// 일을 구해서 변수에 저장한다.
		String	datePath	= monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		// 년월일에 맞게 디렉토리를 생성한다.
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		// 일경로를 리턴한다.
		return datePath;
	}
	
	// 디렉토리 생성
	// 가변 사이즈의 매개 변수 마침표 3개(...)
	// String ... 에 uploadPath
	// paths[0]에 yearPath
	// paths[1]에 monthPath
	// paths[2]에 datePath 가 들어오게 된다.
	private static void makeDir(String uploadPath, String ... paths) {
		
		// 디렉토리가 이미 존재한다면 만들지 않고 돌아간다.
		if(new File(paths[paths.length-1]).exists()) {
			return;
		}
		// paths에 있는 모든 정보(년경로, 월경로, 일경로)만큼 반복을 한다.
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
			if(!dirPath.exists()) {	// 디렉토리가 존재하지 않는 경우에만
				dirPath.mkdir();	// 디렉토리를 생성한다.
			}
		}
	}
	
	// 파일 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName) {
		System.out.println(fileName);
		// fileName에는 이미지 파일의 경우 원본 파일 이름이 넘어온다.
		
		// File.separatorChar : 유닉스 /    윈도우즈 \
		String front = fileName.substring(0, 12);
		String end = fileName.substring(12);
		// 원본 삭제
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		
		// 썸네일 삭제
		new File(uploadPath + (front + "s_" + end).replace('/', File.separatorChar)).delete();
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
}
