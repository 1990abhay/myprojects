package com.mymicroservices.quiz_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.mymicroservices.quiz_service.model.QuestionsWrapper;
import com.mymicroservices.quiz_service.model.QuizDTO;
import com.mymicroservices.quiz_service.model.Response;
import com.mymicroservices.quiz_service.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<String>creatQuizz(@RequestBody QuizDTO quizDTO){
		
		return quizService.creatQuizz(quizDTO.getCategoryNameString(),quizDTO.getNumQuestionInteger(),quizDTO.getTitle());
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionsWrapper>>getQuizQuestions(@PathVariable Integer id){
		return quizService.getQuizQuestions(id);
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer>submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses ){
		
		return quizService.calculateResult(id,responses);
	}
	
}
