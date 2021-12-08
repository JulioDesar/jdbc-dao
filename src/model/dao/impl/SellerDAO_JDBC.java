package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class SellerDAO_JDBC implements SellerDAO {

	private Connection conn;

	public SellerDAO_JDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller sl) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller sl) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");

			st.setInt(1, id);

			rs = st.executeQuery();
			if (rs.next()) {
				Department dp = instantiateDepartment(rs);

				Seller sl = instantiateSeller(rs, dp);

				return sl;
			}

			return null;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);

		}

	}

	private Seller instantiateSeller(ResultSet rs, Department dp) throws SQLException {
		Seller sl = new Seller();
		sl.setId(rs.getInt("Id"));
		sl.setName(rs.getString("Name"));
		sl.setEmail(rs.getString("Email"));
		sl.setBirthDate(rs.getDate("BirthDate"));
		sl.setBaseSalary(rs.getDouble("BaseSalary"));
		sl.setDepartment(dp);

		return sl;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {

		Department dp = new Department();
		dp.setId(rs.getInt("DepartmentId"));
		dp.setName(rs.getString("DepName"));

		return dp;
	}

	@Override
	public List<Seller> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name");

			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<Integer, Department>();
			
			while (rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller sl = instantiateSeller(rs, dep);

				list.add(sl);
			}

			return list;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	

	@Override
	public List<Seller> findByDepartment(Department dp) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					  "SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name");

			st.setInt(1, dp.getId());

			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<Integer, Department>();
			
			while (rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller sl = instantiateSeller(rs, dep);

				list.add(sl);
			}

			return list;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
