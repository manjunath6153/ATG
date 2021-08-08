package com.atg.dao;

import com.atg.beans.Product;

public interface ProductDao {
	int addProduct(Product product);
	// int updateProduct(Product product);
	int deleteProduct(Product product);
}
