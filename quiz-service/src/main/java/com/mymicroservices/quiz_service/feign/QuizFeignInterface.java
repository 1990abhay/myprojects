package com.mymicroservices.quiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mymicroservices.quiz_service.model.QuestionsWrapper;
import com.mymicroservices.quiz_service.model.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizFeignInterface {
	
	@GetMapping("questions/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam Integer numQuestions);
	
	@PostMapping("questions/getquestions")
	public ResponseEntity<List<QuestionsWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionsIDs);
	
	@PostMapping("questions/getscore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
