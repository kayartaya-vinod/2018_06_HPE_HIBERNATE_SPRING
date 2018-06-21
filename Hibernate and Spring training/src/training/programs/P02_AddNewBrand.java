package training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import training.entity.Brand;
import training.util.HibernateUtil;

public class P02_AddNewBrand {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			// transient object
			Brand b1 = new Brand("HPE");
			
			Session session = factory.openSession();
			
			Transaction tx = session.beginTransaction();
			try {
				session.persist(b1); // also save(b1) can be used
				// b1 now is a persistent object
				tx.commit();
				System.out.println("New brand added with id: " + b1.getId());
			} catch (Exception e) {
				tx.rollback();
				System.out.println("There was an error: " + e.getMessage());
			}
			
			session.close();
			// b1 now is considered as a detached object
		} finally {
			factory.close();
		}
	}
}
