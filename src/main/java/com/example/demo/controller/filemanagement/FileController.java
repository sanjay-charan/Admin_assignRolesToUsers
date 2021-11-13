package com.example.demo.controller.filemanagement;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.filemanagement.FileModel;
import com.example.demo.service.filemangement.FileService;
import com.example.demo.utilities.Cloudinary;


@RestController
@RequestMapping("/api/v1/file")
public class FileController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);


	@Autowired
	private FileService fileservice;

	@GetMapping("/getallfiles")
	public List<FileModel> getAllFiles() {
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
		return fileservice.getFileByAssetId(dataHashMap.get("defect_id"), dataHashMap.get("asset_id"));
	}

	@PostMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public FileModel uploadFile(@RequestPart("file") MultipartFile file, @RequestPart("defect_id") String defect_id)
			throws IOException {

		return fileservice.addFile(Cloudinary.uploadToCloudinary(file, defect_id));

	}

	@PostMapping(value = "/updatefilebyid", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public FileModel updateFileByIdAndAssetId(@RequestPart("file") MultipartFile file,
			@RequestPart("defect_id") String defect_id, @RequestPart("asset_id") String asset_id) throws IOException {
	
		return fileservice.updateFileByIdAndAssetId(Cloudinary.uploadToCloudinary(file, defect_id), defect_id, asset_id);

	}

	@DeleteMapping("/deleteallfilesbyid")
	public String deleteAllFiles(@RequestBody HashMap<String, String> dataHashMap) {
		return fileservice.deleteAllFiles(dataHashMap.get("defect_id"));

	}

	@DeleteMapping("/deletefilesbyassetid")
	public String deleteFileByAssetId(@RequestBody HashMap<String, String> dataHashMap) {
		return fileservice.deleteFileByAssetId(dataHashMap.get("defect_id"), dataHashMap.get("asset_id"));

	}

}
