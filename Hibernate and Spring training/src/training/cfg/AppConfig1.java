package training.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import training.dao.impl.ProductDaoJdbcImpl;

@Configuration
public class AppConfig1 {
	
	public AppConfig1() {
		System.out.println("AppConfig1 instantiated!");
	}
	
	@Bean
	public Integer someNum() {
		System.out.println("AppConfig1.someNum() called");
		return 100;
	}

	@Bean
	public ProductDaoJdbcImpl dao() {
		System.out.println("AppConfig1.dao() called");
		this.someNum();
		this.someNum();
		this.someNum();
		this.someNum();
		this.someNum();
		return new ProductDaoJdbcImpl();
	}
	
}
