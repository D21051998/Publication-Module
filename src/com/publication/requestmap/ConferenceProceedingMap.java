package com.publication.requestmap;

import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;

import com.publication.model.ConferenceProceedings;


public class ConferenceProceedingMap {
	
	static String indices="";

	public static ConferenceProceedings conferenceProceedingFormMap(ConferenceProceedings conferenceProceeding, List<FileItem> fileItems) {
		for (FileItem item : fileItems) {

			switch (item.getFieldName()) {

			case "nameOauthors":
				conferenceProceeding.setNameOauthors(item.getString());
				break;
			case "deptt":
				conferenceProceeding.setDeptt(item.getString());
				break;
			case "title":
				conferenceProceeding.setTitle(item.getString());
				break;
			case "proceedingsOf":
				conferenceProceeding.setProceedingsOf(item.getString());
				break;
			case "nationality":
				conferenceProceeding.setNationality(item.getString());
				break;
			case "venue":
				conferenceProceeding.setVenue(item.getString());
				break;
			case "year":
				conferenceProceeding.setYear(Integer.parseInt(item.getString()));
				break;
			case "monthPublished":
				conferenceProceeding.setMonthPublished(item.getString());
				break;
			case "publisher":
				conferenceProceeding.setPublisher(item.getString());
				break;
			case "pageNo":
				conferenceProceeding.setPageNo((item.getString()));
				break;
			case "hyperLink":
				conferenceProceeding.setHyperlink(item.getString());
				break;
			case "index":
				if(indices.isEmpty()){
					indices = indices+item.getString();
				}else{
					indices = indices+", "+item.getString();
				}
			break;
			case "link":
				conferenceProceeding.setLink(item.getString());
				break;
			case "writtenBy":
				conferenceProceeding.setWrittenBy(item.getString());
				break;
			case "status":
				conferenceProceeding.setStatus(Integer.parseInt(item.getString()));
				break;
			case "publication":
				conferenceProceeding.setPublicationFileName(FilenameUtils.getName(item.getName()));
				break;
			case "plagReport":
				conferenceProceeding.setPlagReportFileName(FilenameUtils.getName(item.getName()));
				break;
			case "plagCopy":
				conferenceProceeding.setPlagCopyFileName(FilenameUtils.getName(item.getName()));
				break;
			case "certificate":
				conferenceProceeding.setCertificateName(FilenameUtils.getName(item.getName()));
				break;
			
			}
		}
		conferenceProceeding.setIndex(indices);
		indices="";
		return conferenceProceeding;
	}
	

}
