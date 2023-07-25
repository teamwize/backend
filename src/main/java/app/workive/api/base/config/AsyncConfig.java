package app.workive.api.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@EnableAsync
@Configuration
public class AsyncConfig {



    @Bean("geocodingExecutor")
    ExecutorService geocodingExecutor() {
        return Executors.newFixedThreadPool(20, new CustomizableThreadFactory("Geocoding-Worker"));
    }

}
