package com.publication.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.publication.constants.GeneratePCN;
import com.publication.dao.PatentDAO;
import com.publication.database.ConnectionFactory;
import com.publication.model.Journal;
import com.publication.model.Patent;

public class PatentIMPL implements PatentDAO {

	@Override
	public boolean savePatent(Patent patent) {
		if (patent == null) {
		return false;
	}
	Connection connection = null;
	PreparedStatement ps = null;
	try {
		connection = ConnectionFactory.getConnection();
		ps = connection.prepareStatement(
				"insert into patent (faculy, deptt, title, nationality,country, applicationNo, applicationYear, applicationDate, patentYear, awardDate , publicationfilename, plagreportfilename,status, writtenBy, id) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1, patent.getFaculty());
		ps.setString(2, patent.getDeptt().toUpperCase());
		ps.setString(3, patent.getTitle());
		ps.setString(4, patent.getNationality());
		ps.setString(5,  patent.getCountry());
		ps.setString(6, patent.getApplicationNo());
		ps.setInt(7, patent.getApplicationYear());
		ps.setString(8, patent.getApplicationDate());
		ps.setInt(9, patent.getPatentYear());
		ps.setString(10, patent.getAwardDate());
		ps.setString(11, patent.getPublicationFileName());
		ps.setString(12, patent.getPlagReportFileName());
		
		ps.setInt(13, patent.getStatus());
		ps.setString(14, patent.getWrittenBy());
		String id;
		PreparedStatement ps1 = connection.prepareStatement("select id from patent");
		ResultSet rs = ps1.executeQuery();
		ArrayList<Integer> list = new ArrayList<>();

		if (!rs.next()) {
			id = "T0001";
		} else {
			rs.beforeFirst();
			while (rs.next()) {
				String result = rs.getString("id");
				list.add(Integer.parseInt(result.substring(1)));
			}
			int[] array = list.stream().mapToInt(i -> i).toArray();
			int sno = getMissing(array, array.length);
			id = String.format("T%04d", sno);
		}
		ps.setString(15, id);
		if (ps.executeUpdate() > 0) {
			return true;
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		ConnectionFactory.close(connection);
	}
	return false;
	}

	@Override
	public boolean updatePatent(Patent Patent) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Patent> getAllPatents() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patent getPatentByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String id) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("delete from patent where id=?");
			ps.setString(1, id);
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return false;
	}

	@Override
	public boolean action(String id, int status) {
		Patent patent = getPatentByID(id);
		if (null == patent) {
			return false;
		}
		Connection connection;
		PreparedStatement ps1;
		PreparedStatement ps2;
		ArrayList<Integer> list = new ArrayList<>();
		try {
			connection = ConnectionFactory.getConnection();
			ps1 = connection.prepareStatement(
					"select pcn from patent where pcn like \"" + patent.getDeptt().toUpperCase() + "____%T___\"");
			ResultSet rs = ps1.executeQuery();
			String pcn;
			if (!rs.next()) {
				pcn = GeneratePCN.generatePCN(patent.getDeptt().toUpperCase(), "T", 1);
			} else {
				rs.beforeFirst();
				while (rs.next()) {
					String result = rs.getString("pcn");
					list.add(Integer.parseInt(result.substring(8)));
				}
				int[] array = list.stream().mapToInt(i -> i).toArray();
				int sno = getMissing(array, array.length);
				pcn = GeneratePCN.generatePCN(patent.getDeptt().toUpperCase(), "T", sno);
			}
			ps2 = connection.prepareStatement("update patent set pcn=?, status=?, monthAssigned=? where id=?");
			if (status == 1) {
				ps2.setString(1, pcn.toUpperCase());
			} else if (status == 2) {
				ps2.setString(1, pcn.toUpperCase());
			}
			ps2.setInt(2, status);
			ps2.setDate(3, new Date(System.currentTimeMillis()));
			ps2.setString(4, id);
			if (ps2.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean reject(String id, int status, String message) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMissing(int[] a, int n) {
		int i;
		int total;
		total = (n + 1) * (n + 2) / 2;
		for (i = 1; i <=n; i++)
			total -= a[i-1];
		return total;
	}

	@Override
	public int notificationRejectedPatents(String id) {
		Connection connection = null;
		PreparedStatement statement;
		try{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement("select distinct count(*) as number from patent where status>0 and writtenby=?");
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				return rs.getInt("number");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(connection);
		}
		return 0;
	}

}
