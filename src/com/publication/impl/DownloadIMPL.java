package com.publication.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.publication.dao.DownloadDAO;
import com.publication.database.ConnectionFactory;

public class DownloadIMPL implements DownloadDAO {

	@Override
	public void downloadRequest(ServletOutputStream servletOutputStream, String[] what, String[] branch, String from,
			String to) {
		// TODO Auto-generated method stub
		XSSFWorkbook workbook = new XSSFWorkbook();

		for (String source : what) {
			switch (source) {
			case "bookChapter":
				downloadBookChapter(workbook, branch, from, to);
				break;
			case "journal":
				downloadJournal(workbook, branch, from, to);
				break;
			}
		}

		try {
			workbook.write(servletOutputStream);
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public XSSFSheet downloadBookChapter(XSSFWorkbook workbook, String[] branches, String from, String to) {

		XSSFSheet sheet = workbook.createSheet("Book Chapters");
		Connection connection = null;
		PreparedStatement ps1 = null;
		try {
			List<Object[]> list = new ArrayList<>();
			list.add(new Object[] { "PCN", "Name of authors", "Department", "Chapter No", "Chapter Title", "Book Title",
					"Publisher", "Nationality", "Year", "Month Published", "Month Assigned", "Page No", "ISBN",
					"Hyperlink", "Index Flag", "Index Link" });
			connection = ConnectionFactory.getConnection();
			ps1 = connection
					.prepareStatement("select * from book_chapter where deptt=? and monthAssigned between ? and ?");
			for (String branch : branches) {
				ps1.setString(1, branch);
				ps1.setString(2, from);
				ps1.setString(3, to);
				ResultSet rs = ps1.executeQuery();
				while (rs.next()) {
					list.add(new Object[] { rs.getString("pcn") == null ? "-" : rs.getString("pcn"),
							rs.getString("nameOauthors"), rs.getString("deptt").toUpperCase(), rs.getInt("chapterNo"),
							rs.getString("chapterTitle"), rs.getString("bookTitle"), rs.getString("publisher"),
							rs.getString("nationality"), rs.getInt("year"), rs.getString("monthPublished"),
							rs.getString("monthAssigned") == null ? "-" : rs.getString("monthAssigned"),
							rs.getInt("pageNo"), rs.getString("isbn"), rs.getString("hyperLink"),
							rs.getString("indexFlag"), rs.getString("indexLink") });
				}

			}

			int rowCount = 0;
			for (Object[] objs : list) {
				Row row = sheet.createRow(rowCount++);
				int colCount = 0;
				for (Object field : objs) {
					try {
						System.out.print(field.toString() + " ");
					} catch (NullPointerException e) {

					}
					Cell cell = row.createCell(colCount++);
					if (field instanceof String) {
						cell.setCellValue((String) field);
					}
					if (field instanceof Integer) {
						cell.setCellValue((Integer) field);
					}
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
			ps1 = null;
		}
		return sheet;
	}

	public XSSFSheet downloadJournal(XSSFWorkbook workbook, String[] branches, String from, String to) {
		XSSFSheet sheet = workbook.createSheet("Journals");
		Connection connection = null;
		PreparedStatement ps1 = null;
		try {
			List<Object[]> list = new ArrayList<>();
			list.add(new Object[] { "PCN", "Name Of Author", "Department", "Title", "Journal", "Nationality", "Year",
					"Month Published", "Month Assigned", "Volume", "Isse", "Page No", "DOI No", "Impact Factor",
					"Which Impact Factor", "Link for Impact Factor", "Paid or Unpaid", "Payment done or not", "PW",
					"PS", "PG", "PI" });
			connection = ConnectionFactory.getConnection();
			ps1 = connection.prepareStatement("select * from journal where deptt=? and monthAssigned between ? and ?");
			for (String branch : branches) {
				ps1.setString(1, branch);
				ps1.setString(2, from);
				ps1.setString(3, to);
				ResultSet rs = ps1.executeQuery();

				while (rs.next()) {
					try {
						list.add(new Object[] { rs.getString("pcn") == null ? "-" : rs.getString("pcn"),
								rs.getString("nameOauthors"), rs.getString("deptt"), rs.getString("title"),
								rs.getString("journal"), rs.getString("nationality"), rs.getInt("year"),
								rs.getString("monthPublished"),
								rs.getString("monthAssigned") == null ? "-" : rs.getString("monthAssigned"),
								rs.getInt("volume"), rs.getInt("issue"), rs.getInt("pageNo"), rs.getInt("doiNo"),
								rs.getString("impactFactor"), rs.getString("whatImpactFactor"),
								rs.getString("linkImpFactor"), rs.getString("paidOrUnpaid"),
								rs.getString("paymentFlag"), rs.getString("pwFlag"), rs.getString("psFlag"),
								rs.getString("pgFlag"), rs.getString("piFlag") });
					} catch (NullPointerException e) {
						e.printStackTrace();
					}

				}
			}

			int rowCount = 0;
			for (Object[] objs : list) {
				Row row = sheet.createRow(rowCount++);
				int colCount = 0;
				for (Object field : objs) {
					Cell cell = row.createCell(colCount++);
					if (field instanceof String) {
						cell.setCellValue((String) field);
					}
					if (field instanceof Integer) {
						cell.setCellValue((Integer) field);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
			ps1 = null;
		}
		return sheet;
	}

	@Override
	public String[] downloadJournalFilesByID(String id) {
		Connection connection =  null;
		PreparedStatement ps  = null;
		try{
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select publicationfilename,plagreportfilename, plagcopyfilename from journal where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return new String[]{rs.getString("publicationfilename"),rs.getString("plagreportfilename"),rs.getString("plagcopyfilename")};
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection);
		}
		return null;
	}

	@Override
	public String[] downloadBookChapterFilesByID(String id) {
		Connection connection =  null;
		PreparedStatement ps  = null;
		try{
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select publicationfilename,plagreportfilename, plagcopyfilename from book_chapter where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return new String[]{rs.getString("publicationfilename"),rs.getString("plagreportfilename"),rs.getString("plagcopyfilename")};
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection);
		}
		return null;
	}

	@Override
	public String[] downloadBookFilesByID(String id) {
		Connection connection =  null;
		PreparedStatement ps  = null;
		try{
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select publicationfilename,plagreportfilename, plagcopyfilename from book where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return new String[]{rs.getString("publicationfilename"),rs.getString("plagreportfilename"),rs.getString("plagcopyfilename")};
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection);
		}
		return null;
	}

	@Override
	public String[] downloadConferencePresentationFilesByID(String id) {
		Connection connection =  null;
		PreparedStatement ps  = null;
		try{
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select publicationfilename,plagreportfilename from conf_pres  where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return new String[]{rs.getString("publicationfilename"),rs.getString("plagreportfilename")};
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection);
		}
		return null;
	}

	@Override
	public String[] downloadConferenceProceedingFilesByID(String id) {
		Connection connection =  null;
		PreparedStatement ps  = null;
		try{
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select publicationfilename,plagreportfilename, plagcopyfilename from conf_proc where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return new String[]{rs.getString("publicationfilename"),rs.getString("plagreportfilename"),rs.getString("plagcopyfilename")};
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection);
		}
		return null;
	}

	@Override
	public String[] downloadPatentFilesByID(String id) {
		Connection connection =  null;
		PreparedStatement ps  = null;
		try{
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select publicationfilename,plagreportfilename from patent where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return new String[]{rs.getString("publicationfilename"),rs.getString("plagreportfilename")};
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection);
		}
		return null;
	}

	@Override
	public String[] downloadTechnicalReportFilesByID(String id) {
		Connection connection =  null;
		PreparedStatement ps  = null;
		try{
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select publicationfilename,plagreportfilename, plagcopyfilename from tech_rep where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return new String[]{rs.getString("publicationfilename"),rs.getString("plagreportfilename"),rs.getString("plagcopyfilename")};
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection);
		}
		return null;
	}

}
