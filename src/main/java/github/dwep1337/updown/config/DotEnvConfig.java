package github.dwep1337.updown.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotEnvConfig {

    @Bean
    public static Dotenv dotenv() {
        return Dotenv.configure().load();
    }
}
