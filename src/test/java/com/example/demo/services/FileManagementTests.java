package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.io.IOException;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import com.example.demo.constants.Constants;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.filemanagement.FileCount;
import com.example.demo.model.filemanagement.FileModel;
import com.example.demo.service.filemangement.FileService;
import com.example.demo.utilities.Cloudinary;

@SpringBootTest
public class FileManagementTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	void contextLoads() {
	}

//	@SpyBean
//	private MongoTemplate mongoTemplate;

	@Autowired
	private MongoOperations mongoOperations;
//	
//	MongoTemplate mongoTemplate = Mockito.spy(
//		    //Instance the MongoTemplate, use any test framework
//		    new MongoTemplate(new SimpleMongoClientDbFactory("mongodb://localhost/test"))
//		);

	@Autowired
	private FileService service;
	MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE,
			"Hello, World!".getBytes());

//	@Test
//	public void addFileTest() throws IOException {
//
//		assertEquals("DEF_10", service.addFile(Cloudinary.uploadToCloudinary(file, "DEF_10")).getDefect_id());
//	}

	@Test
	public void updateFileByIdAndAssetIdTest() throws IOException {

		Assertions.assertThrows(BadRequestException.class,
				() -> service.updateFileByIdAndAssetId(Cloudinary.uploadToCloudinary(file, "DEF_5"), "DEF_5",
						"60519e15be0455795b8ef100685a71a1").getDefect_id());

//		assertEquals("DEF_5", service.updateFileByIdAndAssetId(Cloudinary.uploadToCloudinary(file, "DEF_5"),"DEF_5","60519e15be0455795b8ef100685a71a1").getDefect_id());

	}

	@Test
	public void addFileTest() throws IOException {
		
		
		FileModel filemodel = Cloudinary.uploadToCloudinary(file, "DEF_10");
		
		Update update = new Update().addToSet(Constants.FILE_SUB_DOCUMENT, filemodel.getFilesubdocument().get(0));

		Query q = new Query();
		q.addCriteria(Criteria.where(Constants.DEFECT_ID).is(filemodel.getDefect_id()));

		when(mongoOperations.findAndModify(q, update, options().returnNew(true).upsert(true), FileModel.class)).thenReturn(filemodel);

		assertEquals(filemodel, service.addFile(filemodel));
	}

	@Test
	public void getAllFilesTest() {
		assertEquals(5, service.getAllFiles().size());
	}

	@Test
	public void getFileByIdTest() {

		assertEquals("DEF_10", service.getFileById("DEF_10").getDefect_id());

	}

	@Test
	public void getFileByAssetIdTest() {
		assertEquals("DEF_3", service.getFileByAssetId("DEF_3", "9990e0fb5df653797045b13cdec03157").getDefect_id());
	}

	@Test
	public void deleteAllFilesTest() {

		Assertions.assertThrows(BadRequestException.class, () -> service.deleteAllFiles("DEF_2"));
		;
	}

	@Test
	void FileCountTest() {

		FileCount filecount = new FileCount("test", 10);
	}

	@Test
	void uploadToCloudinaryTest() throws IOException {

		assertEquals("DEF_10", Cloudinary.uploadToCloudinary(file, "DEF_10").getDefect_id());

	}

}
