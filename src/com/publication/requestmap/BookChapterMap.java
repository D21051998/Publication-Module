package com.publication.requestmap;

import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;

import com.publication.model.BookChapter;

public class BookChapterMap {
	static String indices = "";

	public static BookChapter bookChapterFormMap(BookChapter chapter, List<FileItem> fileItems) {
		for (FileItem item : fileItems) {

			switch (item.getFieldName()) {

			case "nameOauthors":
				chapter.setNameOauthors(item.getString());
				break;
			case "deptt":
				chapter.setDeptt(item.getString());
				break;

			case "chapterNo":
				chapter.setChapterNo(Integer.parseInt(item.getString()));
				break;

			case "chapterTitle":
				chapter.setChapterTitle(item.getString());
				break;
			case "bookTitle":
				chapter.setBookTitle(item.getString());
				break;
			case "publisher":
				chapter.setPublisher(item.getString());
				break;
			case "nationality":
				chapter.setNationality(item.getString());
				break;
			case "year":
				chapter.setYear(Integer.parseInt(item.getString()));
				break;
			case "monthPublished":
				chapter.setMonthPublished(item.getString());
				break;
			case "pageNo":
				chapter.setPageNo(item.getString());
				break;
			case "isbn":
				chapter.setIsbn(item.getString());
				break;
			case "hyperLink":
				chapter.setHyperLink(item.getString());
				break;
			case "indexFlag":
				if (indices.isEmpty()) {
					indices = indices + item.getString();
				} else {
					indices = indices + ", " + item.getString();
				}
			case "indexLink":
				chapter.setIndexLink(item.getString());
				break;
			case "writtenBy":
				chapter.setWrittenBy(item.getString());
				break;
			case "status":
				chapter.setStatus(Integer.parseInt(item.getString()));
				break;
			case "publication":
				chapter.setPublicationFileName(FilenameUtils.getName(item.getName()));
				break;
			case "plagReport":
				chapter.setPlagReportFileName(FilenameUtils.getName(item.getName()));
				break;
			case "plagCopy":
				chapter.setPlagCopyFileName(FilenameUtils.getName(item.getName()));
				break;
			case "certificate":
				chapter.setCertificateName(FilenameUtils.getName(item.getName()));
				break;

			}
		}
		chapter.setIndexFlag(indices);
		indices="";
		return chapter;
	}

}
