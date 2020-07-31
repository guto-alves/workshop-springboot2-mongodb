package com.example.workshopmongo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostResource {

	@Autowired
	private PostService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post post = service.findById(id);
		return ResponseEntity.ok(post);
	}

	@GetMapping("/search")
	public ResponseEntity<List<Post>> findByTitle(
			@RequestParam(name = "title", defaultValue = "") String text) {
		return ResponseEntity.ok(service.findByTitle(text));
	}
}
