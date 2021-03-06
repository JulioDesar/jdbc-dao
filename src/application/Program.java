package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDAO sellerDao = DaoFactory.createSellerDao();
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);

		Department dp = new Department(2, null);
		
		/*List<Seller> list = sellerDao.findByDepartment(dp);

		for (Seller sl : list) {
			System.out.println(sl);
		}*/

		List<Seller> listAll = sellerDao.findAll();

		for (Seller sl : listAll) {
			System.out.println(sl);
		}
		
		Seller newSeller = new Seller(null, "Julio", "cesarj598@gmail.com", new Date(), 12000.0, dp);
		sellerDao.insert(newSeller);
		System.out.println("New Seller id: " + newSeller.getId());

		sellerDao.deleteById(8);
		System.out.println("Deleted!");
	}

}
