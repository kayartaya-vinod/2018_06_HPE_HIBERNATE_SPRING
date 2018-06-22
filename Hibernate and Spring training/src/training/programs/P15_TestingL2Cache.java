package training.programs;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import training.entity.Product;
import training.util.HibernateUtil;

public class P15_TestingL2Cache {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		try {
			
			Session session1 = factory.openSession();
			Session session2 = factory.openSession();
			
			System.out.println(">>>> Pass 1");
			Product p1 = (Product) session1.get(Product.class, 11);
			System.out.println(">>>> Pass 2");
			System.out.println(p1.getName() + "--> Rs." + p1.getUnitPrice());

			session1.close();
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Press enter key to continue....");
			sc.nextLine();
			
			System.out.println(">>>> Pass 3");
			p1 = (Product) session2.get(Product.class, 11);
			System.out.println(">>>> Pass 4");
			System.out.println(p1.getName() + "--> Rs." + p1.getUnitPrice());
			session2.close();
			
			
		} finally {
			factory.close();
		}
			
		
		
		
	}
}
