package PETVET.bg.petvet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PetvetApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetvetApplication.class, args);
	}

}
