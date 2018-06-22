package training.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import training.dao.DaoException;
import training.dao.ProductDao;
import training.entity.Product;

// A class becomes a component from spring's perspective when it is
// annotated with one of these: 
// Component, Repository, Service, Controller, RestController, Configuration
@Repository
public class ProductDaoJdbcImpl implements ProductDao {

	private String driver, url, username, password;

	@Autowired(required = false)
	private DataSource dataSource;

	public ProductDaoJdbcImpl() {
	}

	public ProductDaoJdbcImpl(String driver, String url, String username, String password) {
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	// a convenient constructor for dependency injection
	public ProductDaoJdbcImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// spring can inject that value for the member driver
	// via this writable property "driver"
	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// with this we can supply a data source
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private Connection openConnection() throws Exception {

		if (dataSource != null) {
			return dataSource.getConnection();
		}

		Class.forName(driver);
		return DriverManager.getConnection(url, username, password);
	}

	@Override
	public int count() throws DaoException {
		String sql = "select count(*) from products";

		try (Connection conn = openConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void addProduct(Product product) throws DaoException {
		throw new DaoException("Method not implemented");
	}

	@Override
	public Product getProduct(Integer id) throws DaoException {
		throw new DaoException("Method not implemented");
	}

	@Override
	public void updateProduct(Product product) throws DaoException {
		throw new DaoException("Method not implemented");
	}

	@Override
	public void deleteProduct(Integer id) throws DaoException {
		throw new DaoException("Method not implemented");
	}

	@Override
	public List<Product> getAll() throws DaoException {
		throw new DaoException("Method not implemented");
	}

	@Override
	public List<Product> getProductByPrice(Double min, Double max) throws DaoException {
		throw new DaoException("Method not implemented");
	}

	@Override
	public List<Product> getProductsByBrand(Integer brandId) throws DaoException {
		throw new DaoException("Method not implemented");
	}

	@Override
	public List<Product> getProductsByCategory(Integer categoryId) throws DaoException {
		throw new DaoException("Method not implemented");
	}

}
