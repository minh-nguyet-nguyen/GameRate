package com.minh.project.GameRateService.api;

public interface Game {
	
	Long getId();
	
	void setId(Long id);
	
	String getTitle();
	
	void setTitle(String title);
	
	String getComment();
	
	void setComment(String comment);
	
	Integer getRating();
	
	void setRating(Integer rating);
}
