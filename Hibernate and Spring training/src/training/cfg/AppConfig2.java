package training.cfg;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import training.dao.ProductDao;
import training.dao.impl.ProductDaoJdbcImpl;

@Configuration
public class AppConfig2 {

	// dependency injection method 1:
	// note that call to the dbcp() does not pass control to the function.
	// Instead, it calls the dbcp() from the proxy which returns the object 
	// maintained by spring.
	@Bean(name="dao1")
	public ProductDao dao() {
		ProductDaoJdbcImpl dao = new ProductDaoJdbcImpl();
		dao.setDataSource(dbcp());
		return dao;
	}
	
	// dependency injection method 2:
	// since spring is the one who calls @Bean functions, we can specify our needs
	// Spring attempts to supply instances of the needed types
	@Bean(name="dao2")
	public ProductDao dao(DataSource dbcp) {
		return new ProductDaoJdbcImpl(dbcp);
	}

	@Bean
	public DataSource dbcp() {

		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("org.h2.Driver");
		bds.setUrl("jdbc:h2:tcp://localhost/~/hpe201806");
		bds.setUsername("sa");
		bds.setPassword("");
		
		bds.setInitialSize(5);
		bds.setMaxTotal(100);
		bds.setMaxIdle(50);
		bds.setMinIdle(5);
		return bds;
	}
}







