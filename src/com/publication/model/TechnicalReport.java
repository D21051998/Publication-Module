package com.publication.model;

public class TechnicalReport {

	private String id;
	private String pcn;
	private String faculty;
	private String deptt;
	private String title;
	private int year;
	private String date;
	private String remarks;
	private String monthPublished;
	private String monthAssigned;
	private String publicationFileName;
	private String plagReportFileName;
	private String plagCopyFileName;
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
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
		return "TechnicalReport [id=" + id + ", pcn=" + pcn + ", faculty=" + faculty + ", deptt=" + deptt + ", title="
				+ title + ", year=" + year + ", date=" + date + ", remarks=" + remarks + ", monthPublished="
				+ monthPublished + ", monthAssigned=" + monthAssigned + ", publicationFileName=" + publicationFileName
				+ ", plagReportFileName=" + plagReportFileName + ", plagCopyFileName=" + plagCopyFileName + ", status="
				+ status + ", writtenBy=" + writtenBy + "]";
	}
	
	
}
