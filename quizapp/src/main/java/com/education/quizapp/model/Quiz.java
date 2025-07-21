package com.education.quizapp.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Data
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	public String title;
	
	@ManyToMany
	public List<Questions> questions;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title=title;
		
	}

	public void setQuestions(List<Questions> questions) {
		this.questions=questions;
	}

	public List<Questions> getQuestions() {
		return questions;
	}

}
