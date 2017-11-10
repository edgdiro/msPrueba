package pe.infast.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pe.infast.security.model.User;
import pe.infast.security.model.embebed.Rol;
import pe.infast.security.repository.UserRepository;

import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

import java.util.stream.Stream;

@SpringBootApplication
public class SecurityApplication {

	private static Logger logger = LoggerFactory.getLogger(SecurityApplication.class);


	@Bean
	CommandLineRunner users(UserRepository repository) {
		
		return args ->{

			List<Rol> roles = new ArrayList<Rol>();
			roles.add(new Rol("admin"));

			repository.deleteAll()
			.subscribe(null, null, ()-> Stream.of(new User("17162504", UUID.randomUUID().toString(), "2","og1142s","123456",roles,1, "17162504", "17162504"),
                    new User(UUID.randomUUID().toString(), UUID.randomUUID().toString(), "1","demer342","8524163",roles,1,"17162504", "17162504"),
                    new User(UUID.randomUUID().toString(), UUID.randomUUID().toString(), "1","loged543","8529874",roles,1,"17162504", "17162504"),
                    new User(UUID.randomUUID().toString(), UUID.randomUUID().toString(), "1","rfe546","85274136",roles,1,"17162504", "17162504"),
                    new User(UUID.randomUUID().toString(), UUID.randomUUID().toString(), "1","tye543","9632587",roles,1,"17162504", "17162504"))
                    .forEach(user -> repository.save(user)
.subscribe(System.out::println)));
		};
	}


	public static void main(String[] args)throws Exception {

		SpringApplication.run(SecurityApplication.class, args);
		logger.info("Application started...");



	}
}
