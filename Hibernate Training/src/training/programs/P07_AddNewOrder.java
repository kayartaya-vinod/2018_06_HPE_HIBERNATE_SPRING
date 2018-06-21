package training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import training.entity.Customer;
import training.entity.LineItem;
import training.entity.Order;
import training.entity.Product;
import training.util.HibernateUtil;

public class P07_AddNewOrder {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			Session session = factory.openSession();
			
			Customer c1 = (Customer) session.get(Customer.class, 1);

			Product p1 = (Product) session.get(Product.class, 11);
			Product p2 = (Product) session.get(Product.class, 21);
			Product p3 = (Product) session.get(Product.class, 31);

			LineItem item1 = new LineItem(p1, 12, p1.getUnitPrice());
			LineItem item2 = new LineItem(p2, 5, p2.getUnitPrice());
			LineItem item3 = new LineItem(p3, 2, p3.getUnitPrice());
			
			
			Transaction tx = session.beginTransaction();

			Order o = new Order();
			o.addLineItem(item1);
			o.addLineItem(item2);
			o.addLineItem(item3);
			o.setCustomer(c1);

			session.persist(o);
			
			tx.commit();

		} finally {
			factory.close();
		}
	}

}
