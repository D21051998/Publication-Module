package com.publication.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.publication.constants.GeneratePCN;
import com.publication.dao.ConferencePresentationDAO;
import com.publication.database.ConnectionFactory;
import com.publication.model.ConferencePresentation;

public class ConferencePresentationIMPL implements ConferencePresentationDAO {

	@Override
	public boolean saveConferencePresentation(ConferencePresentation conferencePresentation) {
		if (conferencePresentation == null) {
			return false;
		}
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(
					"insert into conferencePresentation (faculty, deptt, title, conferencePresentation, nationality,organisedby, venue,  year,dates,hyperlink, monthPublished, publicationfilename, plagreportfilename, status, writtenBy, id) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, conferencePresentation.getFaculty());
			ps.setString(2, conferencePresentation.getDeptt().toUpperCase());
			ps.setString(3, conferencePresentation.getTitle());
			ps.setString(4, conferencePresentation.getConferencePresentation());
			ps.setString(5, conferencePresentation.getNationality());
			ps.setString(6, conferencePresentation.getOrganisedBy());
			ps.setString(7, conferencePresentation.getVenue());
			ps.setInt(8, conferencePresentation.getYear());
			ps.setString(9, conferencePresentation.getDates());
			ps.setString(10, conferencePresentation.getHyperlink());
			ps.setString(11, conferencePresentation.getMonthPublished());
			ps.setString(12, conferencePresentation.getPublicationFileName());
			ps.setString(13, conferencePresentation.getPlagReportFileName());
			ps.setInt(14, conferencePresentation.getStatus());
			ps.setString(15, conferencePresentation.getWrittenBy());
			String id;
			PreparedStatement ps1 = connection.prepareStatement("select id from conf_pres");
			ResultSet rs = ps1.executeQuery();
			ArrayList<Integer> list = new ArrayList<>();

			if (!rs.next()) {
				id = "C0001";
			} else {
				rs.beforeFirst();
				while (rs.next()) {
					String result = rs.getString("id");
					list.add(Integer.parseInt(result.substring(1)));
				}
				int[] array = list.stream().mapToInt(i -> i).toArray();
				int sno = getMissing(array, array.length);
				id = String.format("C%04d", sno);
			}
			ps.setString(16, id);
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
	public boolean updateConferencePresentation(ConferencePresentation ConferencePresentation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ConferencePresentation> getAllConferencePresentations() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConferencePresentation getConferencePresentationByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String id) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("delete from conf_pres where id=?");
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
		ConferencePresentation presentation = getConferencePresentationByID(id);
		if (null == presentation) {
			return false;
		}
		Connection connection;
		PreparedStatement ps1;
		PreparedStatement ps2;
		ArrayList<Integer> list = new ArrayList<>();
		try {
			connection = ConnectionFactory.getConnection();
			ps1 = connection.prepareStatement(
					"select pcn from conf_pres where pcn like \"" + presentation.getDeptt().toUpperCase() + "____%C___\"");
			ResultSet rs = ps1.executeQuery();
			String pcn;
			if (!rs.next()) {
				pcn = GeneratePCN.generatePCN(presentation.getDeptt().toUpperCase(), "C", 1);
			} else {
				rs.beforeFirst();
				while (rs.next()) {
					String result = rs.getString("pcn");
					list.add(Integer.parseInt(result.substring(8)));
				}
				int[] array = list.stream().mapToInt(i -> i).toArray();
				int sno = getMissing(array, array.length);
				pcn = GeneratePCN.generatePCN(presentation.getDeptt().toUpperCase(), "C", sno);
			}
			ps2 = connection.prepareStatement("update conf_pres set pcn=?, status=?, monthAssigned=? where id=?");
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
	public int notificationRejectedConferencePresentations(String id) {
		Connection connection = null;
		PreparedStatement statement;
		try{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement("select distinct count(*) as number from conf_pres where status>0 and writtenby=?");
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
