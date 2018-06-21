package training.dao.impl;

import training.dao.DaoException;
import training.dao.ProductDao;

public class ProductDaoJdbcImpl implements ProductDao {

	@Override
	public int count() throws DaoException {
		return 100;
	}

}
