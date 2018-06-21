package training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import training.entity.Employee;
import training.entity.Laptop;
import training.util.HibernateUtil;

public class P09_SaveLaptopsAndEmployees {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			
			// 3 employees
			Employee e1 = new Employee("John", 2000.0);
			Employee e2 = new Employee("Jane", 3000.0);
			Employee e3 = new Employee("Smith", 1200.0);
			
			// 4 laptops
			Laptop lt1 = new Laptop("ASD1", "Lenovo", "CZR123", e1);
			e1.setLaptop(lt1);
			
			Laptop lt2 = new Laptop("FFF2", "Macbook", "MCB222", e3);
			e3.setLaptop(lt2);
			
			Laptop lt3 = new Laptop("FGH2", "Toshiba", "TSB212", null);
			Laptop lt4 = new Laptop("THM2", "Macbook", "MCB123", null);
			
			session.persist(e1);
			session.persist(e2);
			session.persist(e3);
			session.persist(lt3);
			session.persist(lt4);
			
			session.getTransaction().commit();
			session.close();
		} finally {
			factory.close();
		}
		
		System.out.println("Done");
	}
}
