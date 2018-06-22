package training.cfg;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import training.entity.Brand;
import training.entity.Customer;
import training.entity.LineItem;
import training.entity.Order;
import training.entity.Product;

@Configuration
@ComponentScan(basePackages = { "training.dao", "training.aspects" })
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class AppConfig5 {

	@Bean
	public PlatformTransactionManager txManager(SessionFactory factory) {
		return new HibernateTransactionManager(factory);
	}

	@Bean
	public HibernateTemplate ht(SessionFactory factory) {
		return new HibernateTemplate(factory);
	}

	// this bean is not an implementation of SessionFactory, but is
	// capable of manufacturing one. Spring calls lsfb().getObject() to do the same.
	@Bean
	public LocalSessionFactoryBean lsfb(DataSource dataSource) {
		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(dataSource);

		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

		sf.setHibernateProperties(props);

		sf.setAnnotatedClasses(Product.class, Brand.class, Order.class, LineItem.class, Customer.class);
		sf.setMappingResources("training/entity/category.hbm.xml");

		return sf;
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
