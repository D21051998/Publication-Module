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
import com.publication.dao.BookChapterDAO;
import com.publication.database.ConnectionFactory;
import com.publication.model.BookChapter;

public class BookChapterIMPL implements BookChapterDAO {

	@Override
	public boolean saveBookChapter(BookChapter bookChapter) {
		if (bookChapter == null) {
			return false;
		}
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(
					"insert into book_chapter (nameOauthors, deptt, chapterNo, chapterTitle, bookTitle, publisher, nationality, year, monthPublished, pageNo, isbn, hyperLink, indexFlag, indexLink, status, written_by,id, publicationfilename,plagreportfilename, plagcopyfilename) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, bookChapter.getNameOauthors());
			ps.setString(2, bookChapter.getDeptt().toUpperCase());
			ps.setInt(3, bookChapter.getChapterNo());
			ps.setString(4, bookChapter.getChapterTitle());
			ps.setString(5, bookChapter.getBookTitle());
			ps.setString(6, bookChapter.getPublisher());
			ps.setString(7, bookChapter.getNationality());
			ps.setInt(8, bookChapter.getYear());
			ps.setString(9, bookChapter.getMonthPublished());
			ps.setInt(10, bookChapter.getPageNo());
			ps.setString(11, bookChapter.getIsbn());
			ps.setString(12, bookChapter.getHyperLink());
			ps.setString(13, bookChapter.getIndexFlag());
			ps.setString(14, bookChapter.getIndexLink());
			ps.setInt(15, bookChapter.getStatus());
			ps.setString(16, bookChapter.getWrittenBy());
			String id;
			PreparedStatement ps1 = connection.prepareStatement("select id from book_chapter");
			ResultSet rs = ps1.executeQuery();
			ArrayList<Integer> list = new ArrayList<>();

			if (!rs.next()) {
				id = "BC0001";
			} else {
				rs.beforeFirst();
				while (rs.next()) {
					String result = rs.getString("id");
					list.add(Integer.parseInt(result.substring(1)));
				}
				int[] array = list.stream().mapToInt(i -> i).toArray();
				int sno = getMissing(array, array.length);
				id = String.format("BC%04d", sno);
			}
			ps.setString(17, id);
			ps.setString(18, bookChapter.getPublicationFileName());
			ps.setString(19, bookChapter.getPlagReportFileName());
			ps.setString(20, bookChapter.getPlagCopyFileName());
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
	public boolean updateBookChapter(BookChapter bookChapter) {
		if (bookChapter == null) {
			return false;
		}
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(
					"update book_chapter set nameOauthors=?, deptt=?, chapterNo=?, chapterTitle=?, bookTitle=?, publisher=?, nationality=?, year=?, monthPublished=?, pageNo=?, isbn=?, hyperLink=?, indexFlag=?, indexLink=?, status=?, written_by=?, publicationfilename=?,plagreportfilename=?, plagcopyfilename=? where id=?");
			ps.setString(1, bookChapter.getNameOauthors());
			ps.setString(2, bookChapter.getDeptt().toUpperCase());
			ps.setInt(3, bookChapter.getChapterNo());
			ps.setString(4, bookChapter.getChapterTitle());
			ps.setString(5, bookChapter.getBookTitle());
			ps.setString(6, bookChapter.getPublisher());
			ps.setString(7, bookChapter.getNationality());
			ps.setInt(8, bookChapter.getYear());
			ps.setString(9, bookChapter.getMonthPublished());
			ps.setInt(10, bookChapter.getPageNo());
			ps.setString(11, bookChapter.getIsbn());
			ps.setString(12, bookChapter.getHyperLink());
			ps.setString(13, bookChapter.getIndexFlag());
			ps.setString(14, bookChapter.getIndexLink());
			ps.setInt(15, bookChapter.getStatus());
			ps.setString(16, bookChapter.getWrittenBy());
			ps.setString(17, bookChapter.getPublicationFileName());
			ps.setString(18, bookChapter.getPlagReportFileName());
			ps.setString(19, bookChapter.getPlagCopyFileName());
			ps.setString(20, bookChapter.getId());
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
	public List<BookChapter> getAllBookChapters() throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		List<BookChapter> list = new ArrayList<>();
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select * from book_chapter");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BookChapter bc = new BookChapter();
				bc.setId(rs.getString("id"));
				bc.setPcn(rs.getString("pcn"));
				bc.setNameOauthors(rs.getString("nameOauthors"));
				bc.setDeptt(rs.getString("deptt").toUpperCase());
				bc.setChapterNo(rs.getInt("chapterNo"));
				bc.setChapterTitle(rs.getString("chapterTitle"));
				bc.setBookTitle(rs.getString("bookTitle"));
				bc.setPublisher(rs.getString("publisher"));
				bc.setNationality(rs.getString("nationality"));
				bc.setYear(rs.getInt("year"));
				bc.setMonthPublished(rs.getString("monthPublished"));
				bc.setMonthOfPCN(rs.getString("monthAssigned"));
				bc.setPageNo(rs.getInt("pageNo"));
				bc.setIsbn(rs.getString("isbn"));
				bc.setHyperLink(rs.getString("hyperLink"));
				bc.setIndexFlag(rs.getString("indexFlag"));
				bc.setIndexLink(rs.getString("indexLink"));
				bc.setStatus(rs.getInt("status"));
				bc.setWrittenBy(rs.getString("written_by"));
				list.add(bc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return list;
	}

	@Override
	public boolean delete(String id) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("delete from book_chapter where id=?");
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

	public int getMissing(int a[], int n) {
		int i;
		int total;
		total = (n + 1) * (n + 2) / 2;
		for (i = 1; i <= n; i++)
			total -= a[i - 1];
		return total;
	}

	@Override
	public BookChapter getBookChapterByID(String id) {
		Connection connection = null;
		PreparedStatement ps = null;
		BookChapter bc =null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select * from book_chapter where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				bc = new BookChapter();
				bc.setId(rs.getString("id"));
				bc.setPcn(rs.getString("pcn"));
				bc.setNameOauthors(rs.getString("nameOauthors"));
				bc.setDeptt(rs.getString("deptt").toUpperCase());
				bc.setChapterNo(rs.getInt("chapterNo"));
				bc.setChapterTitle(rs.getString("chapterTitle"));
				bc.setBookTitle(rs.getString("bookTitle"));
				bc.setPublisher(rs.getString("publisher"));
				bc.setNationality(rs.getString("nationality"));
				bc.setYear(rs.getInt("year"));
				bc.setMonthPublished(rs.getString("monthPublished"));
				bc.setMonthOfPCN(rs.getString("monthAssigned"));
				bc.setPageNo(rs.getInt("pageNo"));
				bc.setIsbn(rs.getString("isbn"));
				bc.setHyperLink(rs.getString("hyperLink"));
				bc.setIndexFlag(rs.getString("indexFlag"));
				bc.setIndexLink(rs.getString("indexLink"));
				bc.setStatus(rs.getInt("status"));
				bc.setWrittenBy(rs.getString("written_by"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return bc;
	}

	@Override
	public boolean action(String id, int status) {
		BookChapter chapter = getBookChapterByID(id);
		if (null == chapter) {
			return false;
		}
		Connection connection;
		PreparedStatement ps1;
		PreparedStatement ps2;
		ArrayList<Integer> list = new ArrayList<>();
		try {
			connection = ConnectionFactory.getConnection();
			ps1 = connection.prepareStatement("select pcn from book_chapter where pcn like \""
					+ chapter.getDeptt().toUpperCase() + "____%BC___\"");
			ResultSet rs = ps1.executeQuery();
			String pcn;
			if (!rs.next()) {
				pcn = GeneratePCN.generatePCN(chapter.getDeptt().toUpperCase(), "BC", 1);
			} else {
				rs.beforeFirst();
				while (rs.next()) {
					String result = rs.getString("pcn");
					list.add(Integer.parseInt(result.substring(9)));
				}
				int[] array = list.stream().mapToInt(i -> i).toArray();
				int sno = getMissing(array, array.length);
				pcn = GeneratePCN.generatePCN(chapter.getDeptt().toUpperCase(), "BC", sno);
			}
			ps2 = connection.prepareStatement("update book_chapter set pcn=?, status=?, monthAssigned=? where id=?");
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

		BookChapter bookChapter = getBookChapterByID(id);
		if (null == bookChapter) {
			return false;
		}
		Connection connection=null;
		PreparedStatement ps1;
		PreparedStatement ps;
		try {
			connection = ConnectionFactory.getConnection();
			ps1 = connection.prepareStatement("update rej_book_chapter set status=?, pcn=?, monthAssigned=? where id=?");
			ps = connection.prepareStatement("insert into rej_book_chapter (nameOauthors, deptt, chapterNo, chapterTitle, bookTitle, publisher, nationality, year, monthPublished, pageNo, isbn, hyperLink, indexFlag, indexLink, status, written_by,id, publicationfilename,plagreportfilename, plagcopyfilename, message) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, bookChapter.getNameOauthors());
			ps.setString(2, bookChapter.getDeptt().toUpperCase());
			ps.setInt(3, bookChapter.getChapterNo());
			ps.setString(4, bookChapter.getChapterTitle());
			ps.setString(5, bookChapter.getBookTitle());
			ps.setString(6, bookChapter.getPublisher());
			ps.setString(7, bookChapter.getNationality());
			ps.setInt(8, bookChapter.getYear());
			ps.setString(9, bookChapter.getMonthPublished());
			ps.setInt(10, bookChapter.getPageNo());
			ps.setString(11, bookChapter.getIsbn());
			ps.setString(12, bookChapter.getHyperLink());
			ps.setString(13, bookChapter.getIndexFlag());
			ps.setString(14, bookChapter.getIndexLink());
			ps.setInt(15, bookChapter.getStatus());
			ps.setString(16, bookChapter.getWrittenBy());
			ps.setString(17, id);
			ps.setString(18, bookChapter.getPublicationFileName());
			ps.setString(19, bookChapter.getPlagReportFileName());
			ps.setString(20, bookChapter.getPlagCopyFileName());
			ps.setString(21, message);
			ps1.setInt(1, status);
			ps1.setNull(2, Types.VARCHAR);
			ps1.setNull(3, Types.DATE);
			ps1.setString(4, id);
			if (ps1.executeUpdate() > 0 && ps.executeUpdate()>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int notificationRejectedBookChapters(String id) {
		Connection connection = null;
		PreparedStatement statement;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(
					"select distinct count(*) as number from book_chapter where status>0 and writtenby=?");
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
