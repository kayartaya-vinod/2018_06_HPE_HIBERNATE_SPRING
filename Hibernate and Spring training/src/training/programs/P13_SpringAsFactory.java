package training.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import training.cfg.AppConfig4;
import training.dao.DaoException;
import training.dao.ProductDao;

public class P13_SpringAsFactory {

	public static void main(String[] args) throws DaoException {
		// a variable representing spring container
		AnnotationConfigApplicationContext ctx;
		
		// a spring container created using AppConfig.java
		ctx = new AnnotationConfigApplicationContext(AppConfig4.class);
		// we are asking for a bean from spring, which can be avoided
		// by dependency injection
		ProductDao dao = ctx.getBean("productDaoJdbcTemplateImpl", ProductDao.class);
		int pc = dao.count();
		System.out.println("Total product count = " + pc);
		
		ctx.close();
		
	}
}
