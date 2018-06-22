package training.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import training.dao.impl.ProductDaoJdbcImpl;

@Configuration
public class AppConfig1 {

	@Scope("singleton")
	@Bean
	public ProductDaoJdbcImpl dao() {
		String driver, url, username, password;
		driver = "org.h2.Driver";
		url = "jdbc:h2:tcp://localhost/~/hpe201806";
		username = "sa";
		password = "";
		return new ProductDaoJdbcImpl(driver, url, username, password);
	}

}
