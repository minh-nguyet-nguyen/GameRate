package com.minh.project.model;

import com.minh.project.model.Game;

public class Game {

	private Long id;
	
	private String title;
	
	private String comment;
	
	private Integer rating;
	
	public Game(){
		id = 0L;
	}
	
	public Game(Long id, String title, String comment, Integer rating){
		this.id = id;
		this.title = title;
		this.comment = comment;
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Game))
			return false;
		Game other = (Game) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", title=" + title + ", comment=" + comment + ", rating=" + rating +  "]";
	}
}