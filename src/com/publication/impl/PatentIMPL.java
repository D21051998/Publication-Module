package com.publication.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publication.constants.GeneratePCN;
import com.publication.dao.PatentDAO;
import com.publication.database.ConnectionFactory;
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
					"insert into patent (faculty, deptt, title, nationality,country, applicationNo, applicationYear, applicationDate, patentYear, awardDate , publicationfilename, plagreportfilename,status, writtenBy, id, patentNo) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, patent.getFaculty());
			ps.setString(2, patent.getDeptt().toUpperCase());
			ps.setString(3, patent.getTitle());
			ps.setString(4, patent.getNationality());
			ps.setString(5, patent.getCountry());
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
			ps.setInt(16, patent.getPatentNo());
			
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
	public boolean updatePatent(Patent patent) {
		if (patent == null) {
			return false;
		}
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(
					"update patent set faculty=?, deptt=?, title=?, nationality=?,country=?, applicationNo=?, applicationYear=?, applicationDate=?, patentYear=?, awardDate=? , publicationfilename=?, plagreportfilename=?,status=?, writtenBy=?,patentNo=? where id=?");
			ps.setString(1, patent.getFaculty());
			ps.setString(2, patent.getDeptt().toUpperCase());
			ps.setString(3, patent.getTitle());
			ps.setString(4, patent.getNationality());
			ps.setString(5, patent.getCountry());
			ps.setString(6, patent.getApplicationNo());
			ps.setInt(7, patent.getApplicationYear());
			ps.setString(8, patent.getApplicationDate());
			ps.setInt(9, patent.getPatentYear());
			ps.setString(10, patent.getAwardDate());
			ps.setString(11, patent.getPublicationFileName());
			ps.setString(12, patent.getPlagReportFileName());
			ps.setInt(13, patent.getStatus());
			ps.setString(14, patent.getWrittenBy());
			ps.setInt(15, patent.getPatentNo());
			ps.setString(16, patent.getId());
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
	public List<Patent> getAllPatents() throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		List<Patent> list = new ArrayList<>();
		try{
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select * from patent");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Patent patent = new Patent();
				patent.setId(rs.getString("id"));
				patent.setPcn(rs.getString("pcn"));
				patent.setFaculty(rs.getString("faculty"));
				patent.setDeptt(rs.getString("deptt"));
				patent.setTitle(rs.getString("title"));
				patent.setNationality(rs.getString("nationality"));
				patent.setCountry(rs.getString("country"));
				patent.setApplicationNo(rs.getString("applicationNo"));
				patent.setApplicationYear(rs.getInt("applicationYear"));
				patent.setApplicationDate(rs.getString("applicationDate"));
				patent.setPatentYear(rs.getInt("patentYear"));
				patent.setAwardDate(rs.getString("awardDate"));
				patent.setMonthAssigned(rs.getString("monthAssigned"));
				patent.setPublicationFileName(rs.getString("publicationFileName"));
				patent.setPlagReportFileName(rs.getString("plagReportFileName"));
				patent.setStatus(rs.getInt("status"));
				patent.setWrittenBy(rs.getString("writtenBy"));
				patent.setPatentNo(rs.getInt("patentNo"));
				list.add(patent);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection);
		}
		return list;
	}

	@Override
	public Patent getPatentByID(String id) {
		Connection connection = null;
		PreparedStatement ps = null;
		Patent patent = null;
		try{
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select * from patent where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				patent = new Patent();
				patent.setId(rs.getString("id"));
				patent.setPcn(rs.getString("pcn"));
				patent.setFaculty(rs.getString("faculty"));
				patent.setDeptt(rs.getString("deptt"));
				patent.setTitle(rs.getString("title"));
				patent.setNationality(rs.getString("nationality"));
				patent.setCountry(rs.getString("country"));
				patent.setApplicationNo(rs.getString("applicationNo"));
				patent.setApplicationYear(rs.getInt("applicationYear"));
				patent.setApplicationDate(rs.getString("applicationDate"));
				patent.setPatentYear(rs.getInt("patentYear"));
				patent.setAwardDate(rs.getString("awardDate"));
				patent.setMonthAssigned(rs.getString("monthAssigned"));
				patent.setPublicationFileName(rs.getString("publicationFileName"));
				patent.setPlagReportFileName(rs.getString("plagReportFileName"));
				patent.setStatus(rs.getInt("status"));
				patent.setWrittenBy(rs.getString("writtenBy"));
				patent.setPatentNo(rs.getInt("patentNo"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection);
		}
		return patent;
	}

	@Override
	public boolean delete(String id) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("delete from patent where id=?");
			ps.setString(1, id);
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
		Patent patent = getPatentByID(id);
		if (null == patent) {
			return false;
		}
		Connection connection = null;
		PreparedStatement ps1;
		PreparedStatement ps;
		try {
			connection = ConnectionFactory.getConnection();
			ps1 = connection.prepareStatement("update patent set status=?, pcn=?, monthAssigned=? where id=?");
			ps = connection.prepareStatement(
					"insert into rej_patent (faculty, deptt, title, nationality,country, applicationNo, applicationYear, applicationDate, patentYear, awardDate , publicationfilename, plagreportfilename,status, writtenBy, id,message, patentNo) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, patent.getFaculty());
			ps.setString(2, patent.getDeptt().toUpperCase());
			ps.setString(3, patent.getTitle());
			ps.setString(4, patent.getNationality());
			ps.setString(5, patent.getCountry());
			ps.setString(6, patent.getApplicationNo());
			ps.setInt(7, patent.getApplicationYear());
			ps.setString(8, patent.getApplicationDate());
			ps.setInt(9, patent.getPatentYear());
			ps.setString(10, patent.getAwardDate());
			ps.setString(11, patent.getPublicationFileName());
			ps.setString(12, patent.getPlagReportFileName());
			ps.setInt(13, patent.getStatus());
			ps.setString(14, patent.getWrittenBy());
			ps.setString(15, id);
			ps.setString(16, message);
			ps.setInt(17, patent.getPatentNo());
			ps1.setInt(1, status);
			ps1.setNull(2, Types.VARCHAR);
			ps1.setNull(3, Types.DATE);
			ps1.setString(4, id);
			if (ps1.executeUpdate() > 0 && ps.executeUpdate() > 0) {
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
	public int getMissing(int[] a, int n) {
		int i;
		int total;
		total = (n + 1) * (n + 2) / 2;
		for (i = 1; i <= n; i++)
			total -= a[i - 1];
		return total;
	}

	@Override
	public int notificationRejectedPatents(String id) {
		Connection connection = null;
		PreparedStatement statement;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection
					.prepareStatement("select distinct count(*) as number from patent where status>0 and writtenby=?");
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return rs.getInt("number");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return 0;
	}
	
	
	@Override
	public Map<String,Patent> getAllRejPatents() {
		Connection connection = null;
		PreparedStatement ps = null;
		Map<String, Patent> map = new HashMap<>();
		try{
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select * from rej_patent");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Patent patent = new Patent();
				String rejID = Integer.toString(rs.getInt("rej"));
				patent.setId(rs.getString("id"));
				patent.setPcn(rs.getString("pcn"));
				patent.setFaculty(rs.getString("faculty"));
				patent.setDeptt(rs.getString("deptt"));
				patent.setTitle(rs.getString("title"));
				patent.setNationality(rs.getString("nationality"));
				patent.setCountry(rs.getString("country"));
				patent.setApplicationNo(rs.getString("applicationNo"));
				patent.setApplicationYear(rs.getInt("applicationYear"));
				patent.setApplicationDate(rs.getString("applicationDate"));
				patent.setPatentYear(rs.getInt("patentYear"));
				patent.setAwardDate(rs.getString("awardDate"));
				patent.setMonthAssigned(rs.getString("monthAssigned"));
				patent.setPublicationFileName(rs.getString("publicationFileName"));
				patent.setPlagReportFileName(rs.getString("plagReportFileName"));
				patent.setStatus(rs.getInt("status"));
				patent.setWrittenBy(rs.getString("writtenBy"));
				patent.setPatentNo(rs.getInt("patentNo"));
				map.put(rejID,patent);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection);
		}
		return map;
	}
	@Override
	public boolean checkIfRejected(String id){
		Connection connection = null;
		PreparedStatement statement;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(
					"select * from rej_patent where id=?");
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return false;
	}

}
