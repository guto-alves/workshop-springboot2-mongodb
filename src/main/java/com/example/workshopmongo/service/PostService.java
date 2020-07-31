package com.example.workshopmongo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.repository.PostRepository;
import com.example.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public Post findById(String id) {
		Optional<Post> post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Post not found"));
	}

	public List<Post> findByTitle(String text) {
		return repository.findByTitle(text);
	}

	public List<Post> findBy(String text, Date min, Date max) {
		if (min == null) {
			min = new Date(0L);
		}

		if (max == null) {
			max = new Date();
		}
		
		return repository.findBy(text, min, max);
	}

}
