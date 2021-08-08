package com.atg.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.atg.beans.Product;
import com.atg.dao.EmailSender;
import com.atg.dao.NotificationValidator;

public class NotificationValidatorImpl implements NotificationValidator {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private EmailSender emailSender;

	@Override
	public void validateAllProducts() {

		// List<Product> productsList = CUSTID, PRODUCTNAME, DOMAIN, STARTDATE,
		// DURATION
		String sql = "select * from product";
		List<Product> products = jdbcTemplate.query(sql, new RowMapper<Product>() {

			public Product mapRow(ResultSet rs, int rowNumber) throws SQLException {
				Product product = new Product();
				product.setCustomerId(rs.getInt("CUSTID"));
				product.setDomain(rs.getString("DOMAIN"));
				product.setProductName(rs.getString("PRODUCTNAME"));
				product.setStartDate(rs.getString("STARTDATE"));
				product.setDurationMonths(rs.getInt("DURATION"));
				return product;
			}
		});

		for (Product product : products) {
			if (product.getProductName().equalsIgnoreCase("domain")) {
				if (validateDomainProduct(product)) {
					emailSender.sendEmail(getEmail(product.getCustomerId()), "Expiry email");
				}
			} else if (product.getProductName().equalsIgnoreCase("hosting")) {
				// will check the dates similar to above code and send email
			} else {
				// will check the dates similar to above code and send email for
				// private pdomain as well
			}

		}
	}

	private boolean validateHostingProduct() {
		return true;
	}

	private boolean validateDomainProduct(Product product) {

		LocalDate expiryDate = LocalDate.parse(product.getStartDate()).plusMonths(product.getDurationMonths());

		LocalDate today = LocalDateTime.now().toLocalDate();

		if (today.plusDays(2).compareTo(expiryDate) == 0) {
			return true;
		} else {
			return false;
		}
	}

	private String getEmail(int custId) {
		return "email ID";
	}
}
