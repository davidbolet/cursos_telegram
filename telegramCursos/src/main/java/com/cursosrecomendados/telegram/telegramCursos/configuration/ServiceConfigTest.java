package com.cursosrecomendados.telegram.telegramCursos.configuration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableJpaRepositories(basePackages = {"com.cursosrecomendados.telegram.telegramCursos.repository"})
@EntityScan( basePackages = {"com.cursosrecomendados.telegram.telegramCursos.model"} )
@ComponentScan(basePackages = {"com.cursosrecomendados.telegram.telegramCursos"})
public class ServiceConfigTest {
	
}