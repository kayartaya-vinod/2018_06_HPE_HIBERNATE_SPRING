package training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import training.entity.Customer;
import training.util.HibernateUtil;

public class P06_GetCustomer {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			Session session = factory.openSession();
			Customer c1 = (Customer) session.get(Customer.class, 1);
			session.close();
			
			System.out.println("Name = " + c1.getName());
			System.out.println("City = " + c1.getContactDetails().getCity());
			
		} finally {
			factory.close();
		}
	}
}
