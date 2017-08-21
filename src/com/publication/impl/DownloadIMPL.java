package com.publication.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.publication.dao.DownloadDAO;
import com.publication.database.ConnectionFactory;

public class DownloadIMPL implements DownloadDAO {

	@Override
	public void downloadRequest(ServletOutputStream servletOutputStream, String[] what, String[] branch, String from,
			String to) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		for (String source : what) {
			switch (source) {
			case "bookChapter":
				downloadBookChapter(workbook, branch, from, to);
				break;
			case "journal":
				downloadJournal(workbook, branch, from, to);
				break;
			case "books":
				downloadBook(workbook, branch, from, to);
				break;
			case "patent":
				downloadPatent(workbook, branch, from, to);
				break;
			case "techRep":
				downloadTechnicalReport(workbook, branch, from, to);
				break;
			case "confPresentation":
				downloadConferencePresentation(workbook, branch, from, to);
				break;
			case "confProceeding":
				downloadConferenceProceedings(workbook, branch, from, to);
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
			sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 23));
			CellStyle cs = workbook.createCellStyle();
			cs.setWrapText(true);
			Row header = sheet.createRow(0);
			header.createCell(0);
			header.getCell(0).setCellStyle(cs);
			header.getCell(0).setCellValue(
					"Note:\n1. Names of NCU Authors in the sequence as mentioned in Book Chapter should be in bold to distinguish from outside faculty/ Scholars.  Put * for corresponding author.");
			list.add(new Object[] { "S.No", "PCN", "Name of authors", "Department", "Chapter No", "Chapter Title",
					"Book Title", "Publisher", "Nationality", "Year", "Month Published", "Month Assigned", "Page No",
					"ISBN", "Hyperlink", "Index Flag", "Index Link" });
			connection = ConnectionFactory.getConnection();
			ps1 = connection
					.prepareStatement("select * from book_chapter where deptt=? and monthAssigned between ? and ?");
			int sno = 1;
			for (String branch : branches) {
				ps1.setString(1, branch);
				ps1.setString(2, from);
				ps1.setString(3, to);
				ResultSet rs = ps1.executeQuery();
				while (rs.next()) {
					list.add(new Object[] { Integer.toString(sno),
							rs.getString("pcn") == null ? "-" : rs.getString("pcn"), rs.getString("nameOauthors"),
							rs.getString("deptt").toUpperCase(), rs.getInt("chapterNo"), rs.getString("chapterTitle"),
							rs.getString("bookTitle"), rs.getString("publisher"), rs.getString("nationality"),
							rs.getInt("year"), rs.getString("monthPublished"),
							rs.getString("monthAssigned") == null ? "-" : rs.getString("monthAssigned"),
							rs.getInt("pageNo"), rs.getString("isbn"), rs.getString("hyperLink"),
							rs.getString("indexFlag"), rs.getString("indexLink") });
				}
			}
			int rowCount = 3;
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
	
	public XSSFSheet downloadTechnicalReport(XSSFWorkbook workbook, String[] branches, String from, String to) {
		XSSFSheet sheet = workbook.createSheet("Technical Reports");
		Connection connection = null;
		PreparedStatement ps1 = null;
		try {
			List<Object[]> list = new ArrayList<>();
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 10));
			list.add(new Object[] { "S.No", "PCN", "Faculty", "Department", "Title of Report", "Year",
            "Month Published", "Month Assigned", "Date","Remarks"});
			connection = ConnectionFactory.getConnection();
			ps1 = connection
					.prepareStatement("select * from tech_rep where deptt=? and monthAssigned between ? and ?");
			int sno = 1;
			for (String branch : branches) {
				ps1.setString(1, branch);
				ps1.setString(2, from);
				ps1.setString(3, to);
				ResultSet rs = ps1.executeQuery();
				while (rs.next()) {
					list.add(new Object[] { Integer.toString(sno),
							rs.getString("pcn") == null ? "-" : rs.getString("pcn"), rs.getString("faculty"),
							rs.getString("deptt").toUpperCase(), rs.getString("title"), rs.getString("year"), rs.getString("monthPublished"),
							rs.getString("monthAssigned") == null ? "-" : rs.getString("monthAssigned"),
							rs.getString("date"), rs.getString("remarks")});
				}
			}
			int rowCount = 2;
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

