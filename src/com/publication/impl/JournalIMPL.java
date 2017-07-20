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
import com.publication.dao.JournalDAO;
import com.publication.database.ConnectionFactory;
import com.publication.model.Journal;

public class JournalIMPL implements JournalDAO {

	@Override
	public boolean saveJournal(Journal journal) {
		if (journal == null) {
			return false;
		}
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(
					"insert into journal (nameOauthors, deptt, title, journal, nationality, year, monthPublished, volume, issue, pageNo,"
							+ " doiNo, impactFactor, whatImpactFactor, linkImpFactor, paidOrUnpaid, paymentFlag, pwflag, psflag, pgflag, piflag, publicationfilename, plagreportfilename, plagcopyfilename, status, writtenBy, id) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, journal.getNameOauthors());
			ps.setString(2, journal.getDeptt().toUpperCase());
			ps.setString(3, journal.getTitle());
			ps.setString(4, journal.getJournal());
			ps.setString(5, journal.getNationality());
			ps.setInt(6, journal.getYear());
			ps.setString(7, journal.getMonthPublished());
			ps.setInt(8, journal.getVolume());
			ps.setInt(9, journal.getIssue());
			ps.setInt(10, journal.getPageNo());
			ps.setInt(11, journal.getDoiNo());
			ps.setString(12, journal.getImpactFactor());
			ps.setString(13, journal.getWhatImpactFactor());
			ps.setString(14, journal.getLinkImpFactor());
			ps.setString(15, journal.getPaidOrUnpaid());
			ps.setString(16, journal.getPaymentFlag());
			ps.setString(17, journal.getPwFlag());
			ps.setString(18, journal.getPsFlag());
			ps.setString(19, journal.getPgFlag());
			ps.setString(20, journal.getPiFlag());
			ps.setString(21, journal.getPublicationFileName());
			ps.setString(22, journal.getPlagReportFileName());
			ps.setString(23, journal.getPlagCopyFileName());
			ps.setInt(24, journal.getStatus());
			ps.setString(25, journal.getWrittenBy());
			String id;
			PreparedStatement ps1 = connection.prepareStatement("select id from journal");
			ResultSet rs = ps1.executeQuery();
			ArrayList<Integer> list = new ArrayList<>();

			if (!rs.next()) {
				id = "J0001";
			} else {
				rs.beforeFirst();
				while (rs.next()) {
					String result = rs.getString("id");
					list.add(Integer.parseInt(result.substring(1)));
				}
				int[] array = list.stream().mapToInt(i -> i).toArray();
				int sno = getMissing(array, array.length);
				id = String.format("J%04d", sno);
			}
			ps.setString(26, id);
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
	public boolean updateJournal(Journal journal) {
		if (journal == null) {
			return false;
		}
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(
					"update journal set nameOauthors=?, deptt=?, title=?, journal=?, nationality=?, year=?, monthPublished=?, volume=?, issue=?, pageNo=?,"
							+ " doiNo=?, impactFactor=?, whatImpactFactor=?, linkImpFactor=?, paidOrUnpaid=?, paymentFlag=?,"
							+ " pwflag=?, psflag=?, pgflag=?, piflag=?, publicationfilename=?, plagreportfilename=?, plagcopyfilename=?, status=?, writtenBy=? where  id=?");
			ps.setString(1, journal.getNameOauthors());
			ps.setString(2, journal.getDeptt().toUpperCase());
			ps.setString(3, journal.getTitle());
			ps.setString(4, journal.getJournal());
			ps.setString(5, journal.getNationality());
			ps.setInt(6, journal.getYear());
			ps.setString(7, journal.getMonthPublished());
			ps.setInt(8, journal.getVolume());
			ps.setInt(9, journal.getIssue());
			ps.setInt(10, journal.getPageNo());
			ps.setInt(11, journal.getDoiNo());
			ps.setString(12, journal.getImpactFactor());
			ps.setString(13, journal.getWhatImpactFactor());
			ps.setString(14, journal.getLinkImpFactor());
			ps.setString(15, journal.getPaidOrUnpaid());
			ps.setString(16, journal.getPaymentFlag());
			ps.setString(17, journal.getPwFlag());
			ps.setString(18, journal.getPsFlag());
			ps.setString(19, journal.getPgFlag());
			ps.setString(20, journal.getPiFlag());
			ps.setString(21, journal.getPublicationFileName());
			ps.setString(22, journal.getPlagReportFileName());
			ps.setString(23, journal.getPlagCopyFileName());
			ps.setInt(24, journal.getStatus());
			ps.setString(25, journal.getWrittenBy());
			ps.setString(26, journal.getId());
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
	public List<Journal> getAllJournals() throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		List<Journal> list = new ArrayList<>();
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select * from journal");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Journal j = new Journal();
				j.setId(rs.getString("id"));
				j.setPcn(rs.getString("pcn"));
				j.setNameOauthors(rs.getString("nameOauthors"));
				j.setDeptt(rs.getString("deptt"));
				j.setTitle(rs.getString("title"));
				j.setJournal(rs.getString("journal"));
				j.setNationality(rs.getString("nationality"));
				j.setYear(rs.getInt("year"));
				j.setMonthPublished(rs.getString("monthPublished"));
				j.setMonthAssigned(rs.getString("monthAssigned"));
				j.setVolume(rs.getInt("volume"));
				j.setIssue(rs.getInt("issue"));
				j.setPageNo(rs.getInt("pageNo"));
				j.setDoiNo(rs.getInt("doiNo"));
				j.setImpactFactor(rs.getString("impactFactor"));
				j.setWhatImpactFactor(rs.getString("whatImpactFactor"));
				j.setLinkImpFactor(rs.getString("linkImpFactor"));
				j.setPaidOrUnpaid(rs.getString("paidOrUnpaid"));
				j.setPaymentFlag(rs.getString("paymentFlag"));
				j.setPwFlag(rs.getString("pwFlag"));
				j.setPsFlag(rs.getString("psFlag"));
				j.setPgFlag(rs.getString("pgFlag"));
				j.setPiFlag(rs.getString("piFlag"));
				j.setPublicationFileName(rs.getString("publicationFileName"));
				j.setPlagReportFileName(rs.getString("plagReportFileName"));
				j.setPlagCopyFileName(rs.getString("plagCopyFileName"));
				j.setStatus(rs.getInt("status"));
				j.setWrittenBy(rs.getString("writtenBy"));
				list.add(j);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return list;
	}

	@Override
	public Journal getJournalByID(String id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select * from journal where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Journal j = new Journal();
				j.setId(rs.getString("id"));
				j.setPcn(rs.getString("pcn"));
				j.setNameOauthors(rs.getString("nameOauthors"));
				j.setDeptt(rs.getString("deptt"));
				j.setTitle(rs.getString("title"));
				j.setJournal(rs.getString("journal"));
				j.setNationality(rs.getString("nationality"));
				j.setYear(rs.getInt("year"));
				j.setMonthPublished(rs.getString("monthPublished"));
				j.setMonthAssigned(rs.getString("monthAssigned"));
				j.setVolume(rs.getInt("volume"));
				j.setIssue(rs.getInt("issue"));
				j.setPageNo(rs.getInt("pageNo"));
				j.setDoiNo(rs.getInt("doiNo"));
				j.setImpactFactor(rs.getString("impactFactor"));
				j.setWhatImpactFactor(rs.getString("whatImpactFactor"));
				j.setLinkImpFactor(rs.getString("linkImpFactor"));
				j.setPaidOrUnpaid(rs.getString("paidOrUnpaid"));
				j.setPaymentFlag(rs.getString("paymentFlag"));
				j.setPwFlag(rs.getString("pwFlag"));
				j.setPsFlag(rs.getString("psFlag"));
				j.setPgFlag(rs.getString("pgFlag"));
				j.setPiFlag(rs.getString("piFlag"));
				j.setPublicationFileName(rs.getString("publicationFileName"));
				j.setPlagReportFileName(rs.getString("plagReportFileName"));
				j.setPlagCopyFileName(rs.getString("plagCopyFileName"));
				j.setStatus(rs.getInt("status"));
				j.setWrittenBy(rs.getString("writtenBy"));
				return j;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return null;
	}

	@Override
	public boolean delete(String id) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("delete from journal where id=?");
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
		Journal journal = getJournalByID(id);
		if (null == journal) {
			return false;
		}
		Connection connection;
		PreparedStatement ps1;
		PreparedStatement ps2;
		ArrayList<Integer> list = new ArrayList<>();
		try {
			connection = ConnectionFactory.getConnection();
			ps1 = connection.prepareStatement(
					"select pcn from journal where pcn like \"" + journal.getDeptt().toUpperCase() + "____%J___\"");
			ResultSet rs = ps1.executeQuery();
			String pcn;
			if (!rs.next()) {
				pcn = GeneratePCN.generatePCN(journal.getDeptt().toUpperCase(), "J", 1);
			} else {
				rs.beforeFirst();
				while (rs.next()) {
					String result = rs.getString("pcn");
					list.add(Integer.parseInt(result.substring(8)));
				}
				int[] array = list.stream().mapToInt(i -> i).toArray();
				int sno = getMissing(array, array.length);
				pcn = GeneratePCN.generatePCN(journal.getDeptt().toUpperCase(), "J", sno);
			}
			ps2 = connection.prepareStatement("update journal set pcn=?, status=?, monthAssigned=? where id=?");
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

	public boolean reject(String id, int status, String message) {
		Journal journal = getJournalByID(id);
		if (null == journal) {
			return false;
		}
		Connection connection =null;
		PreparedStatement ps1;
		PreparedStatement ps;
		try {
			connection = ConnectionFactory.getConnection();
			ps1 = connection.prepareStatement("update journal set status=?, pcn=?, monthAssigned=? where id=?");
			ps = connection.prepareStatement(
					"insert into rej_journal (nameOauthors, deptt, title, journal, nationality, year, monthPublished, volume, issue, pageNo,"
							+ " doiNo, impactFactor, whatImpactFactor, linkImpFactor, paidOrUnpaid, paymentFlag, pwflag, psflag, pgflag, piflag,"
							+ " publicationfilename, plagreportfilename, plagcopyfilename, status, writtenBy, id,message) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        	ps.setString(1, journal.getNameOauthors());
			ps.setString(2, journal.getDeptt().toUpperCase());
			ps.setString(3, journal.getTitle());
			ps.setString(4, journal.getJournal());
			ps.setString(5, journal.getNationality());
			ps.setInt(6, journal.getYear());
			ps.setString(7, journal.getMonthPublished());
			ps.setInt(8, journal.getVolume());
			ps.setInt(9, journal.getIssue());
			ps.setInt(10, journal.getPageNo());
			ps.setInt(11, journal.getDoiNo());
			ps.setString(12, journal.getImpactFactor());
			ps.setString(13, journal.getWhatImpactFactor());
			ps.setString(14, journal.getLinkImpFactor());
			ps.setString(15, journal.getPaidOrUnpaid());
			ps.setString(16, journal.getPaymentFlag());
			ps.setString(17, journal.getPwFlag());
			ps.setString(18, journal.getPsFlag());
			ps.setString(19, journal.getPgFlag());
			ps.setString(20, journal.getPiFlag());
			ps.setString(21, journal.getPublicationFileName());
			ps.setString(22, journal.getPlagReportFileName());
			ps.setString(23, journal.getPlagCopyFileName());
			ps.setInt(24, journal.getStatus());
			ps.setString(25, journal.getWrittenBy());
			ps.setString(26, journal.getId());
			ps.setString(27, message);
			ps1.setInt(1, status);
			ps1.setNull(2, Types.VARCHAR);
			ps1.setNull(3, Types.DATE);
			ps1.setString(4, id);

			
			if (ps1.executeUpdate() > 0 && ps.executeUpdate()>0) {
				return true;
			}
		}
		 catch (Exception e) {
			e.printStackTrace();
		}finally {
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
	public int notificationRejectedJournal(String id){
		Connection connection = null;
		PreparedStatement statement;
		try{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement("select distinct count(*) as number from journal where status<0 and writtenby=?");
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
