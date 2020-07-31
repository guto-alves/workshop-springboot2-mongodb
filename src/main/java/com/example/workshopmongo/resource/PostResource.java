package com.example.workshopmongo.resource;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
	
	@GetMapping("/fullsearch")
	public ResponseEntity<List<Post>> findBy(
			@RequestParam("text") String text,
			@RequestParam(name = "min", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date min,
			@RequestParam(name = "max", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date max) {
		return ResponseEntity.ok(service.findBy(text, min, max));
	}
}
