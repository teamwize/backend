package app.workive.api.base.config;

import io.hypersistence.utils.spring.repository.BaseJpaRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(
        value = "app.workive.api",
        repositoryBaseClass = BaseJpaRepositoryImpl.class
)
public class JpaConfiguration {

}