package umc.springumc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableRedisRepositories
public class SpringUmcApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringUmcApplication.class, args);
	}
}
