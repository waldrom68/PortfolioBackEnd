/*
 * Para que se integre/comunique nuestra app frontend
 * Habilitacion del Cors
 */
package com.portfolio.wdr;

import java.util.Date;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
* Clase que habilita CORS
* @author YO Walter Romero
* https://howtodoinjava.com/spring-boot2/spring-cors-configuration/
*/
@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//            .allowedOrigins("http://localhost:4200" )
            .allowedOrigins("https://portfolio-frontend-wdr.web.app")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*");
        
        System.out.println("###### Hola Gil, estoy en WebConfig class, con la prueba 7201 CORS ######");
        Date date = new Date();
        System.out.println(date);
    }
    
}
