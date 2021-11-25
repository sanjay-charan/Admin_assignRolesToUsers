package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.model.filemanagement.FileCount;
import com.example.demo.service.filemangement.FileService;
import com.example.demo.utilities.Cloudinary;

@SpringBootTest
class EmpCrudApplicationTests {

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



	@Test
	public void addFileTest() throws IOException {
		MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE,
				"Hello, World!".getBytes());
		assertEquals("DEF_10", service.addFile(Cloudinary.uploadToCloudinary(file, "DEF_10")).getDefect_id());
	}
	
	@Test
	public void getAllFilesTest() {
		assertEquals(6, service.getAllFiles().size());		
	}
	
	@Test
	public void getFileByIdTest() {
		assertEquals("DEF_10", service.getFileByAssetId( "DEF_10" , "4b1ec76e678811e39eaf9eadf9532301").getDefect_id());
		
	}
	
	@Test
	void FileCountTest() {
		
		FileCount filecount=new FileCount("test",10);
	}
	
	@Test
	void uploadToCloudinaryTest() throws IOException {
		MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE,
				"Hello, World!".getBytes());
		
		assertEquals("DEF_10",Cloudinary.uploadToCloudinary(file, "DEF_10").getDefect_id());
		
		
		
	}

}
