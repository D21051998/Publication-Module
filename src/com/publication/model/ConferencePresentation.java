package com.publication.model;

public class ConferencePresentation {

	
	private String id;
	private String pcn;
	private String faculty;
	private String deptt;
	private String title;
	private String conferencePresentation;
	private String nationality;
	private String organisedBy;
	private String venue;
	private int year;
	private String dates;
	private String hyperlink;
	private String monthPublished;
	private String monthAssigned;
	private String publicationFileName;
	private String plagReportFileName;
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
	public String getConferencePresentation() {
		return conferencePresentation;
	}
	public void setConferencePresentation(String conferencePresentation) {
		this.conferencePresentation = conferencePresentation;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getOrganisedBy() {
		return organisedBy;
	}
	public void setOrganisedBy(String organisedBy) {
		this.organisedBy = organisedBy;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public String getHyperlink() {
		return hyperlink;
	}
	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
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
		return "ConferencePresentation [id=" + id + ", pcn=" + pcn + ", faculty=" + faculty + ", deptt=" + deptt
				+ ", title=" + title + ", conferencePresentation=" + conferencePresentation + ", nationality="
				+ nationality + ", organisedBy=" + organisedBy + ", venue=" + venue + ", year=" + year + ", dates="
				+ dates + ", hyperlink=" + hyperlink + ", monthPublished=" + monthPublished + ", monthAssigned="
				+ monthAssigned + ", publicationFileName=" + publicationFileName + ", plagReportFileName="
				+ plagReportFileName + ", status=" + status + ", writtenBy=" + writtenBy + "]";
	}

}
