package training.cfg;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan(basePackages = { "training.dao" })
public class AppConfig4 {

	@Bean(autowire = Autowire.BY_NAME)
	public JdbcTemplate template() {
		return new JdbcTemplate();
	}

	@Bean(name = "dataSource")
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
