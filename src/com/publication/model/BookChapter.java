package com.publication.model;

public class BookChapter {

	private String id;
	private String pcn;
	private String nameOauthors;
	private String deptt;
	private int chapterNo;
	private String chapterTitle;
	private String bookTitle;
	private String publisher;
	private String nationality;
	private int year;
	private String monthPublished;
	private String monthOfPCN;
	private int pageNo;
	private String isbn;
	private String hyperLink;
	private String indexFlag;
	private String indexLink;
	private String publicationFileName;
	private String plagReportFileName;
	private String plagCopyFileName;
	private int status;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPublicationFileName() {
		return publicationFileName;
	}

	public void setPublicationFileName(String publicationFileName) {
		this.publicationFileName = publicationFileName;
	}

	public String getPlagReportFileName() {
		return plagReportFileName;
	}

	public void setPlagReportFileName(String plagReportFileName) {
		this.plagReportFileName = plagReportFileName;
	}

	public String getPlagCopyFileName() {
		return plagCopyFileName;
	}

	public void setPlagCopyFileName(String plagCopyFileName) {
		this.plagCopyFileName = plagCopyFileName;
	}

	private String writtenBy;

	public String getWrittenBy() {
		return writtenBy;
	}

	public void setWrittenBy(String writtenBy) {
		this.writtenBy = writtenBy;
	}

	// status 0 = pending
	// status 1 = approved by dept cor
	// status 2 = approved by rdil
	public String getPcn() {
		return pcn;
	}

	public void setPcn(String pcn) {
		this.pcn = pcn;
	}

	public String getNameOauthors() {
		return nameOauthors;
	}

	public void setNameOauthors(String nameOauthors) {
		this.nameOauthors = nameOauthors;
	}

	public String getDeptt() {
		return deptt;
	}

	public void setDeptt(String deptt) {
		this.deptt = deptt;
	}

	public int getChapterNo() {
		return chapterNo;
	}

	public void setChapterNo(int chapterNo) {
		this.chapterNo = chapterNo;
	}

	public String getChapterTitle() {
		return chapterTitle;
	}

	public void setChapterTitle(String chapterTitle) {
		this.chapterTitle = chapterTitle;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMonthPublished() {
		return monthPublished;
	}

	public void setMonthPublished(String monthPublished) {
		this.monthPublished = monthPublished;
	}

	public String getMonthOfPCN() {
		return monthOfPCN;
	}

	public void setMonthOfPCN(String monthOfPCN) {
		this.monthOfPCN = monthOfPCN;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getHyperLink() {
		return hyperLink;
	}

	public void setHyperLink(String hyperLink) {
		this.hyperLink = hyperLink;
	}

	public String getIndexFlag() {
		return indexFlag;
	}

	public void setIndexFlag(String indexFlag) {
		this.indexFlag = indexFlag;
	}

	public String getIndexLink() {
		return indexLink;
	}

	public void setIndexLink(String indexLink) {
		this.indexLink = indexLink;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BookChapter [id=" + id + ", pcn=" + pcn + ", nameOauthors=" + nameOauthors + ", deptt=" + deptt
				+ ", chapterNo=" + chapterNo + ", chapterTitle=" + chapterTitle + ", bookTitle=" + bookTitle
				+ ", publisher=" + publisher + ", nationality=" + nationality + ", year=" + year + ", monthPublished="
				+ monthPublished + ", monthOfPCN=" + monthOfPCN + ", pageNo=" + pageNo + ", isbn=" + isbn
				+ ", hyperLink=" + hyperLink + ", indexFlag=" + indexFlag + ", indexLink=" + indexLink
				+ ", publicationFileName=" + publicationFileName + ", plagReportFileName=" + plagReportFileName
				+ ", plagCopyFileName=" + plagCopyFileName + ", status=" + status + ", writtenBy=" + writtenBy + "]";
	}

	public BookChapter(BookChapter bookChapter) {
		this.pcn = bookChapter.pcn;
		this.nameOauthors = bookChapter.nameOauthors;
		this.deptt = bookChapter.deptt;
		this.chapterNo = bookChapter.chapterNo;
		this.chapterTitle = bookChapter.chapterTitle;
		this.bookTitle = bookChapter.bookTitle;
		this.publisher = bookChapter.publisher;
		this.nationality = bookChapter.nationality;
		this.year = bookChapter.year;
		this.monthPublished = bookChapter.monthPublished;
		this.monthOfPCN = bookChapter.monthOfPCN;
		this.pageNo = bookChapter.pageNo;
		this.isbn = bookChapter.isbn;
		this.hyperLink = bookChapter.hyperLink;
		this.indexFlag = bookChapter.indexFlag;
		this.indexLink = bookChapter.indexLink;
		this.status = bookChapter.status;
	}
	
	public BookChapter(){
		
	}
}
