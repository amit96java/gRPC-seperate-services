package io.amit.user;

import io.amit.library.LibDto;
import io.amit.user.repository.UserRepository;
import io.amit.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class UserGrpcApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(UserGrpcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.addUsers();
		List<LibDto> booksByUserId = userService.getBooksByUserId(3);
		System.out.println("books are "+booksByUserId);
	}
}
