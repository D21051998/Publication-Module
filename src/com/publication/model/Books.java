package com.publication.model;

public class Books {

	private String id;
	private String pcn;
	private String nameOauthors;
	private String deptt;
	private String title;
	private String publisher;
	private String nationality;
	private int year;
	private String monthPublished;
	private String monthAssigned;
	private String pageNo;
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	private String isbn;
	private String hyperlink;
	private String index;
	public String getPageNo() {
		return pageNo;
	}
	private String link;
	private String publicationFileName;
	private String plagReportFileName;
	private String plagCopyFileName;
	private String certificateName;
	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	private int status;
	private String writtenBy;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getMonthAssigned() {
		return monthAssigned;
	}
	public void setMonthAssigned(String monthAssigned) {
		this.monthAssigned = monthAssigned;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getHyperlink() {
		return hyperlink;
	}
	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getWrittenBy() {
		return writtenBy;
	}
	public void setWrittenBy(String writtenBy) {
		this.writtenBy = writtenBy;
	}
	@Override
	public String toString() {
		return "Books [id=" + id + ", pcn=" + pcn + ", nameOauthors=" + nameOauthors + ", deptt=" + deptt + ", title="
				+ title + ", publisher=" + publisher + ", nationality=" + nationality + ", year=" + year
				+ ", monthPublished=" + monthPublished + ", monthAssigned=" + monthAssigned + ", pageNo=" + pageNo
				+ ", isbn=" + isbn + ", hyperlink=" + hyperlink + ", index=" + index + ", link=" + link
				+ ", publicationFileName=" + publicationFileName + ", plagReportFileName=" + plagReportFileName
				+ ", plagCopyFileName=" + plagCopyFileName + ", status=" + status + ", writtenBy=" + writtenBy + "]";
	}

	
}
