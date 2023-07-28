package com.codingdojo.ideasite.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.ideasite.models.Comment;
import com.codingdojo.ideasite.repositories.CommentRepository;

@Service
public class CommentService {
	private final CommentRepository commentRepository;
	
	@Autowired
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	public List<Comment> allComments() {
		return commentRepository.findAll();
	}
	
	public Comment createComment(Comment comment) {
		return commentRepository.save(comment);
	}
	
	public Comment findComment(Long id) {
		Optional<Comment> commentOptional = commentRepository.findById(id);
		return commentOptional.orElse(null);
	}
	
	public Comment updateComment(Long id, String msg) {
		Comment comment = commentRepository.findById(id).orElse(null);
		if (comment != null) {
			comment.setcMsg(msg);
			return commentRepository.save(comment);
		}
		return null;
	}
	
	public Comment updateCommentFromComment(Comment comment) {
		return commentRepository.save(comment);
	}
	
	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}
}
