package com.example.demo.controller.dashboard;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dashboard.IdOnly;
import com.example.demo.model.testcase.TestCaseModel;
import com.example.demo.service.dashboard.DashboardService;
import com.example.demo.service.testcase.TestCaseService;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashController {

	@Autowired
	private DashboardService dashservice;

	@Autowired
	private TestCaseService testCaseService;

//	@GetMapping("/getprevcount")
//	public List<IdOnly> getPrevDayCount(@RequestBody HashMap<String, String> dataHashMap) {
//
//		return dashservice.getPrevDayListAndUpdate(dataHashMap.get("historyType"),
//				Long.parseLong(dataHashMap.get("currCount")));
//	}

	@GetMapping("/getopentests")
	public List<IdOnly> getOpenTests() {
		return testCaseService.getOpenTests();

	}

}
