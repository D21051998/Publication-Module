package com.publication.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.publication.constants.GeneratePCN;
import com.publication.dao.ConferenceProceedingDAO;
import com.publication.database.ConnectionFactory;
import com.publication.model.ConferenceProceedings;

public class ConferenceProceedingIMPL implements ConferenceProceedingDAO {

	@Override
	public boolean saveConferenceProceedings(ConferenceProceedings conferenceProceedings) {
		if (conferenceProceedings == null) {
			return false;
		}
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(
					"insert into conf_proc (nameOauthors, deptt, title,proceedingsOf, nationality,venue,  year, "
							+ "monthPublished, publisher, pageNo, hyperLink, indices, link, publicationfilename, plagreportfilename,"
							+ " plagcopyfilename, status, writtenBy, id) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, conferenceProceedings.getNameOauthors());
			ps.setString(2, conferenceProceedings.getDeptt().toUpperCase());
			ps.setString(3, conferenceProceedings.getTitle());
			ps.setString(4, conferenceProceedings.getProceedingsOf());
			ps.setString(5, conferenceProceedings.getNationality());
			ps.setString(6, conferenceProceedings.getVenue());
			ps.setInt(7, conferenceProceedings.getYear());
			ps.setString(8, conferenceProceedings.getMonthPublished());
			ps.setString(9, conferenceProceedings.getPublisher());
			ps.setInt(10, conferenceProceedings.getPageNo());
			ps.setString(11, conferenceProceedings.getHyperlink());
			ps.setString(12, conferenceProceedings.getIndex());
			ps.setString(13, conferenceProceedings.getLink());
			ps.setString(14, conferenceProceedings.getPublicationFileName());
			ps.setString(15, conferenceProceedings.getPlagReportFileName());
			ps.setString(16, conferenceProceedings.getPlagCopyFileName());
			ps.setInt(17, conferenceProceedings.getStatus());
			ps.setString(18, conferenceProceedings.getWrittenBy());
			String id;
			PreparedStatement ps1 = connection.prepareStatement("select id from conf_proc");
			ResultSet rs = ps1.executeQuery();
			ArrayList<Integer> list = new ArrayList<>();

			if (!rs.next()) {
				id = "P0001";
			} else {
				rs.beforeFirst();
				while (rs.next()) {
					String result = rs.getString("id");
					list.add(Integer.parseInt(result.substring(1)));
				}
				int[] array = list.stream().mapToInt(i -> i).toArray();
				int sno = getMissing(array, array.length);
				id = String.format("P%04d", sno);
			}
			ps.setString(19, id);
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
	public boolean updateConferenceProceedings(ConferenceProceedings conferenceProceedings) {
		if (conferenceProceedings == null) {
			return false;
		}
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(
					"update conf_proc set nameOauthors=?, deptt=?, title=?,proceedingsOf=?, nationality=?,venue=?,  year=?, "
							+ "monthPublished=?, publisher=?, pageNo=?, hyperLink=?, indices=?, link=?, publicationfilename=?, plagreportfilename=?,"
							+ " plagcopyfilename=?, status=?, writtenBy=? where id=?");
			ps.setString(1, conferenceProceedings.getNameOauthors());
			ps.setString(2, conferenceProceedings.getDeptt().toUpperCase());
			ps.setString(3, conferenceProceedings.getTitle());
			ps.setString(4, conferenceProceedings.getProceedingsOf());
			ps.setString(5, conferenceProceedings.getNationality());
			ps.setString(6, conferenceProceedings.getVenue());
			ps.setInt(7, conferenceProceedings.getYear());
			ps.setString(8, conferenceProceedings.getMonthPublished());
			ps.setString(9, conferenceProceedings.getPublisher());
			ps.setInt(10, conferenceProceedings.getPageNo());
			ps.setString(11, conferenceProceedings.getHyperlink());
			ps.setString(12, conferenceProceedings.getIndex());
			ps.setString(13, conferenceProceedings.getLink());
			ps.setString(14, conferenceProceedings.getPublicationFileName());
			ps.setString(15, conferenceProceedings.getPlagReportFileName());
			ps.setString(16, conferenceProceedings.getPlagCopyFileName());
			ps.setInt(17, conferenceProceedings.getStatus());
			ps.setString(18, conferenceProceedings.getWrittenBy());
			ps.setString(19, conferenceProceedings.getId());
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
	public List<ConferenceProceedings> getAllConferenceProceedingss() throws SQLException {
		List<ConferenceProceedings> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select * from conf_proc");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ConferenceProceedings cp = new ConferenceProceedings();
				cp.setId(rs.getString("id"));
				cp.setPcn(rs.getString("pcn"));
				cp.setNameOauthors(rs.getString("nameOauthors"));
				cp.setDeptt(rs.getString("deptt"));
				cp.setTitle(rs.getString("title"));
				cp.setProceedingsOf(rs.getString("proceedingsOf"));
				cp.setNationality(rs.getString("nationality"));
				cp.setVenue(rs.getString("venue"));
				cp.setYear(rs.getInt("year"));
				cp.setMonthAssigned(rs.getString("monthAssigned"));
				cp.setMonthPublished(rs.getString("monthPublished"));
				cp.setPublisher(rs.getString("publisher"));
				cp.setPageNo(rs.getInt("pageNo"));
				cp.setHyperlink(rs.getString("hyperlink"));
				cp.setIndex(rs.getString("indices"));
				cp.setLink(rs.getString("link"));
				cp.setPublicationFileName(rs.getString("publicationFileName"));
				cp.setPlagCopyFileName(rs.getString("plagCopyFileName"));
				cp.setPlagReportFileName(rs.getString("plagReportFileName"));
				cp.setStatus(rs.getInt("status"));
				cp.setWrittenBy(rs.getString("writtenBy"));
				list.add(cp);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection);
		}
		
		return list;
	}

	@Override
	public ConferenceProceedings getConferenceProceedingsByID(String id) {
		ConferenceProceedings cp = null;
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select * from conf_proc where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cp = new ConferenceProceedings();
				cp.setId(rs.getString("id"));
				cp.setPcn(rs.getString("pcn"));
				cp.setNameOauthors(rs.getString("nameOauthors"));
				cp.setDeptt(rs.getString("deptt"));
				cp.setTitle(rs.getString("title"));
				cp.setProceedingsOf(rs.getString("proceedingsOf"));
				cp.setNationality(rs.getString("nationality"));
				cp.setVenue(rs.getString("venue"));
				cp.setYear(rs.getInt("year"));
				cp.setMonthAssigned(rs.getString("monthAssigned"));
				cp.setMonthPublished(rs.getString("monthPublished"));
				cp.setPublisher(rs.getString("publisher"));
				cp.setPageNo(rs.getInt("pageNo"));
				cp.setHyperlink(rs.getString("hyperlink"));
				cp.setIndex(rs.getString("indices"));
				cp.setLink(rs.getString("link"));
				cp.setPublicationFileName(rs.getString("publicationFileName"));
				cp.setPlagCopyFileName(rs.getString("plagCopyFileName"));
				cp.setPlagReportFileName(rs.getString("plagReportFileName"));
				cp.setStatus(rs.getInt("status"));
				cp.setWrittenBy(rs.getString("writtenBy"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection);
		}
		
		return cp;
	}

	@Override
	public boolean delete(String id) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("delete from conf_proc where id=?");
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
		ConferenceProceedings proceedings = getConferenceProceedingsByID(id);
		if (null == proceedings) {
			return false;
		}
		Connection connection;
		PreparedStatement ps1;
		PreparedStatement ps2;
		ArrayList<Integer> list = new ArrayList<>();
		try {
			connection = ConnectionFactory.getConnection();
			ps1 = connection.prepareStatement("select pcn from conf_proc where pcn like \""
					+ proceedings.getDeptt().toUpperCase() + "____%P___\"");
			ResultSet rs = ps1.executeQuery();
			String pcn;
			if (!rs.next()) {
				pcn = GeneratePCN.generatePCN(proceedings.getDeptt().toUpperCase(), "P", 1);
			} else {
				rs.beforeFirst();
				while (rs.next()) {
					String result = rs.getString("pcn");
					list.add(Integer.parseInt(result.substring(8)));
				}
				int[] array = list.stream().mapToInt(i -> i).toArray();
				int sno = getMissing(array, array.length);
				pcn = GeneratePCN.generatePCN(proceedings.getDeptt().toUpperCase(), "P", sno);
			}
			ps2 = connection.prepareStatement("update conf_proc set pcn=?, status=?, monthAssigned=? where id=?");
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
		ConferenceProceedings conferenceProceedings = getConferenceProceedingsByID(id);
		if (null == conferenceProceedings) {
			return false;
		}
		Connection connection = null;
		PreparedStatement ps1;
		PreparedStatement ps;
		try {
			connection = ConnectionFactory.getConnection();
			ps1 = connection.prepareStatement("update conf_proc set status=?, pcn=?, monthAssigned=? where id=?");
			ps = connection.prepareStatement(
					"insert into rej_conf_proc (nameOauthors, deptt, title,proceedingsOf, nationality,venue,  year, "
							+ "monthPublished, publisher, pageNo, hyperLink, indices, link, publicationfilename, plagreportfilename,"
							+ " plagcopyfilename, status, writtenBy, id,message) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, conferenceProceedings.getNameOauthors());
			ps.setString(2, conferenceProceedings.getDeptt().toUpperCase());
			ps.setString(3, conferenceProceedings.getTitle());
			ps.setString(4, conferenceProceedings.getProceedingsOf());
			ps.setString(5, conferenceProceedings.getNationality());
			ps.setString(6, conferenceProceedings.getVenue());
			ps.setInt(7, conferenceProceedings.getYear());
			ps.setString(8, conferenceProceedings.getMonthPublished());
			ps.setString(9, conferenceProceedings.getPublisher());
			ps.setInt(10, conferenceProceedings.getPageNo());
			ps.setString(11, conferenceProceedings.getHyperlink());
			ps.setString(12, conferenceProceedings.getIndex());
			ps.setString(13, conferenceProceedings.getLink());
			ps.setString(14, conferenceProceedings.getPublicationFileName());
			ps.setString(15, conferenceProceedings.getPlagReportFileName());
			ps.setString(16, conferenceProceedings.getPlagCopyFileName());
			ps.setInt(17, conferenceProceedings.getStatus());
			ps.setString(18, conferenceProceedings.getWrittenBy());
			ps.setString(19, id);
			ps.setString(20, message);
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
	public int notificationRejectedConferenceProceedingss(String id) {
		Connection connection = null;
		PreparedStatement statement;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(
					"select distinct count(*) as number from conf_proc where status>0 and writtenby=?");
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

}
