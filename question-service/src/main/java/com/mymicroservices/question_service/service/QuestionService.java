package com.mymicroservices.question_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mymicroservices.question_service.model.Questions;
import com.mymicroservices.question_service.model.QuestionsWrapper;
import com.mymicroservices.question_service.model.Response;
import com.mymicroservices.question_service.dao.QuestionsDao;

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

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
		
		List<Integer> questions=questionsDao.findRandomQuestionsByCategory(numQuestions,categoryName);
		return new ResponseEntity<>(questions,HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionsWrapper>> getQuestionsFromId(List<Integer> questionsIDs) {
		List<QuestionsWrapper> questionsWrapper=new ArrayList<QuestionsWrapper>();
		List<Questions> questions=new ArrayList<Questions>();
		for(Integer id:questionsIDs) {
			questions.add(questionsDao.findById(id).get());
		}
		for(Questions questions2:questions) {
			QuestionsWrapper qWrapper=new QuestionsWrapper(questions2.getId(), questions2.getOption1(), questions2.getOption2(), questions2.getOption3(), questions2.getOption4(), questions2.getQuestionTitle());
			questionsWrapper.add(qWrapper);
		}
		return new ResponseEntity<>(questionsWrapper,HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		
		int right=0;
		for(Response response: responses) {
			Questions questions=questionsDao.findById(response.getId()).get();
			if(response.getResponse().equals(questions.getRightAnswer()))
				right++;	
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}
}

