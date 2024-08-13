package com.example.sampleapp;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.configuration.SpringDocDataRestConfiguration;
import org.springdoc.core.configuration.SpringDocHateoasConfiguration;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

@Configuration
@ComponentScan(
    basePackages = {"org.springdoc"},
    excludeFilters = {@ComponentScan.Filter(
        type = FilterType.CUSTOM,
        classes = {
            OpenApiConfiguration.SpringDocDataRestFilter.class,
            OpenApiConfiguration.SpringDocHateoasFilter.class
        }
    )}
)
@EnableWebMvc
@PropertySource("classpath:/application.properties")
public class OpenApiConfiguration {


    @Bean
    public OpenAPI exampleSpringOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("Example Swagger API")
                      .description("Example Swagger sample application")
                      .version("v0.0.1")
                      .license(new License().name("Apache 2.0").url("http://springdoc.org")))
            .externalDocs(new ExternalDocumentation()
                              .description("Example Swagger Wiki Documentation")
                              .url("https://springshop.wiki.github.org/docs"));
    }

    @Bean
    public GroupedOpenApi erpApi() {
        return GroupedOpenApi.builder()
            .group("erp")
            .pathsToMatch("/erp/**")
            .build();
    }

    @Bean
    public GroupedOpenApi tdmApi() {
        return GroupedOpenApi.builder()
            .group("tdm")
            .pathsToMatch("/tdm/**")
            .build();
    }

    @Bean
    public OpenApiCustomizer customizer() {
        return openApi -> openApi.info(new Info().title("Customized API Title").version("1.0"));
    }

    public static class SpringDocDataRestFilter extends ClassFilter {
        @Override
        protected Class<?> getFilteredClass() {
            return SpringDocDataRestConfiguration.class;
        }
    }

    public static class SpringDocHateoasFilter extends ClassFilter {
        @Override
        protected Class<?> getFilteredClass() {
            return SpringDocHateoasConfiguration.class;
        }
    }

    private static abstract class ClassFilter implements TypeFilter {
        protected abstract Class<?> getFilteredClass();

        @Override
        public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
            String className = metadataReader.getClassMetadata().getClassName();
            String enclosingClassName = metadataReader.getClassMetadata().getEnclosingClassName();
            return
                className.equals(getFilteredClass().getCanonicalName())
                || (enclosingClassName!=null && enclosingClassName.equals(getFilteredClass().getCanonicalName()));
        }
    }
}