package training.programs;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import training.entity.Brand;
import training.entity.Product;
import training.util.HibernateUtil;

@SuppressWarnings("unchecked")
public class P10_HQL {
	private static Session session;

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			session = factory.openSession();

			// printAllBrands();
			// printProductsByPrice(10.0, 20.0); // min=10, max=20
			printProductsByPage(5); // pageNum = 5,

			session.close();
		} finally {
			factory.close();
		}
	}

	static void printProductsByPage(int pageNum) {
		int pageSize = 10;
		int offset = (pageNum - 1) * pageSize;
		Query qry = session.createQuery("from Product");
		qry.setFirstResult(offset);
		qry.setMaxResults(pageSize);

		List<Product> list = qry.list();
		for(Product p: list) {
			System.out.printf("%2d %-40s %10.2f\n", 
					p.getId(), p.getName(), p.getUnitPrice());
		}
	}

	static void printProductsByPrice(double min, double max) {
		String hql = "FROM Product WHERE unitPrice BETWEEN :min_price AND :max_price";
		Query qry = session.createQuery(hql);
		qry.setParameter("min_price", min);
		qry.setDouble("max_price", max);
		List<Product> list = qry.list();

		System.out.printf("There are %d products between Rs.%.2f and Rs.%.2f\n", list.size(), min, max);
		for (Product p : list) {
			System.out.printf("%s --> %.2f\n", p.getName(), p.getUnitPrice());
		}
	}

	static void printAllBrands() {
		String hql = "from Brand";
		Query qry = session.createQuery(hql);
		List<Brand> list = qry.list(); // hql to sql generation and execution is done here

		for (Brand b : list) {
			System.out.println(b);
		}

	}
}
