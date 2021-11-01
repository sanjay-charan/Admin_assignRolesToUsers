package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) throws IOException {
		String url = "https://api.cloudinary.com/v1_1/dc2zqvf2k/auto/upload";
		System.out.println("INSIDE FILE");
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

	    LinkedMultiValueMap<String, String> HeaderMap = new LinkedMultiValueMap<>();
	    HeaderMap.add("Content-disposition", "form-data; name=file; filename=" + file.getOriginalFilename());
	    HeaderMap.add("Content-type", "multipart/form-data");
	    HttpEntity<byte[]> fileInBytes = new HttpEntity<byte[]>(file.getBytes(), HeaderMap);

	    LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
	    body.add("file", fileInBytes);
	    body.add("upload_preset", "km3nmsxv");

	    HttpEntity<LinkedMultiValueMap<String, Object>> reqEntity = new HttpEntity<>(body, headers);
	    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, reqEntity, String.class);
		

		

		return response;

	}
	

}
