package com.education.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.quizapp.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer>{

}
