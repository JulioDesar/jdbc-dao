package application;

import model.dao.DaoFactory;
import model.dao.SellerDAO;

public class Program {

	public static void main(String[] args) {
		
		SellerDAO seller = DaoFactory.createSellerDao();
		
	}

}
