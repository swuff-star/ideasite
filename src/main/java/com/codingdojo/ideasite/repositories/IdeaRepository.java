package com.codingdojo.ideasite.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codingdojo.ideasite.models.Idea;
import com.codingdojo.ideasite.models.User;

@Repository
public interface IdeaRepository extends CrudRepository<Idea, Long> {
	List<Idea> findAll();
	List<Idea> findAllByUser(User user);
	List<Idea> findAllByUserId(Long userId);
}