package training.programs;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import training.cfg.AppConfig5;
import training.dao.DaoException;
import training.dao.ProductDao;
import training.entity.Product;

public class P14_TestingProductDao {

	public static void main(String[] args) throws DaoException {
		
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppConfig5.class);
		
		ProductDao dao = ctx.getBean("dao", ProductDao.class);
		System.out.println("dao is an instanceof " + dao.getClass());
		
		Double min = 10.0, max=20.0;
		List<Product> list = dao.getProductByPrice(min, max);
		System.out.println("There are " + list.size()+" products between Rs.10 and 20");
		
		Product p = dao.getProduct(12);
		System.out.println(p.getName() + " --> Rs." + p.getUnitPrice());
		
		p.setUnitPrice(p.getUnitPrice() + 1);
		dao.updateProduct(p);
		p = dao.getProduct(12);
		System.out.println("After updating...");
		System.out.println(p.getName() + " --> Rs." + p.getUnitPrice());
		
		
		ctx.close();
	}
}








