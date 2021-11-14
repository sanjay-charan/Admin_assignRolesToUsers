package com.example.demo.model.filemanagement;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;


@JsonIgnoreProperties
//@Data
//@AllArgsConstructor
//@Getter
//@Setter
@Document(collection = "filecount")
public class FileCount {
	
	private String time;
	private long files_count;
	
	
	public String getTime() {
		return time;
	}
	public FileCount(String time, long files_count) {
		super();
		this.time = time;
		this.files_count = files_count;
	}
	public FileCount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setTime(String time) {
		this.time = time;
	}
	public long getFiles_count() {
		return files_count;
	}
	public void setFiles_count(long files_count) {
		this.files_count = files_count;
	}
	

}
