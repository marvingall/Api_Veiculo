package ApiVeiculo.APS.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*")  // Permite CORS em todas as rotas
                .allowedOrigins("")    // Permite todos os domínios
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos permitidos
                .allowedHeaders("*")    // Permite todos os headers
                .allowCredentials(true);  // Permite o envio de cookies/sessões
    }
}