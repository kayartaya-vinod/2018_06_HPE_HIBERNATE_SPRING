package training.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import training.entity.Brand;
import training.entity.Customer;
import training.entity.Employee;
import training.entity.Laptop;
import training.entity.LineItem;
import training.entity.Order;
import training.entity.Product;

public class HibernateUtil {

	private static SessionFactory factory = null;

	public static SessionFactory getSessionFactory() {
		if (factory != null) {
			return factory;
		}

		// an empty configuration object
		Configuration cfg = new Configuration();
		// read from hibernate.cfg.xml
		cfg.configure();
		cfg.addAnnotatedClass(Brand.class);
		cfg.addAnnotatedClass(Product.class);
		cfg.addAnnotatedClass(Customer.class);
		cfg.addAnnotatedClass(Order.class);
		cfg.addAnnotatedClass(LineItem.class);
		cfg.addAnnotatedClass(Employee.class);
		cfg.addAnnotatedClass(Laptop.class);
		
		cfg.addFile("src/training/entity/category.hbm.xml");

		StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder()
				.applySettings(cfg.getProperties());

		factory = cfg.buildSessionFactory(registry.build());

		return factory;
	}

}
