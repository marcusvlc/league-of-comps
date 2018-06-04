package com.composicao.comps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.composicao.comps.controllers.FiltroControllers;

@SpringBootApplication
public class CompsApplication {
	
	@Bean
	public FilterRegistrationBean getFiltro() {
		FilterRegistrationBean filtro = new FilterRegistrationBean();
		filtro.setFilter(new FiltroControllers());
		filtro.addUrlPatterns("/api/*");
		return filtro;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CompsApplication.class, args);
	}
}
