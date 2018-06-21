package training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import training.entity.Order;
import training.entity.Product;
import training.util.HibernateUtil;

public class P05_GetProductById {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			Session session = factory.openSession();
			Product p1 = (Product) session.get(Product.class, 21);
			
			// dummy code to initiate route to db
			p1.getOrders().size();
			 
			session.close();
			
			System.out.println("Product name = " + p1.getName());
			System.out.println("Brand        = " + p1.getBrand().getName());
			System.out.println("Category     = " + p1.getCategory().getName());
			
			System.out.println("Following orders exist for this product:");
			for(Order o: p1.getOrders()) {
				System.out.println("Order id " + o.getId() + ", placed by " + 
						o.getCustomer().getName());
			}
			
			
		} finally {
			factory.close();
		}
	}
}
