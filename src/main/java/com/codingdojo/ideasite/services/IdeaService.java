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
	private final IdeaRepository productRepository;
	
	@Autowired
	public IdeaService(IdeaRepository prodRepository) {
		this.productRepository = prodRepository;
	}
	
	public List<Idea> allGames() {
		return productRepository.findAll();
	}
	
	public Idea createProduct(Idea prod) {
		return productRepository.save(prod);
	}
	
	public Idea findProduct(Long id) {
		Optional<Idea> prodOptional = productRepository.findById(id);
		return prodOptional.orElse(null);
	}
	
	public Idea updateProduct(Long id, String name, String desc) {
		Idea prod = productRepository.findById(id).orElse(null);
		if (prod != null) {
			prod.setpName(name);
			prod.setpDesc(desc);
			return productRepository.save(prod);
		}
		return null;
	}
	
	public Idea updateProductFromProd(Idea game) {
		return productRepository.save(game);
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
	public List<Idea> ideasAlphabetical() {
	    List<Idea> prods = productRepository.findAll();
	    prods.sort(Comparator.comparing(Idea::getpName));
	    return prods;
	}
	
	public List<Idea> findProductsByUserId(Long userId) {
	    return productRepository.findAllByUserId(userId);
	}
}
