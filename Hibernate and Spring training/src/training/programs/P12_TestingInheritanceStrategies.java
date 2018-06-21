package training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import training.entity.Person;
import training.entity.Professor;
import training.entity.Student;
import training.util.HibernateUtil;

public class P12_TestingInheritanceStrategies {

	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			Session session = factory.openSession();
			
			session.beginTransaction();
			
			Person p1 = new Student(1, "John", "Dallas", "Java");
			Person p2 = new Professor(2, "Martin", "Chicago", "Physics", "martin@example.com");
			
			session.persist(p1);
			session.persist(p2);
			
			session.getTransaction().commit();
			
			session.close();
		} finally {
			factory.close();
		}
		
	}
}
