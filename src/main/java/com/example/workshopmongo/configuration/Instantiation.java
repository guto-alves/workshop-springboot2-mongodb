package com.example.workshopmongo.configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.dto.AuthorDTO;
import com.example.workshopmongo.dto.CommentDTO;
import com.example.workshopmongo.repository.PostRepository;
import com.example.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(List.of(maria, alex, bob));

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

		Post post1 = new Post(null, dateFormat.parse("21/03/2018"), 
				"Partiu viagem", "Vou viajar para São Paulo. Abraços!", 
				new AuthorDTO(maria));
		
		Post post2 = new Post(null, dateFormat.parse("23/03/2018"), 
				"Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		CommentDTO comment1 = new CommentDTO(
				"Boa viagem mano!", dateFormat.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO comment2 = new CommentDTO(
				"Aproveite!", dateFormat.parse("22/03/2018"), new AuthorDTO(maria));
		CommentDTO comment3 = new CommentDTO(
				"Tenha um ótimo dia!", dateFormat.parse("23/03/2018"), new AuthorDTO(alex));
		
		post1.getComments().addAll(List.of(comment1, comment2));
		post2.getComments().add(comment3);
		
		postRepository.saveAll(List.of(post1, post2));
	
		maria.getPosts().addAll(List.of(post1, post2));
		userRepository.save(maria);
	}

}
