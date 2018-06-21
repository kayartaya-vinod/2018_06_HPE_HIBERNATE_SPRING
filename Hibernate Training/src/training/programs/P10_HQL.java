package training.programs;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import training.entity.Brand;
import training.util.HibernateUtil;

@SuppressWarnings("unchecked")
public class P10_HQL {
	private static Session session;

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			session = factory.openSession();
			
			printAllBrands();

			session.close();
		} finally {
			factory.close();
		}
	}

	static void printAllBrands() {
		String hql = "from Brand";
		Query qry = session.createQuery(hql);
		List<Brand> list = qry.list(); // hql to sql generation and execution is done here
		
		for(Brand b: list) {
			System.out.println(b);
		}
		
	}
}







