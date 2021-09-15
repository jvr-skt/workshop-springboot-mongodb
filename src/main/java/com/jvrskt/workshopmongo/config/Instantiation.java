package com.jvrskt.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jvrskt.workshopmongo.domain.User;
import com.jvrskt.workshopmongo.repositories.UserRepository;


@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		
		User jean = new User(null, "Jean Victor", "jean@gmail.com");
		User bruna = new User(null, "Bruna Julliene", "bruna@gmail.com");
		User marlene = new User(null, "Marlene Rodrigues", "marlene@gmail.com");
		User nilson = new User(null, "Nilson Ferreira", "nilson@gmail.com");
		
		userRepository.saveAll(Arrays.asList(jean, bruna, marlene, nilson));
		
		
	}

}
