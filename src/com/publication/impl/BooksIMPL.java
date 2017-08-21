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
import com.publication.dao.BookDAO;
import com.publication.database.ConnectionFactory;
import com.publication.model.Books;

public class BooksIMPL implements BookDAO {

	@Override
	public boolean saveBook(Books book) {

		if (book == null) {
			return false;
		}
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(
					"insert into book (nameOauthors, deptt, title, publisher, nationality, year, monthPublished,pageNo"
							+ ",isbn, hyperlink, indices, link"
							+ ", publicationfilename, plagreportfilename, plagcopyfilename, status, writtenBy, id,certificateName) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, book.getNameOauthors());
			ps.setString(2, book.getDeptt().toUpperCase());
			ps.setString(3, book.getTitle());
			ps.setString(4, book.getPublisher());
			ps.setString(5, book.getNationality());
			ps.setInt(6, book.getYear());
			ps.setString(7, book.getMonthPublished());
			ps.setString(8, book.getPageNo());
			ps.setString(9, book.getIsbn());
			ps.setString(10, book.getHyperlink());
			ps.setString(11, book.getIndex());
			ps.setString(12, book.getLink());
			ps.setString(13, book.getPublicationFileName());
			ps.setString(14, book.getPlagReportFileName());
			ps.setString(15, book.getPlagCopyFileName());
			ps.setInt(16, book.getStatus());
			ps.setString(17, book.getWrittenBy());
			String id;
			PreparedStatement ps1 = connection.prepareStatement("select id from book");
			ResultSet rs = ps1.executeQuery();
			ArrayList<Integer> list = new ArrayList<>();

			if (!rs.next()) {
				id = "B0001";
			} else {
				rs.beforeFirst();
				while (rs.next()) {
					String result = rs.getString("id");
					list.add(Integer.parseInt(result.substring(1)));
				}
				int[] array = list.stream().mapToInt(i -> i).toArray();
				int sno = getMissing(array, array.length);
				id = String.format("B%04d", sno);
			}
			ps.setString(18, id);
			ps.setString(19, book.getCertificateName());
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
	public boolean updateBook(Books book) {

		if (book == null) {
			return false;
		}
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(
					"update book set nameOauthors=?, deptt=?, title=?, publisher=?, nationality=?, year=?, monthPublished=?,pageNo=?"
							+ ",isbn=?, hyperlink=?, indices=?, link=?"
							+ ", publicationfilename=?, plagreportfilename=?, plagcopyfilename=?, status=?, writtenBy=?,certificateName=? where id=?");
			ps.setString(1, book.getNameOauthors());
			ps.setString(2, book.getDeptt().toUpperCase());
			ps.setString(3, book.getTitle());
			ps.setString(4, book.getPublisher());
			ps.setString(5, book.getNationality());
			ps.setInt(6, book.getYear());
			ps.setString(7, book.getMonthPublished());
			ps.setString(8, book.getPageNo());
			ps.setString(9, book.getIsbn());
			ps.setString(10, book.getHyperlink());
			ps.setString(11, book.getIndex());
			ps.setString(12, book.getLink());
			ps.setString(13, book.getPublicationFileName());
			ps.setString(14, book.getPlagReportFileName());
			ps.setString(15, book.getPlagCopyFileName());
			ps.setInt(16, book.getStatus());
			ps.setString(17, book.getWrittenBy());
			ps.setString(18, book.getCertificateName());
			ps.setString(19, book.getId());
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
	public List<Books> getAllBooks() throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		List<Books> list = new ArrayList<>();
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select * from book");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Books books = new Books();
				books.setId(rs.getString("id"));
				books.setPcn(rs.getString("pcn"));
				books.setNameOauthors(rs.getString("nameOauthors"));
				books.setDeptt(rs.getString("deptt"));
				books.setTitle(rs.getString("title"));
				books.setPublisher(rs.getString("publisher"));
				books.setNationality(rs.getString("nationality"));
				books.setYear(rs.getInt("year"));
				books.setMonthPublished(rs.getString("monthPublished"));
				books.setMonthAssigned(rs.getString("monthAssigned"));
				books.setPageNo(rs.getString("pageNo"));
				books.setIsbn(rs.getString("isbn"));
				books.setHyperlink(rs.getString("hyperlink"));
				books.setIndex(rs.getString("indices"));
				books.setLink(rs.getString("link"));
				books.setPublicationFileName(rs.getString("publicationFileName"));
				books.setPlagReportFileName(rs.getString("plagReportFileName"));
				books.setPlagCopyFileName(rs.getString("plagCopyFileName"));
				books.setStatus(rs.getInt("status"));
				books.setWrittenBy(rs.getString("writtenBy"));
				books.setCertificateName(rs.getString("certificateName"));
				list.add(books);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return list;
	}

	@Override
	public Books getBookByID(String id) {
		Connection connection = null;
		PreparedStatement ps = null;
		Books books = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select * from book where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				books = new Books();
				books.setId(rs.getString("id"));
				books.setPcn(rs.getString("pcn"));
				books.setNameOauthors(rs.getString("nameOauthors"));
				books.setDeptt(rs.getString("deptt"));
				books.setTitle(rs.getString("title"));
				books.setPublisher(rs.getString("publisher"));
				books.setNationality(rs.getString("nationality"));
				books.setYear(rs.getInt("year"));
				books.setMonthPublished(rs.getString("monthPublished"));
				books.setMonthAssigned(rs.getString("monthAssigned"));
				books.setPageNo(rs.getString("pageNo"));
				books.setIsbn(rs.getString("isbn"));
				books.setHyperlink(rs.getString("hyperlink"));
				books.setIndex(rs.getString("indices"));
				books.setLink(rs.getString("link"));
				books.setPublicationFileName(rs.getString("publicationFileName"));
				books.setPlagReportFileName(rs.getString("plagReportFileName"));
				books.setPlagCopyFileName(rs.getString("plagCopyFileName"));
				books.setCertificateName(rs.getString("certificateName"));
				books.setStatus(rs.getInt("status"));
				books.setWrittenBy(rs.getString("writtenBy"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return books;

	}

	@Override
	public boolean delete(String id) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("delete from book where id=?");
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
		Books books = getBookByID(id);
		if (null == books) {
			return false;
		}
		Connection connection;
		PreparedStatement ps1;
		PreparedStatement ps2;
		ArrayList<Integer> list = new ArrayList<>();
		try {
			connection = ConnectionFactory.getConnection();
			ps1 = connection.prepareStatement(
					"select pcn from book where pcn like \"" + books.getDeptt().toUpperCase() + "____%B___\"");
			ResultSet rs = ps1.executeQuery();
			String pcn;
			if (!rs.next()) {
				pcn = GeneratePCN.generatePCN(books.getDeptt().toUpperCase(), "B", 1);
			} else {
				rs.beforeFirst();
				while (rs.next()) {
					String result = rs.getString("pcn");
					list.add(Integer.parseInt(result.substring(8)));
				}
				int[] array = list.stream().mapToInt(i -> i).toArray();
				int sno = getMissing(array, array.length);
				pcn = GeneratePCN.generatePCN(books.getDeptt().toUpperCase(), "B", sno);
			}
			ps2 = connection.prepareStatement("update book set pcn=?, status=?, monthAssigned=? where id=?");
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
		Books book = getBookByID(id);
		if (null == book) {
			return false;
		}
		Connection connection = null;
		PreparedStatement ps1;
		PreparedStatement ps;
		try {
			connection = ConnectionFactory.getConnection();
			ps1 = connection.prepareStatement("update book set status=?, pcn=?, monthAssigned=? where id=?");
			ps = connection.prepareStatement(
					"insert into rej_book (nameOauthors, deptt, title, publisher, nationality, year, monthPublished,pageNo"
							+ ",isbn, hyperlink, indices, link"
							+ ", publicationfilename, plagreportfilename, plagcopyfilename, status, writtenBy, id, message) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, book.getNameOauthors());
			ps.setString(2, book.getDeptt().toUpperCase());
			ps.setString(3, book.getTitle());
			ps.setString(4, book.getPublisher());
			ps.setString(5, book.getNationality());
			ps.setInt(6, book.getYear());
			ps.setString(7, book.getMonthPublished());
			ps.setString(8, book.getPageNo());
			ps.setString(9, book.getIsbn());
			ps.setString(10, book.getHyperlink());
			ps.setString(11, book.getIndex());
			ps.setString(12, book.getLink());
			ps.setString(13, book.getPublicationFileName());
			ps.setString(14, book.getPlagReportFileName());
			ps.setString(15, book.getPlagCopyFileName());
			ps.setInt(16, book.getStatus());
			ps.setString(17, book.getWrittenBy());
			ps.setString(18, id);
			ps.setString(19, message);
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
	public int notificationRejectedBooks(String id) {
		Connection connection = null;
		PreparedStatement statement;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection
					.prepareStatement("select distinct count(*) as number from book where status>0 and writtenby=?");
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
	public Map<String,Books> getAllRejBooks() {
		Connection connection = null;
		PreparedStatement ps = null;
		Map<String,Books> map = new HashMap<String,Books>();
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select * from rej_book");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Books books = new Books();
				String rejID = Integer.toString(rs.getInt("rej"));
				books.setId(rs.getString("id"));
				books.setPcn(rs.getString("pcn"));
				books.setNameOauthors(rs.getString("nameOauthors"));
				books.setDeptt(rs.getString("deptt"));
				books.setTitle(rs.getString("title"));
				books.setPublisher(rs.getString("publisher"));
				books.setNationality(rs.getString("nationality"));
				books.setYear(rs.getInt("year"));
				books.setMonthPublished(rs.getString("monthPublished"));
				books.setMonthAssigned(rs.getString("monthAssigned"));
				books.setPageNo(rs.getString("pageNo"));
				books.setIsbn(rs.getString("isbn"));
				books.setHyperlink(rs.getString("hyperlink"));
				books.setIndex(rs.getString("indices"));
				books.setLink(rs.getString("link"));
				books.setPublicationFileName(rs.getString("publicationFileName"));
				books.setPlagReportFileName(rs.getString("plagReportFileName"));
				books.setPlagCopyFileName(rs.getString("plagCopyFileName"));
				books.setCertificateName(rs.getString("certificateName"));
				books.setStatus(rs.getInt("status"));
				books.setWrittenBy(rs.getString("writtenBy"));
				map.put(rejID, books);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
					"select * from rej_book where id=?");
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
