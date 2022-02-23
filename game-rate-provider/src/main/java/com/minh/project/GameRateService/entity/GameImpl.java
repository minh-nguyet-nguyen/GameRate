package com.minh.project.GameRateService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.minh.project.GameRateService.api.Game;

@Entity
@Table(name = "GAME")
public class GameImpl implements Game {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
	private Long id;
	
	@Column(name = "TITLE", nullable = false)
	private String title;
	
	@Column(name = "COMMENT", nullable = true)
	private String comment;
	
	@Column(name = "RATING", nullable = false)
	private Integer rating;
	
	GameImpl() {}
	
	GameImpl(Game game) {
		BeanUtils.copyProperties(game, this, GameImpl.class);
	}
	
	public static GameImpl convert(Game game) {
		if (game == null) { 
			return null;
		}
		if (game instanceof GameImpl) {
			return (GameImpl) game;
		}
		return new GameImpl(game);
	}

	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getTitle() {
		return title;
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
		return rating;
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
