package com.example.demo.controller.defect;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.defect.Comments;
import com.example.demo.model.defect.Defect;
import com.example.demo.service.defect.DefectService;
import com.example.demo.service.defect.IdGen;
import com.example.demo.service.defect.SequenceGenService;
import com.example.demo.utilities.Dashboard;


@RequestMapping("/api/defects")
@RestController
public class DefectController {
	
	@Autowired
	private SequenceGenService service;
	private DefectService defService;
	
	public DefectController(DefectService defService) {
		System.out.println("Heyy...I got an Defect Service Object");
		this.defService = defService;
	}
	
	@PostMapping("/create")
	public String createDefect(@RequestBody Defect defect) {
		defect.setId("D"+service.getCount(IdGen.getSequenceName()));
		return defService.addDefect(defect);
	}
	
	@GetMapping("/display")
	public List<Defect> getAllDefects(){
		System.out.println("getAllDefects");
		return defService.getAllDefects();
	}
	
	@GetMapping("/display/{id}")
	public Dashboard getDefectById(@PathVariable("id") String id){
		System.out.println("getAllDefects");
		return defService.getDefectById(id);
	}
	
	@PostMapping("/addComment")
	public String addComment(@RequestBody Comments c) {
		return defService.addComment(c);
	}
	
	@PutMapping("/update")
	public String updateDefect(@RequestBody Map<String,String> defect) {
		System.out.println("updateDefectByID"+defect.get("id"));
		return defService.updateDefectByID(defect);
	}
	
	@PutMapping("/updateStatus/{id}")
	public String updateDefectStatus(@PathVariable("id") String id, @RequestBody Map<String,String> defect) {
		System.out.println("updateStatusByID"+defect.get("id"));
		return defService.updateDefectStatus(defect);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteDefect(@PathVariable("id") String id) {
		return defService.deleteDefect(id);
	}
}
