package training.cfg;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "training.dao" })
public class AppConfig3 {

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
