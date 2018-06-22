package training.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import training.dao.DaoException;
import training.dao.ProductDao;
import training.entity.Brand;
import training.entity.Category;
import training.entity.Product;

@SuppressWarnings("unchecked")
@Repository("dao")
public class ProductDaoHibernateTemplateImpl implements ProductDao {

	@Autowired(required = false)
	private HibernateTemplate template;

	@Override
	public void addProduct(Product product) throws DaoException {
		template.persist(product);
	}

	@Override
	public Product getProduct(Integer id) throws DaoException {
		return template.get(Product.class, id);
	}

	@Override
	public void updateProduct(Product product) throws DaoException {
		template.merge(product);
	}

	@Override
	public void deleteProduct(Integer id) throws DaoException {
		Product p = getProduct(id);
		if (p == null) {
			throw new DaoException("Invalid id " + id);
		}
		template.delete(p);
	}

	@Override
	public List<Product> getAll() throws DaoException {
		return (List<Product>) template.find("from Product");
	}

	@Override
	public List<Product> getProductByPrice(Double min, Double max) throws DaoException {
		DetachedCriteria crit = DetachedCriteria.forClass(Product.class);
		crit.add(Restrictions.between("unitPrice", min, max));
		return (List<Product>) template.findByCriteria(crit);
	}

	@Override
	public List<Product> getProductsByBrand(Integer brandId) throws DaoException {
		Brand b = new Brand();
		b.setId(brandId);
		Product p = new Product();
		p.setBrand(b); // example object

		return template.findByExample(p);
	}

	@Override
	public List<Product> getProductsByCategory(Integer categoryId) throws DaoException {
		Category c = new Category();
		c.setId(categoryId);
		Product p = new Product();
		p.setCategory(c); // example object

		return template.findByExample(p);
	}

	@Override
	public int count() throws DaoException {
		String sql = "select count(*) from products";
		Session session = template.getSessionFactory().openSession();
		SQLQuery qry = session.createSQLQuery(sql);

		int count = (int) qry.uniqueResult();
		session.close();

		return count;
	}

}
