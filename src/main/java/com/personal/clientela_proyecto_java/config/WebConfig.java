package com.personal.clientela_proyecto_java.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(@org.springframework.lang.NonNull ResourceHandlerRegistry registry) {
        // Mapear URL /uploads/** a la carpeta física C:/crud_clientes/uploads/
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:C:/crud_clientes/uploads/");

        // También nos aseguramos de que los recursos estáticos internos funcionen (si
        // los hubiera)
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/", "classpath:/public/", "classpath:/resources/");
    }
}
