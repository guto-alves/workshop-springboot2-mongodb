package com.example.workshopmongo.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshopmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok(List.of(
				new User("1", "Mary Brown", "maria@gmail.com"),
				new User("2", "Alex Green", "alex@gmail.com")));
	}
}
