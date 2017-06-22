package com.publication.impl;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.publication.dao.DownloadDAO;
import com.publication.database.ConnectionFactory;

public class DowloadIMPL implements DownloadDAO {

	@Override
	public void downloadRequest(String[] what, String[] branch, Date from, Date to) {
		// TODO Auto-generated method stub
		XSSFWorkbook workbook = new XSSFWorkbook();
		Map<String, XSSFSheet> map = new HashMap<String, XSSFSheet>();
		map.put("Book Chapter", downloadBookChapter(workbook, from, to));

	}

	public XSSFSheet downloadBookChapter(XSSFWorkbook workbook, Date from, Date to) {

		XSSFSheet sheet = workbook.createSheet("Book Chapters");
		Connection connection = null;
		PreparedStatement ps1 = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps1 = connection.prepareStatement("select * from book_chapter between ? and ?");
			long millis=System.currentTimeMillis();  
			Date date = new Date(millis);
			
			ResultSet rs = ps1.executeQuery();
			if (!rs.next()) {
				return sheet;
			} else {
				List<Object[]> list = new ArrayList<>();
				list.add(new Object[] { "PCN", "Name of authors", "Department", "Chapter No", "Chapter Title",
						"Book Title", "Publisher", "Nationality", "Year", "Month Published", "Month Assigned",
						"Page No", "ISBN", "Hyperlink", "Index Flag", "Index Link" });
				rs.beforeFirst();
				while (rs.next()) {

					list.add(new Object[] { rs.getString("pcn")==null?"-":rs.getString("pcn")
							, rs.getString("nameOauthors"),
							rs.getString("deptt").toUpperCase(), rs.getInt("chapterNo"), rs.getString("chapterTitle"),
							rs.getString("bookTitle"), rs.getString("publisher"), rs.getString("nationality"),
							rs.getInt("year"), rs.getString("monthPublished"), 
							rs.getString("monthAssigned") == null?"-":rs.getString("monthAssigned"),
							rs.getInt("pageNo"), rs.getString("isbn"), rs.getString("hyperLink"),
							rs.getString("indexFlag"), rs.getString("indexLink") });
				}
				int rowCount = 0;
				for (Object[] objs : list) {
					Row row = sheet.createRow(rowCount++);
					int colCount = 0;
					for (Object field : objs) {
						try{
							System.out.print(field.toString()+" ");
						}catch(NullPointerException e){
							
						}
						Cell cell = row.createCell(colCount++);
						if (field instanceof String) {
							cell.setCellValue((String) field);
						}
						if (field instanceof Integer) {
							cell.setCellValue((Integer) field);
						}
					}System.out.println();
				}
				return sheet;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
			ps1 = null;
		}
		return sheet;
	}

	public XSSFSheet downloadJournal(XSSFWorkbook workbook, Date from, Date to) {
		XSSFSheet sheet = workbook.createSheet("Journals");
		Connection connection = null;
		PreparedStatement ps1 = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps1 = connection.prepareStatement("select * from journal");
			ResultSet rs = ps1.executeQuery();
			if (!rs.next()) {
				return sheet;
			} else {
				List<Object[]> list = new ArrayList<>();
				list.add(new Object[] { "PCN", "Name Of Author", "Department", "Title", "Journal", "Nationality",
						"Year", "Month Published", "Month Assigned", "Volume", "Isse", "Page No", "DOI No",
						"Impact Factor", "Which Impact Factor", "Link for Impact Factor", "Paid or Unpaid",
						"Payment done or not", "PW", "PS", "PG", "PI" });

				rs.beforeFirst();
				while (rs.next()) {
					try{
						list.add(new Object[] { rs.getString("pcn")==null?"-":rs.getString("pcn")
								, rs.getString("nameOauthors"), rs.getString("deptt"),
								rs.getString("title"), rs.getString("journal"), rs.getString("nationality"),
								rs.getInt("year"), rs.getString("monthPublished")
								, rs.getString("monthAssigned") == null?"-":rs.getString("monthAssigned"),
								rs.getInt("volume"), rs.getInt("issue"), rs.getInt("pageNo"), rs.getInt("doiNo"),
								rs.getString("impactFactor"), rs.getString("whatImpactFactor"),
								rs.getString("linkImpFactor"), rs.getString("paidOrUnpaid"), rs.getString("paymentFlag"),
								rs.getString("pwFlag"), rs.getString("psFlag"), rs.getString("pgFlag"),
								rs.getString("piFlag") });
					}catch (NullPointerException e) {
						e.printStackTrace();// TODO: handle exception
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
				return sheet;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
			ps1 = null;
		}
		return sheet;
	}

	public static void main(String[] args) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		DowloadIMPL dao = new DowloadIMPL();
		dao.downloadBookChapter(workbook, null, null);
		dao.downloadJournal(workbook, null, null);
		try (FileOutputStream fos = new FileOutputStream("C:\\Users\\Deepanshu Jain\\Desktop\\abc.xlsx")) {
			workbook.write(fos);
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
