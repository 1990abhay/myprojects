package com.mymicroservices.quiz_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mymicroservices.quiz_service.dao.QuizDao;
import com.mymicroservices.quiz_service.feign.QuizFeignInterface;
import com.mymicroservices.quiz_service.model.QuestionsWrapper;
import com.mymicroservices.quiz_service.model.Quiz;
import com.mymicroservices.quiz_service.model.Response;


@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuizFeignInterface quizFeignInterface; 

	public ResponseEntity<String> creatQuizz(String category, int numQ, String title) {
		
		List<Integer> questions =quizFeignInterface.getQuestionsForQuiz(category, numQ).getBody();
		
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		
		return new ResponseEntity<>("success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionsWrapper>> getQuizQuestions(Integer id) {
		Quiz quiz=quizDao.findById(id).get();
		List<Integer> questionsIds= quiz.getQuestions();
		ResponseEntity<List<QuestionsWrapper>> getquestionsList=quizFeignInterface.getQuestionsFromId(questionsIds);
		return getquestionsList;
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		ResponseEntity<Integer> score=quizFeignInterface.getScore(responses);
		return score;
	}
}
