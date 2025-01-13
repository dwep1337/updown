package github.dwep1337.updown.config;


import java.net.URI;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

@Configuration
public class MinIOConfig {

    private final String MINIO_ENDPOINT = DotEnvConfig.dotenv().get("MINIO_ENDPOINT");
    private final String MINIO_ACCESS_KEY = DotEnvConfig.dotenv().get("MINIO_ACCESS_KEY");
    private final String MINIO_SECRET_KEY = DotEnvConfig.dotenv().get("MINIO_SECRET_KEY");


    @Bean
    public S3Client s3Config() {
        return S3Client.builder().region(Region.US_EAST_1)
                .endpointOverride(URI.create(MINIO_ENDPOINT))
                .credentialsProvider(StaticCredentialsProvider
                        .create(AwsBasicCredentials.create(MINIO_ACCESS_KEY, MINIO_SECRET_KEY)))
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(true) //need this for work with minio
                        .build()).build();
    }

}
