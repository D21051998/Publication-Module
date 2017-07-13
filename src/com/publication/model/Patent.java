package com.publication.model;

public class Patent {

	private String id;
	private String pcn;
	private String faculty;
	private String deptt;
	private String title;
	private String nationality;
	private String country;
	private String applicationNo;
	private int applicationYear;
	private String applicationDate;
	private int patentYear;
	private int patentNo;
	private String awardDate;
	private String monthAssigned;
	private String publicationFileName;
	private String plagReportFileName;
	private int status;
	private String writtenBy;
	
	public int getPatentNo() {
		return patentNo;
	}
	public void setPatentNo(int patentNo) {
		this.patentNo = patentNo;
	}
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
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public int getApplicationYear() {
		return applicationYear;
	}
	public void setApplicationYear(int applicationYear) {
		this.applicationYear = applicationYear;
	}
	public String getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	public int getPatentYear() {
		return patentYear;
	}
	public void setPatentYear(int patentYear) {
		this.patentYear = patentYear;
	}
	public String getAwardDate() {
		return awardDate;
	}
	public void setAwardDate(String awardDate) {
		this.awardDate = awardDate;
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
		return "Patent [id=" + id + ", pcn=" + pcn + ", faculty=" + faculty + ", deptt=" + deptt + ", title=" + title
				+ ", nationality=" + nationality + ", country=" + country + ", applicationNo=" + applicationNo
				+ ", applicationYear=" + applicationYear + ", applicationDate=" + applicationDate + ", patentYear="
				+ patentYear + ", patentNo=" + patentNo + ", awardDate=" + awardDate + ", monthAssigned="
				+ monthAssigned + ", publicationFileName=" + publicationFileName + ", plagReportFileName="
				+ plagReportFileName + ", status=" + status + ", writtenBy=" + writtenBy + "]";
	}
		
	
}
