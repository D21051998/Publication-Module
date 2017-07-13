package com.publication.requestmap;

import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;

import com.publication.model.ConferencePresentation;

public class ConferencePresentationMap {

	public static ConferencePresentation conferencePresentationFormMap(ConferencePresentation cp,
			List<FileItem> fileItems) {
		for (FileItem item : fileItems) {

			switch (item.getFieldName()) {

			case "faculty":
				cp.setFaculty(item.getString());
				break;
			case "conferencePresentation":
				cp.setConferencePresentation(item.getString());
				break;
			case "organisedBy":
				cp.setOrganisedBy(item.getString());
				break;
			case "venue":
				cp.setVenue(item.getString());
				break;
			case "dates":
				cp.setDates(item.getString());
				break;
			case "hyperlink":
				cp.setHyperlink(item.getString());
				break;
			case "monthPublished":
				cp.setMonthPublished(item.getString());
				break;
			case "deptt":
				cp.setDeptt(item.getString());
				break;
			case "title":
				cp.setTitle(item.getString());
				break;
			case "nationality":
				cp.setNationality(item.getString());
				break;
			case "year":
				cp.setYear(Integer.parseInt(item.getString()));
				break;
			case "writtenBy":
				cp.setWrittenBy(item.getString());
				break;
			case "status":
				cp.setStatus(Integer.parseInt(item.getString()));

			case "publication":
				cp.setPublicationFileName(FilenameUtils.getName(item.getName()));
				break;
			case "plagReport":
				cp.setPlagReportFileName(FilenameUtils.getName(item.getName()));
				break;
			}
		}

		return cp;
	}

}
