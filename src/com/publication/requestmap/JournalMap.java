package com.publication.requestmap;

import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;

import com.publication.model.Journal;

public class JournalMap {

	public static Journal journalFormMap(Journal journal, List<FileItem> fileItems) {
		for (FileItem item : fileItems) {

			switch (item.getFieldName()) {

			case "nameOauthors":
				journal.setNameOauthors(item.getString());
				break;
			case "deptt":
				journal.setDeptt(item.getString());
				break;
			case "title":
				journal.setTitle(item.getString());
				break;
			case "journal":
				journal.setJournal(item.getString());
				break;
			case "nationality":
				journal.setNationality(item.getString());
				break;
			case "year":
				journal.setYear(Integer.parseInt(item.getString()));
				break;
			case "monthPublished":
				journal.setMonthPublished(item.getString());
				break;
			case "volume":
				journal.setVolume(Integer.parseInt(item.getString()));
				break;
			case "issue":
				journal.setIssue(Integer.parseInt(item.getString()));
				break;
			case "pageNo":
				journal.setPageNo(Integer.parseInt(item.getString()));
				break;
			case "doiNo":
				journal.setDoiNo(Integer.parseInt(item.getString()));
				break;
			case "impactFactor":
				journal.setImpactFactor(item.getString());
				break;
			case "whatImpactFactor":
				journal.setWhatImpactFactor(item.getString());
				break;
			case "linkImpFactor":
				journal.setLinkImpFactor(item.getString());
				break;
			case "paidOrUnpaid":
				journal.setPaidOrUnpaid(item.getString());
				break;
			case "paymentFlag":
				journal.setPaymentFlag(item.getString());
				break;
			case "pwFlag":
				journal.setPwFlag(item.getString());
				break;
			case "psFlag":
				journal.setPsFlag(item.getString());
				break;
			case "pgFlag":
				journal.setPgFlag(item.getString());
				break;
			case "piFlag":
				journal.setPiFlag(item.getString());
				break;
			case "writtenBy":
				journal.setWrittenBy(item.getString());
				break;
			case "status":
				journal.setStatus(Integer.parseInt(item.getString()));
				break;
			case "publication":
				journal.setPublicationFileName(FilenameUtils.getName(item.getName()));
				break;
			case "plagReport":
				journal.setPlagReportFileName(FilenameUtils.getName(item.getName()));
				break;
			case "plagCopy":
				journal.setPlagCopyFileName(FilenameUtils.getName(item.getName()));
				break;
			}
		}

		return journal;
	}

	
}
