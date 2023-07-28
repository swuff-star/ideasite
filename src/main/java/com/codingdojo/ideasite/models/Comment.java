package com.codingdojo.ideasite.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="comments")

public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Comment required to submit!")
	@Size(min=1, max=255, message="Comment must be between 1 and 255 characters.")
	private String cMsg;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idea_id")
	private Idea idea;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	public Comment () { }
	
	public Comment(Long id, String msg, Idea idea) {
		this.id = id;
		this.cMsg = msg;
		this.idea = idea;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getcMsg() {
		return cMsg;
	}

	public void setcMsg(String cMsg) {
		this.cMsg = cMsg;
	}

	public Idea getIdea() {
		return idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}
	
	public String getCommenterName() {
        return user.getUserName();
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
