package com.education.quizapp.dao;

import org.springframework.stereotype.Repository;

import com.education.quizapp.model.Questions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface QuestionsDao extends JpaRepository<Questions,Integer>{
	
	List<Questions>findByCategory(String category);
	
	@Query(value = "select TOP(:numQ) * from questions q where q.category=:category",nativeQuery=true)
	List<Questions> findRandomQuestionsByCategory(int numQ, String category);
}
