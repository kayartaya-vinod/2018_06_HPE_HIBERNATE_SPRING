package training.programs;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import training.entity.Brand;
import training.entity.Customer;
import training.entity.Order;
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
			// printProductsByPage(5); // pageNum = 5,
			// printProductNames(); // projection
			// printProductNamesAndPrices();
			// printProductAndCategoryNames(); // include join
			// printProductCountByBrand(); // group by
			// increaseProductPrice(2.0); // increase by Rs.2
			// printCustomerByEmail("vinod@vinod.co");
			// printProductsByNameLike("c%");
			// printOrderTotals();
			printOrderDetailsWhereTotalLessThan(500.0);
			
			session.close();
		} finally {
			factory.close();
		}
	}

	static void printOrderDetailsWhereTotalLessThan(double orderTotal) {
		String sql = "SELECT * FROM orders where id in ("
				+ "SELECT o.id from orders o left join LINE_ITEMS li "
				+ "on o.id = li.order_id group by o.id "
				+ "having ifnull(sum(quantity*unit_price), 0) < :ORDER_TOTAL)";
		
		Query qry = session.createSQLQuery(sql).addEntity(Order.class);
		qry.setParameter("ORDER_TOTAL", orderTotal);
		List<Order> orders = qry.list();
		for(Order o: orders) {
			System.out.printf("%d --> %s\n", o.getId(), o.getCustomer().getName());
		}
	}

	static void printOrderTotals() {
		String sql = "SELECT o.id, ifnull(sum(quantity*unit_price), 0) order_total "
				+ "FROM orders o left join LINE_ITEMS li "
				+ "on o.id = li.order_id group by o.id";
		
		Query qry = session.createSQLQuery(sql);
		List<Object[]> list = qry.list();

		for (Object[] data : list) {
			System.out.println(data[0] + " --> Rs." + data[1]);
		}
	}

	static void printProductsByNameLike(String token) {
		String hql = "select name from Product where lower(name) like lower(:TOKEN)";
		Query qry = session.createQuery(hql);
		qry.setString("TOKEN", token );
		List<String> list = qry.list();
		for (String name : list) {
			System.out.println(name);
		}
	}

	static void printCustomerByEmail(String email) {
		String hql = "from Customer where email = :EMAIL";
		Query qry = session.createQuery(hql);
		qry.setString("EMAIL", email);
		Customer c1 = (Customer) qry.uniqueResult();
		System.out.println(c1);
	}

	static void increaseProductPrice(Double incAmount) {
		String hql = "update Product set unitPrice = unitPrice + ?";
		Query qry = session.createQuery(hql);
		qry.setParameter(0, incAmount);
		int count = qry.executeUpdate();
		System.out.printf("Updated %d products\n", count);
		session.beginTransaction().commit();
	}

	static void printProductCountByBrand() {
		String hql = "select p.brand.name, count(p) "
				+ "from Product p group by p.brand "
				+ "having count(p)>15";
		
		Query qry = session.createQuery(hql);
		List<Object[]> list = qry.list();

		for (Object[] data : list) {
			System.out.println(data[0] + " --> " + data[1]);
		}
	}

	static void printProductAndCategoryNames() {
		// String hql = "select name, category.name from Product";
		String hql = "select p.name, c.name from Category c join c.products as p";
		Query qry = session.createQuery(hql);

		List<Object[]> list = qry.list();

		for (Object[] data : list) {
			System.out.println(data[0] + " --> " + data[1]);
		}

	}

	static void printProductNamesAndPrices() {
		String hql = "select name, unitPrice from Product";
		Query qry = session.createQuery(hql);

		List<Object[]> list = qry.list();

		for (Object[] data : list) {
			System.out.println(data[0] + " --> Rs." + data[1]);
		}
	}

	static void printProductNames() {
		String hql = "select name from Product";
		Query qry = session.createQuery(hql);
		List<String> list = qry.list();
		for (String name : list) {
			System.out.println(name);
		}
	}

	static void printProductsByPage(int pageNum) {
		int pageSize = 10;
		int offset = (pageNum - 1) * pageSize;
		Query qry = session.createQuery("from Product");
		qry.setFirstResult(offset);
		qry.setMaxResults(pageSize);

		List<Product> list = qry.list();
		for (Product p : list) {
			System.out.printf("%2d %-40s %10.2f\n", p.getId(), p.getName(), p.getUnitPrice());
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
