package model.dao;

import model.dao.impl.SellerDAO_JDBC;

public class DaoFactory {
	
	public static SellerDAO createSellerDao() {
		return new SellerDAO_JDBC();
	}

}
