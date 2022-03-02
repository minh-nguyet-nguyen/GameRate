package com.minh.project.GameRateService.model;

import com.minh.project.GameRateService.api.Game;
import org.springframework.beans.BeanUtils;


public class GameView implements Game {
	Long id;
	String title;
	String comment;
	Integer rating;
	
	GameView() {}
	
	GameView(Game game) {
		BeanUtils.copyProperties(game, this, GameView.class);
	}
	
	public static GameView convert(Game game) {
		if (game == null) {
			return null;
		}
		if(game instanceof GameView) {
			return (GameView) game;
		}
		
		return new GameView(game);
	}
	
	@Override
	public Long getId() {
		return this.id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String getTitle() {
		return this.title;
	}
	
	@Override
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String getComment() {
		return comment;
	}
	
	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public Integer getRating() {
		return this.rating;
	}
	
	@Override
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return "Game [id=" + id + ", title=" + title + ", comment=" + comment + ", rating=" + rating +  "]";
	}
}
