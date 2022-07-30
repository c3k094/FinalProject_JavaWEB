package PETVET.bg.petvet.config;

import PETVET.bg.petvet.model.dto.ManipulationAddDTO;
import PETVET.bg.petvet.model.entity.ManipulationEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;


@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<ManipulationAddDTO, ManipulationEntity>() {
        @Override
            protected void configure() {
                skip(destination.setId(null));
        }
        });
        return modelMapper;
    }
}

