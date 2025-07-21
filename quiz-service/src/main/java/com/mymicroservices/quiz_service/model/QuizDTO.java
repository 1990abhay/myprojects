package com.mymicroservices.quiz_service.model;

import lombok.Data;

@Data
public class QuizDTO {
	public String categoryName;
	public Integer numQuestion;
	public String title;
	public String getCategoryNameString() {
		return categoryName;
	}
	public void setCategoryNameString(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getNumQuestionInteger() {
		return numQuestion;
	}
	public void setNumQuestionInteger(Integer numQuestion) {
		this.numQuestion = numQuestion;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
