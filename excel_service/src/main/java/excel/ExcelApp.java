package excel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class ExcelApp implements EmbeddedServletContainerCustomizer
{
    public static void main( String[] args )
    {
        SpringApplication.run(ExcelApp.class, args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {

        container.setPort(10004);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**/*")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "HEAD", "PUT", "DELETE");
            }
        };
    }
}
