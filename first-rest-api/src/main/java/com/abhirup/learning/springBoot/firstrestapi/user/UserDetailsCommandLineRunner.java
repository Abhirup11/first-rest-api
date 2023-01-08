package com.abhirup.learning.springBoot.firstrestapi.user;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsCommandLineRunner implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private UserDetailsRepository repository;
	
	public UserDetailsCommandLineRunner(UserDetailsRepository userDetailsRepository) {
		super();
		this.repository = userDetailsRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		
		repository.save(new UserDetails("Abhirup", "developer"));
		repository.save(new UserDetails("Ravi", "Admin"));
		repository.save(new UserDetails("John", "support"));
		
//		List<UserDetails> users = repository.findAll();
		List<UserDetails> users = repository.findByRole("support");
		users.forEach(user -> logger.info(user.toString()));
		
		logger.info(Arrays.toString(args));
		
	}

}
