package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import com.example.demo.model.filemanagement.FileCount;
import com.example.demo.service.filemangement.FileService;
import com.example.demo.utilities.Cloudinary;

@SpringBootTest
public class FileManagementTests {
	
	@Test
	void contextLoads() {
	}

	@SpyBean
	private MongoTemplate mongoTemplate;

//	@MockBean
//	private MongoTemplate mongoTemplate;
//	
//	MongoTemplate mongoTemplate = Mockito.spy(
//		    //Instance the MongoTemplate, use any test framework
//		    new MongoTemplate(new SimpleMongoClientDbFactory("mongodb://localhost/test"))
//		);

	@Autowired
	private FileService service;
	MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE,
			"Hello, World!".getBytes());


	@Test
	public void addFileTest() throws IOException {
		
		assertEquals("DEF_10", service.addFile(Cloudinary.uploadToCloudinary(file, "DEF_10")).getDefect_id());
	}
	
	
	
//	@Test
//	public void updateFileByIdAndAssetIdTest() throws IOException {
//		
//		
//		assertEquals("DEF_5", service.updateFileByIdAndAssetId(Cloudinary.uploadToCloudinary(file, "DEF_5"),"DEF_5","60519e15be0455795b8ef100685a71a1").getDefect_id());
//		
//	}
	@Test
	public void getAllFilesTest() {
		assertEquals(6, service.getAllFiles().size());		
	}
	
	@Test
	public void getFileByIdTest() {
		
		assertEquals("DEF_10", service.getFileById( "DEF_10").getDefect_id());
		
	}
	
	@Test
	public void getFileByAssetIdTest() {
		assertEquals("DEF_2", service.getFileByAssetId( "DEF_3" ,"9990e0fb5df653797045b13cdec03157").getDefect_id());
	}
	
	@Test
	public void deleteAllFilesTest() {
		service.deleteAllFiles( "DEF_2");
	}
	
	@Test
	void FileCountTest() {
		
		FileCount filecount=new FileCount("test",10);
	}
	
	@Test
	void uploadToCloudinaryTest() throws IOException {
		
		assertEquals("DEF_10",Cloudinary.uploadToCloudinary(file, "DEF_10").getDefect_id());
		
		
		
	}

}
