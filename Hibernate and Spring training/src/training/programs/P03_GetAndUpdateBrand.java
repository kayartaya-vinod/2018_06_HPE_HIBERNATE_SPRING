package training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import training.entity.Brand;
import training.util.HibernateUtil;

public class P03_GetAndUpdateBrand {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			Session session = factory.openSession();
			Brand b1 = (Brand) session.get(Brand.class, 4);
			// b1 now is a persistent object

			// b1.setName("HPE");
			// b1.setName("HPE limited");
			//
			// session.beginTransaction().commit();
			session.close();

			// b1 now is detached object
			b1.setName("HPE Limited Bangalore");

			// new session
			session = factory.openSession();
			session.merge(b1); // b1 now is a persistent object
			session.beginTransaction().commit();
			session.close();

		} finally {
			factory.close();
		}
	}
}
