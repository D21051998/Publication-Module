package com.publication.requestmap;

import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;

import com.publication.model.Books;

public class BookMap {
	static String indices="";

	public static Books bookFormMap(Books book, List<FileItem> fileItems) {
		for (FileItem item : fileItems) {

			switch (item.getFieldName()) {
			case "nameOauthors": book.setNameOauthors(item.getString());
			break;
			case "deptt": book.setDeptt(item.getString());
			break;
			case "title": book.setTitle(item.getString());
			break;
			case "publisher":book.setPublisher(item.getString());
			break;
			case "nationality":book.setNationality(item.getString());
				break;
			case "year":book.setYear(Integer.parseInt(item.getString()));
				break;
			case "monthPublished":book.setMonthPublished(item.getString());
				break;
			case "pageNo":book.setPageNo((item.getString()));
				break;
			case "isbn":book.setIsbn(item.getString());
				break;
			case "hyperLink":book.setHyperlink(item.getString());
				break;
			case "indexFlag":if(indices.isEmpty()){
				indices = indices+item.getString();
			}else{
				indices = indices+", "+item.getString();
			}
			break;
			case "indexLink":book.setLink(item.getString());
				break;
			case "publication":book.setPublicationFileName(FilenameUtils.getName(item.getName()));
				break;
			case "plagReport":book.setPlagReportFileName(FilenameUtils.getName(item.getName()));
				break;
			case "plagCopy":book.setPlagCopyFileName(FilenameUtils.getName(item.getName()));
				break;
			case "status":book.setStatus(Integer.parseInt(item.getString()));
				break;
			case "writtenBy":book.setWrittenBy(item.getString());
				break;
			case "certificate":
				book.setCertificateName(FilenameUtils.getName(item.getName()));
				break;
			}
		}
		book.setIndex(indices);
		indices="";
		return book;
	}

}
