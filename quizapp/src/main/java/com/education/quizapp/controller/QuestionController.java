package com.education.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.education.quizapp.model.Questions;
import com.education.quizapp.service.QuestionService;

@RestController
@RequestMapping("questions")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	/*
	 * @GetMapping("allQuestions") public List<Questions> allQuestions() { return
	 * questionService.getAllQuestions(); }
	 */
	@GetMapping("allQuestions")
	public ResponseEntity<List<Questions>> allQuestions() {		
		return questionService.getAllQuestions();
	}
	@GetMapping("category/{category}")
	public List<Questions>getQuestionsByCategory(@PathVariable String category){	
		return questionService.getQuestionsByCategory(category);
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addQuestions(@RequestBody Questions questions) {	
		return questionService.addQuestions(questions);
	}
	
	@DeleteMapping("delete/{id}")
	public String deleteQuestions(@PathVariable Integer id) {
		return questionService.deleteQuestion(id);
	}
}
