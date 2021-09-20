package com.jvrskt.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jvrskt.workshopmongo.domain.Post;
import com.jvrskt.workshopmongo.domain.User;
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
		
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", marlene);
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia","Acordei feliz hoje!", marlene);
		
		userRepository.saveAll(Arrays.asList(jean, bruna, marlene, nilson));
		postRepository.saveAll(Arrays.asList(post1, post2));
		
	}

}
