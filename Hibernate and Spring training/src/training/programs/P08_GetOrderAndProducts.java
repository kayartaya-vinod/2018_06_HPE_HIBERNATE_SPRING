package training.programs;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import training.entity.Order;
import training.entity.Product;
import training.util.HibernateUtil;

public class P08_GetOrderAndProducts {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			Session session = factory.openSession();
			
			Order or1 = (Order) session.get(Order.class, 2);
			System.out.println("Order placed by : " + or1.getCustomer().getName());
			System.out.println("Order placed on : " + or1.getOrderDate());
			System.out.println("Order status is : " + or1.getStatus());
			
			System.out.println("Products in this order are: ");
			for(Product p: or1.getProducts()) {
				System.out.println(p.getName() + " (Rs." + p.getUnitPrice() + ")");
			}
			session.close();
		} finally {
			factory.close();
		}
	}
}
