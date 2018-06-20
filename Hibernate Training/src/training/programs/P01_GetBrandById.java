package training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import training.entity.Brand;
import training.util.HibernateUtil;

public class P01_GetBrandById {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			Session session = factory.openSession();
			Brand b1 = (Brand) session.load(Brand.class, 2); // id = 2
			session.close();
			
			System.out.println("b1 is an instanceof " + b1.getClass());

			System.out.println("Name = " + b1.getName());
			
			
		} finally {
			factory.close();
		}

	}
}
