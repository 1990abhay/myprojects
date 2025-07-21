package com.education.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.education.quizapp.dao.QuestionsDao;
import com.education.quizapp.dao.QuizDao;
import com.education.quizapp.model.Questions;
import com.education.quizapp.model.QuestionsWrapper;
import com.education.quizapp.model.Quiz;
import com.education.quizapp.model.Response;


@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	@Autowired
	QuestionsDao questionsDao;

	public ResponseEntity<String> creatQuizz(String category, int numQ, String title) {
		
		List<Questions> questions=questionsDao.findRandomQuestionsByCategory(numQ,category);
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		
		return new ResponseEntity<>("success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionsWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz=quizDao.findById(id);
		List<Questions> questionsFromDB=quiz.get().getQuestions();
		List<QuestionsWrapper> questionsForUsersList=new ArrayList<>();
		
		for(Questions q:questionsFromDB) {
			QuestionsWrapper qWrapper=new QuestionsWrapper(q.getId(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4(), q.getQuestionTitle());
			questionsForUsersList.add(qWrapper);
		}
		
		return new ResponseEntity<>(questionsForUsersList,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz=quizDao.findById(id).get();
		List<Questions> questions=quiz.getQuestions();
		int right=0;
		int i=0;
		for(Response response: responses) {
			if(response.getResponse().equals(questions.get(i).getRightAnswer()))
				right++;
			
			i++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}
}
