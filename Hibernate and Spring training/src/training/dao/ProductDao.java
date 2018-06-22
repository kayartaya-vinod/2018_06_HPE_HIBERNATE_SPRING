package training.dao;

import java.util.List;

import training.entity.Product;

public interface ProductDao {

	// CRUD OPERATIONS
	public void addProduct(Product product) throws DaoException;

	public Product getProduct(Integer id) throws DaoException;

	public void updateProduct(Product product) throws DaoException;

	public void deleteProduct(Integer id) throws DaoException;

	// QUERIES

	public List<Product> getAll() throws DaoException;

	public List<Product> getProductByPrice(Double min, Double max) throws DaoException;

	public List<Product> getProductsByBrand(Integer brandId) throws DaoException;

	public List<Product> getProductsByCategory(Integer categoryId) throws DaoException;

	public int count() throws DaoException;

}
