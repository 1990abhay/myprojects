package com.mymicroservices.question_service.dao;

import org.springframework.stereotype.Repository;

import com.mymicroservices.question_service.model.Questions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface QuestionsDao extends JpaRepository<Questions,Integer>{
	
	List<Questions>findByCategory(String category);
	
	@Query(value = "select TOP(:numQ) id from questions q where q.category=:category",nativeQuery=true)
	List<Integer> findRandomQuestionsByCategory(int numQ, String category);
}
