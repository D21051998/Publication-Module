package com.publication.requestmap;

import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;
import com.publication.model.Patent;

public class PatentMap {

	public static Patent patentFormMap(Patent patent, List<FileItem> fileItems) {
		for (FileItem item : fileItems) {

			switch (item.getFieldName()) {

			case "faculty":
				patent.setFaculty(item.getString());
				break;
			case "deptt":
				patent.setDeptt(item.getString());
				break;
			case "title":
				patent.setTitle(item.getString());
				break;
			case "nationality":
				patent.setNationality(item.getString());
				break;
			case "country":
				patent.setCountry(item.getString());
				break;
			case "applicationNo":
				patent.setApplicationNo(item.getString());
				break;
			case "applicationYear":
				patent.setApplicationYear(Integer.parseInt(item.getString()));
				break;
			case "applicationDate":
				patent.setApplicationDate(item.getString());
				break;
			case "patentYear":
				patent.setPatentYear(Integer.parseInt(item.getString()));
				break;
			case "awardDate":
				patent.setAwardDate(item.getString());
				break;
			case  "patentNo":patent.setPatentNo(Integer.parseInt(item.getString()));
				break;
			case "publication":
				patent.setPublicationFileName(FilenameUtils.getName(item.getName()));
				break;
			case "plagReport":
				patent.setPlagReportFileName(FilenameUtils.getName(item.getName()));
				break;
			case "status":
				patent.setStatus(Integer.parseInt(item.getString()));
				break;
			case "writtenBy":
				patent.setWrittenBy(item.getString());
				break;
			}
		}
		return patent;
	}

}
