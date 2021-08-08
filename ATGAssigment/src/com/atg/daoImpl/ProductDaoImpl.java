package com.atg.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atg.beans.Product;
import com.atg.dao.ProductDao;

public class ProductDaoImpl implements ProductDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int addProduct(Product product) {
		return jdbcTemplate.update(
				"insert into products (CUSTID, PRODUCTNAME, DOMAIN, STARTDATE, DURATION) values(?,?,?,?,?)",
				new Object[] { product.getCustomerId(), product.getProductName(), product.getDomain(),
						product.getStartDate(), product.getDurationMonths() });
	}

	@Override
	public int deleteProduct(int productId) {
		return jdbcTemplate.update("delete from products where ID=?", new Object[] { productId });
	}

}
