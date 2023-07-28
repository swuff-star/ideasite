package com.codingdojo.ideasite.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.ideasite.models.Idea;
import com.codingdojo.ideasite.repositories.IdeaRepository;

@Service
public class IdeaService {
	private final IdeaRepository ideaRepository;
	
	@Autowired
	public IdeaService(IdeaRepository ideaRepository) {
		this.ideaRepository = ideaRepository;
	}
	
	public List<Idea> allIdeas() {
		return ideaRepository.findAll();
	}
	
	public Idea createIdea(Idea idea) {
		return ideaRepository.save(idea);
	}
	
	public Idea findIdea(Long id) {
		Optional<Idea> ideaOptional = ideaRepository.findById(id);
		return ideaOptional.orElse(null);
	}
	
	public Idea updateIdea(Long id, String name, String desc) {
		Idea idea = ideaRepository.findById(id).orElse(null);
		if (idea != null) {
			idea.setpName(name);
			idea.setpDesc(desc);
			return ideaRepository.save(idea);
		}
		return null;
	}
	
	public Idea updateIdeaFromIdea(Idea idea) {
		return ideaRepository.save(idea);
	}
	
	public void deleteIdea(Long id) {
		ideaRepository.deleteById(id);
	}
	
	public List<Idea> ideasAlphabetical() {
	    List<Idea> ideas = ideaRepository.findAll();
	    ideas.sort(Comparator.comparing(Idea::getpName));
	    return ideas;
	}
	
	public List<Idea> findIdeaByUserId(Long userId) {
	    return ideaRepository.findAllByUserId(userId);
	}
}
