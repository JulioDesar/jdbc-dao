package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SellerDAO {
	
	void insert (Seller sl);
	void update (Seller sl);
	void deleteById (Integer id);
	Seller findById(Integer id);
	List<Seller> findAll();

}
