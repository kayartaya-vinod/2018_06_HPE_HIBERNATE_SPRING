package training.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import training.dao.impl.ProductDaoJdbcImpl;

@Configuration
public class AppConfig1 {

	@Bean
	public ProductDaoJdbcImpl dao() {
		return new ProductDaoJdbcImpl();
	}

}
