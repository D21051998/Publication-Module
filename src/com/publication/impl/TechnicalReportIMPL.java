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
import com.publication.dao.TechnicalReportDAO;
import com.publication.database.ConnectionFactory;
import com.publication.model.TechnicalReport;

public class TechnicalReportIMPL implements TechnicalReportDAO {

	@Override
	public boolean saveTechnicalReport(TechnicalReport technicalReport) {
		if (technicalReport == null) {
			return false;
		}
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(
					"insert into tech_rep (faculty, deptt, title, year, date, remarks, monthPublished, publicationfilename, plagreportfilename, plagcopyfilename, status, writtenBy, id,certificateName) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, technicalReport.getFaculty());
			ps.setString(2, technicalReport.getDeptt().toUpperCase());
			ps.setString(3, technicalReport.getTitle());
			ps.setInt(4, technicalReport.getYear());
			ps.setString(5, technicalReport.getDate());
			ps.setString(6, technicalReport.getRemarks());
			ps.setString(7, technicalReport.getMonthPublished());
			ps.setString(8, technicalReport.getPublicationFileName());
			ps.setString(9, technicalReport.getPlagReportFileName());
			ps.setString(10, technicalReport.getPlagCopyFileName());
			ps.setInt(11, technicalReport.getStatus());
			ps.setString(12, technicalReport.getWrittenBy());
			String id;
			PreparedStatement ps1 = connection.prepareStatement("select id from tech_rep");
			ResultSet rs = ps1.executeQuery();
			ArrayList<Integer> list = new ArrayList<>();

			if (!rs.next()) {
				id = "R0001";
			} else {
				rs.beforeFirst();
				while (rs.next()) {
					String result = rs.getString("id");
					list.add(Integer.parseInt(result.substring(1)));
				}
				int[] array = list.stream().mapToInt(i -> i).toArray();
				int sno = getMissing(array, array.length);
				id = String.format("R%04d", sno);
			}
			ps.setString(13, id);
			ps.setString(14, technicalReport.getCertificateName());
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
	public boolean updateTechnicalReport(TechnicalReport technicalReport) {
		if (technicalReport == null) {
			return false;
		}
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(
					"update tech_rep set faculty=?, deptt=?, title=?, year=?, date=?, remarks=?, monthPublished=?, publicationfilename=?, plagreportfilename=?, plagcopyfilename=?, status=?, writtenBy=?,certificateName=? where  id=?");
			ps.setString(1, technicalReport.getFaculty());
			ps.setString(2, technicalReport.getDeptt().toUpperCase());
			ps.setString(3, technicalReport.getTitle());
			ps.setInt(4, technicalReport.getYear());
			ps.setString(5, technicalReport.getDate());
			ps.setString(6, technicalReport.getRemarks());
			ps.setString(7, technicalReport.getMonthPublished());
			ps.setString(8, technicalReport.getPublicationFileName());
			ps.setString(9, technicalReport.getPlagReportFileName());
			ps.setString(10, technicalReport.getPlagCopyFileName());
			ps.setInt(11, technicalReport.getStatus());
			ps.setString(12, technicalReport.getWrittenBy());
			ps.setString(13, technicalReport.getCertificateName());
			ps.setString(14, technicalReport.getId());
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
	public List<TechnicalReport> getAllTechnicalReports() throws SQLException {
		List<TechnicalReport> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select * from tech_rep");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TechnicalReport tc = new TechnicalReport();
				tc.setId(rs.getString("id"));
				tc.setPcn(rs.getString("pcn"));
				tc.setFaculty(rs.getString("faculty"));
				tc.setDeptt(rs.getString("deptt"));
				tc.setTitle(rs.getString("title"));
				tc.setYear(rs.getInt("year"));
				tc.setDate(rs.getString("date"));
				tc.setRemarks(rs.getString("remarks"));
				tc.setMonthPublished(rs.getString("monthPublished"));
				tc.setMonthAssigned(rs.getString("monthAssigned"));
				tc.setPublicationFileName(rs.getString("publicationFileName"));
				tc.setPlagReportFileName(rs.getString("plagReportFileName"));
				tc.setPlagCopyFileName(rs.getString("plagCopyFileName"));
				tc.setCertificateName(rs.getString("certificateName"));
				tc.setStatus(rs.getInt("status"));
				tc.setWrittenBy(rs.getString("writtenBy"));
				list.add(tc);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(connection);
		}
		return list;
	}

	@Override
	public TechnicalReport getTechnicalReportByID(String id) {
		TechnicalReport tc = null;
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select * from tech_rep where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tc = new TechnicalReport();
				tc.setId(rs.getString("id"));
				tc.setPcn(rs.getString("pcn"));
				tc.setFaculty(rs.getString("faculty"));
				tc.setDeptt(rs.getString("deptt"));
				tc.setTitle(rs.getString("title"));
				tc.setYear(rs.getInt("year"));
				tc.setDate(rs.getString("date"));
				tc.setRemarks(rs.getString("remarks"));
				tc.setMonthPublished(rs.getString("monthPublished"));
				tc.setMonthAssigned(rs.getString("monthAssigned"));
				tc.setPublicationFileName(rs.getString("publicationFileName"));
				tc.setPlagReportFileName(rs.getString("plagReportFileName"));
				tc.setPlagCopyFileName(rs.getString("plagCopyFileName"));
				tc.setCertificateName(rs.getString("certificateName"));
				tc.setStatus(rs.getInt("status"));
				tc.setWrittenBy(rs.getString("writtenBy"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(connection);
		}
		return tc;
	}

	@Override
	public boolean delete(String id) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("delete from tech_rep where id=?");
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
		TechnicalReport report = getTechnicalReportByID(id);
		if (null == report) {
			return false;
		}
		Connection connection;
		PreparedStatement ps1;
		PreparedStatement ps2;
		ArrayList<Integer> list = new ArrayList<>();
		try {
			connection = ConnectionFactory.getConnection();
			ps1 = connection.prepareStatement(
					"select pcn from tech_rep where pcn like \"" + report.getDeptt().toUpperCase() + "____%R___\"");
			ResultSet rs = ps1.executeQuery();
			String pcn;
			if (!rs.next()) {
				pcn = GeneratePCN.generatePCN(report.getDeptt().toUpperCase(), "R", 1);
			} else {
				rs.beforeFirst();
				while (rs.next()) {
					String result = rs.getString("pcn");
					list.add(Integer.parseInt(result.substring(8)));
				}
				int[] array = list.stream().mapToInt(i -> i).toArray();
				int sno = getMissing(array, array.length);
				pcn = GeneratePCN.generatePCN(report.getDeptt().toUpperCase(), "R", sno);
			}
			ps2 = connection.prepareStatement("update tech_rep set pcn=?, status=?, monthAssigned=? where id=?");
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
		TechnicalReport technicalReport = getTechnicalReportByID(id);
		Connection connection = null;
		PreparedStatement ps1;
		PreparedStatement ps;
		try {
			connection = ConnectionFactory.getConnection();
			ps1 = connection.prepareStatement("update tech_rep set status=?, pcn=?, monthAssigned=? where id=?");
			ps = connection.prepareStatement(
					"insert into rej_tech_rep (faculty, deptt, title, year, date, remarks, monthPublished, publicationfilename, plagreportfilename, plagcopyfilename, status, writtenBy, id,message) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, technicalReport.getFaculty());
			ps.setString(2, technicalReport.getDeptt().toUpperCase());
			ps.setString(3, technicalReport.getTitle());
			ps.setInt(4, technicalReport.getYear());
			ps.setString(5, technicalReport.getDate());
			ps.setString(6, technicalReport.getRemarks());
			ps.setString(7, technicalReport.getMonthPublished());
			ps.setString(8, technicalReport.getPublicationFileName());
			ps.setString(9, technicalReport.getPlagReportFileName());
			ps.setString(10, technicalReport.getPlagCopyFileName());
			ps.setInt(11, technicalReport.getStatus());
			ps.setString(12, technicalReport.getWrittenBy());
			ps.setString(13, id);
			ps.setString(14, message);
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
	public int notificationRejectedTechnicalReports(String id) {
		Connection connection = null;
		PreparedStatement statement;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(
					"select distinct count(*) as number from tech_rep where status<0 and writtenby=?");
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
	public Map<String,TechnicalReport> getAllRejTechnicalReports(){
		Map<String,TechnicalReport> map = new HashMap<String,TechnicalReport>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select * from rej_tech_rep");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TechnicalReport tc = new TechnicalReport();
				String rejID = Integer.toString(rs.getInt("rej"));
				tc.setId(rs.getString("id"));
				tc.setPcn(rs.getString("pcn"));
				tc.setFaculty(rs.getString("faculty"));
				tc.setDeptt(rs.getString("deptt"));
				tc.setTitle(rs.getString("title"));
				tc.setYear(rs.getInt("year"));
				tc.setDate(rs.getString("date"));
				tc.setRemarks(rs.getString("remarks"));
				tc.setMonthPublished(rs.getString("monthPublished"));
				tc.setMonthAssigned(rs.getString("monthAssigned"));
				tc.setPublicationFileName(rs.getString("publicationFileName"));
				tc.setPlagReportFileName(rs.getString("plagReportFileName"));
				tc.setPlagCopyFileName(rs.getString("plagCopyFileName"));
				tc.setCertificateName(rs.getString("certificateName"));
				tc.setStatus(rs.getInt("status"));
				tc.setWrittenBy(rs.getString("writtenBy"));
				map.put(rejID, tc);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
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
					"select * from rej_tech_rep where id=?");
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
