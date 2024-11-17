package com.example.WeatherDataService.services;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Вказуємо, що CORS налаштовується для всіх шляхів, що починаються з /api/
                .allowedOrigins("http://localhost:3000") // Дозволяємо запити з порту 3000 (React)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Дозволяємо GET, POST, PUT та DELETE запити
                .allowedHeaders("*"); // Дозволяємо всі заголовки
    }
}