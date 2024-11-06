package com.aston.frontendpracticeservice.config;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;


@ActiveProfiles(TestContainersConfig.TEST_PROFILE)
@Testcontainers
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(initializers = {TestContainersConfig.Initializer.class})
public class TestContainersConfig {
    private static final String POSTGRES_IMAGE_NAME = "postgres:16.4";
    private static final String DATABASE_NAME = "user_db";
    private static final String DATABASE_USERNAME = "postgres";
    private static final String DATABASE_PASSWORD = "postgres";
    public static final String SPRING_DATASOURCE_URL_PROPERTY = "spring.datasource.url=";
    public static final String SPRING_DATASOURCE_USERNAME_PROPERTY = "spring.datasource.username=";
    public static final String SPRING_DATASOURCE_PASSWORD_PROPERTY = "spring.datasource.password=";

    public static final DockerImageName POSTGRES_IMAGE = DockerImageName.parse(POSTGRES_IMAGE_NAME);

    public static final String TEST_PROFILE = "test";

    @Container
    public static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(POSTGRES_IMAGE)
            .withReuse(true)
            .withDatabaseName(DATABASE_NAME)
            .withUsername(DATABASE_USERNAME)
            .withPassword(DATABASE_PASSWORD)
            .withNetwork(Network.SHARED);

    static {
        Startables.deepStart(postgres).join();
    }

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext context) {
            TestPropertyValues.of(
                    SPRING_DATASOURCE_URL_PROPERTY + postgres.getJdbcUrl(),
                    SPRING_DATASOURCE_USERNAME_PROPERTY + postgres.getUsername(),
                    SPRING_DATASOURCE_PASSWORD_PROPERTY + postgres.getPassword()).applyTo(context.getEnvironment());
        }
    }
}
