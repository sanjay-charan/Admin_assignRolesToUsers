package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.FileModel;
import com.example.demo.model.FileSubDocument;
import com.example.demo.service.FileService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/file")
public class FileController {
	
	
	
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired 
	private FileService fileservice;
	
	@GetMapping("/getallfiles")
	public List<FileModel> getAllFiles(){
		LOGGER.info("IN GET ALL FILES");
		
		return fileservice.getAllFiles();
	}
	
	
	
	/**
	 * Method to get all files by defect_id
	 *
	 * @param defect_id as HashMap.
	 * @return FileModel with respective status and information.
	 */
	@GetMapping("/getfilebyid")
	public FileModel getFileById(@RequestBody HashMap<String, String> dataHashMap) {
		
		return fileservice.getFileById(dataHashMap.get("defect_id"));
	}
	
	
	
	@GetMapping("/getfilebyassetid")
	public FileModel getFileByAssetId(@RequestBody HashMap<String, String> dataHashMap) {
		return fileservice.getFileByAssetId(dataHashMap.get("defect_id"),dataHashMap.get("asset_id"));
	}
	
	@PostMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public FileModel uploadFile(@RequestPart("file") MultipartFile file, @RequestPart("defect_id") String  defect_id) throws IOException {
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
		
	    
	    FileSubDocument filesubdocument = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(response.getBody(), FileSubDocument.class);
	    FileModel filemodel = new FileModel();
	    filemodel.setDefect_id(defect_id);
	    
	    List<FileSubDocument> list=new ArrayList<FileSubDocument>();
	    list.add(filesubdocument);
	    filemodel.setFilesubdocument(list);
		

		return fileservice.addFile(filemodel);

	}
	
	@PostMapping(value = "/updatefilebyid", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public FileModel updateFileByIdAndAssetId(@RequestPart("file") MultipartFile file, @RequestPart("defect_id") String  defect_id,@RequestPart("asset_id") String  asset_id) throws IOException {
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
		
	    
	    FileSubDocument filesubdocument = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(response.getBody(), FileSubDocument.class);
	    FileModel filemodel = new FileModel();
	    filemodel.setDefect_id(defect_id);
	    
	    List<FileSubDocument> list=new ArrayList<FileSubDocument>();
	    list.add(filesubdocument);
	    filemodel.setFilesubdocument(list);
		
		

		return fileservice.updateFileByIdAndAssetId(filemodel,defect_id,asset_id);

	}
	
	@DeleteMapping("/deleteallfilesbyid")
	public String deleteAllFiles(@RequestBody HashMap<String, String> dataHashMap) {
		return fileservice.deleteAllFiles(dataHashMap.get("defect_id"));
		
	}
	
	@DeleteMapping("/deletefilesbyassetid")
	public String deleteFileByAssetId(@RequestBody HashMap<String, String> dataHashMap) {
		return fileservice.deleteFileByAssetId(dataHashMap.get("defect_id"),dataHashMap.get("asset_id"));
		
	}
	

	
	

}
