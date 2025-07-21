package com.education.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.education.quizapp.model.Questions;
import com.education.quizapp.dao.QuestionsDao;

@Service
public class QuestionService {
	
	@Autowired
	QuestionsDao questionsDao;


	public ResponseEntity<List<Questions>>getAllQuestions(){
		try {
			return new ResponseEntity<>(questionsDao.findAll(),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(questionsDao.findAll(),HttpStatus.BAD_REQUEST);
	}
	
	public List<Questions>getQuestionsByCategory(@PathVariable String category){
		
		return questionsDao.findByCategory(category);
	}
	
	
	public ResponseEntity<String> addQuestions(Questions questions) {
		try { 
			questionsDao.save(questions);	
			return new ResponseEntity<>("Success123",HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Success123",HttpStatus.BAD_REQUEST);
	}
	
	public String deleteQuestion(Integer id) {		
		 questionsDao.deleteById(id);
		return "Succefully deleted";
	}
}

