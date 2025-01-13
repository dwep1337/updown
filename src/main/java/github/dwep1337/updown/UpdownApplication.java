package github.dwep1337.updown;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UpdownApplication {

	public static void main(String[] args) {

		loadEnv();

		SpringApplication.run(UpdownApplication.class, args);
	}

	private static void loadEnv() {
		Dotenv dotenv = Dotenv.configure().load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
	}

}