	//TO BE CHANGED
	
	public XSSFSheet downloadConferencePresentation(XSSFWorkbook workbook, String[] branches, String from, String to) {
		XSSFSheet sheet = workbook.createSheet("Conference Presentation");
		Connection connection = null;
		PreparedStatement ps1 = null;
		try {
			List<Object[]> list = new ArrayList<>();
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 10));
			list.add(new Object[] { "S.No", "PCN", "Faculty", "Department", "Title", "Year","Conference Presentation","International/National","Organised By"
            , "Venue","Year","Dates","Hyperlink","Month Published", "Month Assigned"});
			connection = ConnectionFactory.getConnection();
			ps1 = connection
					.prepareStatement("select * from tech_rep where deptt=? and monthAssigned between ? and ?");
			int sno = 1;
			for (String branch : branches) {
				ps1.setString(1, branch);
				ps1.setString(2, from);
				ps1.setString(3, to);
				ResultSet rs = ps1.executeQuery();
				while (rs.next()) {
					list.add(new Object[] { Integer.toString(sno),
							rs.getString("pcn") == null ? "-" : rs.getString("pcn"), rs.getString("faculty"),
							rs.getString("deptt").toUpperCase(), rs.getString("title"), rs.getString("year"),
							rs.getString("conferencePresentation"),rs.getString("nationality"),rs.getString("organisedBy"), rs.getString("venue"), rs.getString("year"),rs.getString("dates"),
							rs.getString("hyperlink") ,rs.getString("monthPublished"),
							rs.getString("monthAssigned") == null ? "-" : rs.getString("monthAssigned")});
				}
			}
			int rowCount = 2;
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

	
	public XSSFSheet downloadConferenceProceedings(XSSFWorkbook workbook, String[] branches, String from, String to) {

		XSSFSheet sheet = workbook.createSheet("Conference Proceedings");
		Connection connection = null;
		PreparedStatement ps1 = null;
		try {
			List<Object[]> list = new ArrayList<>();
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 10));
			list.add(new Object[] { "S.No", "PCN", "Name of Authors", "Department", "Title of Report", "Conference Proceedings","International/National", "Venue", "Year", 
            "Month Published", "Month Assigned","publisher", "pageNo","hyperlink","index" ,"link"});
			connection = ConnectionFactory.getConnection();
			ps1 = connection
					.prepareStatement("select * from conf_proc where deptt=? and monthAssigned between ? and ?");
			int sno = 1;
			for (String branch : branches) {
				ps1.setString(1, branch);
				ps1.setString(2, from);
				ps1.setString(3, to);
				ResultSet rs = ps1.executeQuery();
				while (rs.next()) {
					list.add(new Object[] { Integer.toString(sno),
							rs.getString("pcn") == null ? "-" : rs.getString("pcn"), rs.getString("nameOauthors"),
							rs.getString("deptt").toUpperCase(), rs.getString("title"), rs.getString("proceedingsOf"), rs.getString("nationality"), rs.getString("venue") ,rs.getString("year"), rs.getString("monthPublished"),
							rs.getString("monthAssigned") == null ? "-" : rs.getString("monthAssigned"),rs.getString("publisher"), rs.getString("pageNo"), rs.getString("hyperlink"),
							rs.getString("index"), rs.getString("link")});
				}
			}
			int rowCount = 2;
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
			sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 23));
			CellStyle cs = workbook.createCellStyle();
			cs.setWrapText(true);
			Row header = sheet.createRow(0);
			header.createCell(0);
			header.getCell(0).setCellStyle(cs);
			header.getCell(0).setCellValue(
					"Note:\n1. Filling up of all columns is mandatory. Also, mention Impact Factor (SCI, GIF, SJR, etc) of the journals."
							+ "\n2. Names of NCU Authors in the sequence as mentioned in Journal should be in bold to distinguish from outside faculty/ Scholars. Put * for corresponding author.");
			list.add(new Object[] { "S.No.", "PCN", "Name Of Author", "Department", "Title", "Journal", "Nationality",
					"Year", "Month Published", "Month Assigned", "Volume", "Isse", "Page No", "DOI No", "Impact Factor",
					"Which Impact Factor", "Link for Impact Factor", "Paid or Unpaid", "Payment done or not", "PW",
					"PS", "PG", "PI" });
			connection = ConnectionFactory.getConnection();
			ps1 = connection.prepareStatement("select * from journal where deptt=? and monthAssigned between ? and ?");
			int sno = 1;
			for (String branch : branches) {
				ps1.setString(1, branch);
				ps1.setString(2, from);
				ps1.setString(3, to);
				ResultSet rs = ps1.executeQuery();

				while (rs.next()) {
					try {
						list.add(new Object[] { Integer.toString(sno),
								rs.getString("pcn") == null ? "-" : rs.getString("pcn"), rs.getString("nameOauthors"),
								rs.getString("deptt"), rs.getString("title"), rs.getString("journal"),
								rs.getString("nationality"), rs.getInt("year"), rs.getString("monthPublished"),
								rs.getString("monthAssigned") == null ? "-" : rs.getString("monthAssigned"),
								rs.getInt("volume"), rs.getInt("issue"), rs.getInt("pageNo"), rs.getInt("doiNo"),
								rs.getString("impactFactor"), rs.getString("whatImpactFactor"),
								rs.getString("linkImpFactor"), rs.getString("paidOrUnpaid"),
								rs.getString("paymentFlag"), rs.getString("pwFlag"), rs.getString("psFlag"),
								rs.getString("pgFlag"), rs.getString("piFlag") });
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
					sno++;
				}

			}

			int rowCount = 3;
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
	
	

	public XSSFSheet downloadBook(XSSFWorkbook workbook, String[] branches, String from, String to) {

		XSSFSheet sheet = workbook.createSheet("Books");
		Connection connection = null;
		PreparedStatement ps1 = null;
		try {
			List<Object[]> list = new ArrayList<>();
			sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 23));
			CellStyle cs = workbook.createCellStyle();
			cs.setWrapText(true);
			Row header = sheet.createRow(0);
			header.createCell(0);
			header.getCell(0).setCellStyle(cs);
			header.getCell(0).setCellValue("Note:\n1. Names of NCU Authors in the sequence as mentioned in Book should be in bold to distinguish from outside faculty/ Scholars. Put * for corresponding author.");
			list.add(new Object[] { "S.No", "PCN", "Name of authors", "Department", "Book Title", "Publisher",
					"Nationality", "Year", "Month Published", "Month Assigned", "Page No",
					"ISBN", "Hyperlink", "Index Flag", "Index Link" });
			connection = ConnectionFactory.getConnection();
			ps1 = connection
					.prepareStatement("select * from book where deptt=? and monthAssigned between ? and ?");
			int sno = 1;
			for (String branch : branches) {
				ps1.setString(1, branch);
				ps1.setString(2, from);
				ps1.setString(3, to);
				ResultSet rs = ps1.executeQuery();
				while (rs.next()) {
					list.add(new Object[] { Integer.toString(sno),
							rs.getString("pcn") == null ? "-" : rs.getString("pcn"), rs.getString("nameOauthors"),
							rs.getString("deptt").toUpperCase(), rs.getString("title"),rs.getString("publisher"), rs.getString("nationality"),
							rs.getInt("year"), rs.getString("monthPublished"),
							rs.getString("monthAssigned") == null ? "-" : rs.getString("monthAssigned"),
							rs.getInt("pageNo"), rs.getString("isbn"), rs.getString("hyperLink"),
							rs.getString("indices"), rs.getString("link") });
				}
			}
			int rowCount = 3;
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
	public XSSFSheet downloadPatent(XSSFWorkbook workbook, String[] branches, String from, String to) {

		XSSFSheet sheet = workbook.createSheet("Patents");
		Connection connection = null;
		PreparedStatement ps1 = null;
		try {
			List<Object[]> list = new ArrayList<>();
			list.add(new Object[] { "S.No", "PCN", "Faculty", "Department", "Title of Patent", "International/ National",
					"Country", "Patent Application no.", "Patent Application Year", "Patent Application Date", "Patent Award Year",
					"Patent Award Date", "Patent no."});
			connection = ConnectionFactory.getConnection();
			ps1 = connection
					.prepareStatement("select * from patent where deptt=? and monthAssigned between ? and ?");
			int sno = 1;
			for (String branch : branches) {
				ps1.setString(1, branch);
				ps1.setString(2, from);
				ps1.setString(3, to);
				ResultSet rs = ps1.executeQuery();
				while (rs.next()) {
					list.add(new Object[] { Integer.toString(sno),
							rs.getString("pcn") == null ? "-" : rs.getString("pcn"), rs.getString("faculty"),
							rs.getString("deptt").toUpperCase(), rs.getString("title"),rs.getString("nationality"), rs.getString("country"),
							rs.getInt("applicationNo"), rs.getString("applicationYear"),
							rs.getString("applicationDate") == null ? "-" : rs.getString("patentYear"),
							rs.getInt("patentNo"), rs.getString("awardDate") });
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


	@Override
	public String[] downloadJournalFilesByID(String id) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(
					"select publicationfilename,plagreportfilename, plagcopyfilename,certificateName from journal where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new String[] { rs.getString("publicationfilename"), rs.getString("plagreportfilename"),
						rs.getString("plagcopyfilename"),rs.getString("certificateName") };
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return null;
	}

	@Override
	public String[] downloadBookChapterFilesByID(String id) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(
					"select publicationfilename,plagreportfilename, plagcopyfilename,certificateName from book_chapter where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new String[] { rs.getString("publicationfilename"), rs.getString("plagreportfilename"),
						rs.getString("plagcopyfilename"),rs.getString("certificateName") };
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return null;
	}

	@Override
	public String[] downloadBookFilesByID(String id) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(
					"select publicationfilename,plagreportfilename, plagcopyfilename,certificateName from book where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new String[] { rs.getString("publicationfilename"), rs.getString("plagreportfilename"),
						rs.getString("plagcopyfilename"), rs.getString("certificateName") };
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return null;
	}

	@Override
	public String[] downloadConferencePresentationFilesByID(String id) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection
					.prepareStatement("select publicationfilename,plagreportfilename from conf_pres  where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new String[] { rs.getString("publicationfilename"), rs.getString("plagreportfilename"),"","" };
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return null;
	}

	@Override
	public String[] downloadConferenceProceedingFilesByID(String id) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(
					"select publicationfilename,plagreportfilename, plagcopyfilename,certificateName from conf_proc where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new String[] { rs.getString("publicationfilename"), rs.getString("plagreportfilename"),
						rs.getString("plagcopyfilename") ,rs.getString("certificateName")};
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return null;
	}

	@Override
	public String[] downloadPatentFilesByID(String id) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select publicationfilename,plagreportfilename,certificateName from patent where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new String[] { rs.getString("publicationfilename"), rs.getString("plagreportfilename"),"" ,rs.getString("certificateName") };
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return null;
	}

	@Override
	public String[] downloadTechnicalReportFilesByID(String id) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(
					"select publicationfilename,plagreportfilename, plagcopyfilename,certificateName from tech_rep where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new String[] { rs.getString("publicationfilename"), rs.getString("plagreportfilename"),
						rs.getString("plagcopyfilename"),rs.getString("certificateName") };
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return null;
	}

}
