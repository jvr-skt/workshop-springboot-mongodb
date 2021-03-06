package com.jvrskt.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jvrskt.workshopmongo.domain.Post;
import com.jvrskt.workshopmongo.domain.User;
import com.jvrskt.workshopmongo.dto.AuthorDTO;
import com.jvrskt.workshopmongo.dto.CommentDTO;
import com.jvrskt.workshopmongo.repositories.PostRepository;
import com.jvrskt.workshopmongo.repositories.UserRepository;


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

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		User jean = new User(null, "Jean Victor", "jean@gmail.com");
		User bruna = new User(null, "Bruna Julliene", "bruna@gmail.com");
		User marlene = new User(null, "Marlene Rodrigues", "marlene@gmail.com");
		User nilson = new User(null, "Nilson Ferreira", "nilson@gmail.com");
		
		userRepository.saveAll(Arrays.asList(jean, bruna, marlene, nilson));

		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(marlene));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(marlene));
		
		CommentDTO comment1 = new CommentDTO("Boa viagem meu filho!", sdf.parse("21/03/2018"), new AuthorDTO(nilson));
		CommentDTO comment2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(bruna));
		CommentDTO comment3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("22/03/2018"), new AuthorDTO(jean));
		
		post1.getComments().addAll(Arrays.asList(comment1, comment2));
		post2.getComments().addAll(Arrays.asList(comment3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		marlene.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(marlene);
		
	}

}
