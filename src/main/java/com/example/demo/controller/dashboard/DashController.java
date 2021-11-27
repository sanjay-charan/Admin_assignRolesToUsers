package com.example.demo.controller.dashboard;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.dashboard.DashboardService;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashController {
	
	
	@Autowired
	private DashboardService dashservice;

	@GetMapping("/getprevcount")
	public long getPrevDayCount(@RequestBody HashMap<String, String> dataHashMap) {
		
		return dashservice.getPrevDayCountAndUpdate(dataHashMap.get("historyType"), Long.parseLong(dataHashMap.get("currCount")));
	}
	
}
