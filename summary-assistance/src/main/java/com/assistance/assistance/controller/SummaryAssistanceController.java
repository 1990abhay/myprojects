package com.assistance.assistance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assistance.assistance.model.SummaryAssistanceModel;
import com.assistance.assistance.service.SummaryAssistanceService;


@RestController
@RequestMapping("/api/summaryassistance")
@CrossOrigin("*")
public class SummaryAssistanceController {
	
	
	  @Autowired 
	  
	  SummaryAssistanceService summaryAssistanceService;
	  
	  //http://localhost:8073/api/summaryassistance/processContent
	  @RequestMapping("/processContent") public
	  ResponseEntity<String>processContect(@RequestBody SummaryAssistanceModel summaryAssistanceModel){ 
		  String result=summaryAssistanceService.processResult(summaryAssistanceModel); return
		  ResponseEntity.ok(result); 
	  
	  }
	 
}
