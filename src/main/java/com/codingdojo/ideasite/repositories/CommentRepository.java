package com.codingdojo.ideasite.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.ideasite.models.Comment;
import com.codingdojo.ideasite.models.Idea;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	List<Comment> findAll();
	List<Comment> findAllByIdea(Idea idea);
}
