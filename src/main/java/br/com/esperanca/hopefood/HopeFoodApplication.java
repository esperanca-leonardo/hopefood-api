package br.com.esperanca.hopefood;

import br.com.esperanca.hopefood.infrastructure.repositories.JpaRepositoryCustomImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = JpaRepositoryCustomImpl.class)
public class HopeFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(HopeFoodApplication.class, args);
	}
}
