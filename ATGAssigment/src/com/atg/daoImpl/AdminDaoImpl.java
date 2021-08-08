package com.atg.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atg.beans.Customer;
import com.atg.dao.AdminDao;

public class AdminDaoImpl implements AdminDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int addCustomer(Customer customer) {
		return jdbcTemplate.update("insert into customers (CUSTOMERNAME) values (?)", new Object[] { customer.getCustomerName() });
	}

}
