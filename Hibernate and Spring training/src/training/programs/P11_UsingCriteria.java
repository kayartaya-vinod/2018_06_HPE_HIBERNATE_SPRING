package training.programs;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import training.entity.Brand;
import training.entity.Product;
import training.util.HibernateUtil;

@SuppressWarnings("unchecked")
public class P11_UsingCriteria {
	private static Session session;

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			session = factory.openSession();

			// printAllBrands();
			// printProductsByPrice(10.0, 40.0); // min=10, max=20
			// printProductsByPage(5); // pageNum = 5,
			// printProductNamesAndPrices(); // projection
			printProductCountByBrand(); // group by

			session.close();
		} finally {
			factory.close();
		}
	}

	static void printProductCountByBrand() {
		List<Object[]> list = session.createCriteria(Product.class)
				.createAlias("brand", "b")
				.setProjection(
					Projections.projectionList()
						.add(Projections.groupProperty("b.name"))
						.add(Projections.rowCount())
				).list();
		
		for(Object[] data: list) {
			System.out.printf("%s --> %d\n", data[0], data[1]);
		}
	}

	static void printProductNamesAndPrices() {
		Criteria cr = session.createCriteria(Product.class);
		
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("name"));
		projList.add(Projections.property("unitPrice"));
		
		cr.setProjection(projList);
		List<Object[]> list = cr.list();
		for(Object[] data: list) {
			System.out.printf("%s --> Rs.%.2f\n", data[0], data[1]);
		}
	}

	static void printProductsByPage(int pageNum) {
		int pageSize = 10;
		int offset = (pageNum-1) * pageSize;
		Criteria cr = session.createCriteria(Product.class);
		cr.setFirstResult(offset);
		cr.setMaxResults(pageSize);
		
		List<Product> list = cr.list();
		for (Product p : list) {
			System.out.printf("%d %s (%s) --> Rs.%.2f\n",
					p.getId(),
					p.getName(), p.getCategory().getName(), p.getUnitPrice());
		}
	}

	static void printProductsByPrice(Double min, Double max) {
		Criteria cr = session.createCriteria(Product.class).createAlias("category", "c");

		// cr.add(Restrictions.between("unitPrice", min, max));

		cr.add(Restrictions.ge("unitPrice", min));
		cr.add(Restrictions.le("unitPrice", max));
		cr.add(Restrictions.eq("c.name", "Fruits"));
		cr.addOrder(Order.desc("unitPrice"));

		List<Product> list = cr.list();
		for (Product p : list) {
			System.out.printf("%s (%s) --> Rs.%.2f\n", p.getName(), p.getCategory().getName(), p.getUnitPrice());
		}
	}

	static void printAllBrands() {
		Criteria cr = session.createCriteria(Brand.class);
		List<Brand> list = cr.list();
		for (Brand b : list) {
			System.out.println(b);
		}
	}
}
