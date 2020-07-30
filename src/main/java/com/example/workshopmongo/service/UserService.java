package com.example.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.repository.UserRepository;
import com.example.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(String id) {
		 Optional<User> optional = repository.findById(id);
		 
		 return optional.orElseThrow(() ->
		 	new ObjectNotFoundException("Object not found"));
	}
	
	public User insert(User user) {
		return repository.insert(user);
	}
	
}
