package com.publication.requestmap;

import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;

import com.publication.model.TechnicalReport;

public class TechnicalReportMap {

	public static TechnicalReport technicalReportFormMap(TechnicalReport report, List<FileItem> fileItems) {
		for (FileItem item : fileItems) {

			switch (item.getFieldName()) {
			case "faculty":
				report.setFaculty(item.getString());
				break;
			case "deptt":
				report.setDeptt(item.getString());
				break;
			case "title":
				report.setTitle(item.getString());
				break;
			case "year":
				report.setYear(Integer.parseInt(item.getString()));
				break;
			case "date":
				report.setDate(item.getString());
				break;
			case "remarks":
				report.setRemarks(item.getString());
				break;
			case "monthPublished":
				report.setMonthPublished(item.getString());
				break;
			case "writtenBy":
				report.setWrittenBy(item.getString());
				break;
			case "status":
				report.setStatus(Integer.parseInt(item.getString()));
				break;
			case "publication":
				report.setPublicationFileName(FilenameUtils.getName(item.getName()));
				break;
			case "plagReport":
				report.setPlagReportFileName(FilenameUtils.getName(item.getName()));
				break;
			case "plagCopy":
				report.setPlagCopyFileName(FilenameUtils.getName(item.getName()));
				break;
			case "certificate":
				report.setCertificateName(FilenameUtils.getName(item.getName()));
				break;
			
			}
		}

		return report;
	}

}
