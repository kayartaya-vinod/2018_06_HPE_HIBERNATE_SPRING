package training.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import training.dao.DaoException;
import training.dao.ProductDao;
import training.entity.Brand;
import training.entity.Category;
import training.entity.Product;

@Repository
public class ProductDaoJdbcTemplateImpl implements ProductDao {

	@Autowired(required = false)
	private JdbcTemplate template;

	RowMapper<Product> prm = (rs, index) -> {
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setDescription(rs.getString("description"));
		product.setQuantityPerUnit(rs.getString("quantity_per_unit"));
		product.setPicture(rs.getString("picture"));
		product.setDiscount(rs.getDouble("discount"));
		product.setUnitPrice(rs.getDouble("unit_price"));
		Category c1 = new Category();
		c1.setId(rs.getInt("category_id"));
		product.setCategory(c1);
		Brand b1 = new Brand();
		b1.setId(rs.getInt("brand_id"));
		product.setBrand(b1);
		return product;
	};

	@Override
	public void addProduct(Product p) throws DaoException {
		String sql = "insert into products (name, description, category_id, "
				+ "brand_id, unit_price, quantity_per_unit, picture, discount) " + "value (?,?,?,?,?,?,?,?)";

		template.update(sql, p.getName(), p.getDescription(), p.getCategory().getId(), p.getBrand().getId(),
				p.getUnitPrice(), p.getQuantityPerUnit(), p.getPicture(), p.getDiscount());
	}

	@Override
	public Product getProduct(Integer id) throws DaoException {
		String sql = "select * from products where id = ?";
		return template.queryForObject(sql, prm, id);
	}

	@Override
	public void updateProduct(Product product) throws DaoException {
		String sql = "update products set name=?, description = ?, " + "category_id=?, brand_id=?, unit_price=?, "
				+ "quantity_per_unit=?, picture=?, discount=? " + "where id=?";

		template.update(sql, product.getName(), product.getDescription(), product.getCategory().getId(),
				product.getBrand().getId(), product.getUnitPrice(), product.getQuantityPerUnit(), product.getPicture(),
				product.getDiscount(), product.getId());
	}

	@Override
	public void deleteProduct(Integer id) throws DaoException {
		template.update("delete from products where id = ?", id);
	}

	@Override
	public List<Product> getAll() throws DaoException {
		return template.query("select * from products", prm);
	}

	@Override
	public List<Product> getProductByPrice(Double min, Double max) throws DaoException {
		return template.query(
				"select * from products where unit_price between ? and ?", 
				prm, min, max);
	}

	@Override
	public List<Product> getProductsByBrand(Integer brandId) throws DaoException {
		return template.query("select * from products where brand_id = ?", prm, brandId);
	}

	@Override
	public List<Product> getProductsByCategory(Integer categoryId) throws DaoException {
		return template.query("select * from products where category_id = ?", prm, categoryId);
	}

	@Override
	public int count() throws DaoException {
		return template.queryForObject("select count(*) from products", Integer.class);
	}

}
