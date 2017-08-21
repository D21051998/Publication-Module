package com.publication.model;

public class Journal {
	
	private String id;
	private String pcn;
	private	String nameOauthors;
	private	String deptt;
	private	String title;
	private	String journal;
	private	String nationality;
	private	int year;
	private	String monthPublished;
	private	String monthAssigned;
	private	int volume;
	private	int issue;
	private	String pageNo;
	private	String doiNo;
	private	String impactFactor;
	private	String whatImpactFactor;
	private	String linkImpFactor;
	private	String paidOrUnpaid;
	private	String paymentFlag;
	private	String pwFlag;
	private	String psFlag;
	private	String pgFlag;
	private	String piFlag;
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

	private	int status;
	private	String writtenBy;
	
	
	
	
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
	public String getJournal() {
		return journal;
	}
	public void setJournal(String journal) {
		this.journal = journal;
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
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int getIssue() {
		return issue;
	}
	public void setIssue(int issue) {
		this.issue = issue;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getDoiNo() {
		return doiNo;
	}
	public void setDoiNo(String doiNo) {
		this.doiNo = doiNo;
	}
	public String getImpactFactor() {
		return impactFactor;
	}
	public void setImpactFactor(String impactFactor) {
		this.impactFactor = impactFactor;
	}
	public String getWhatImpactFactor() {
		return whatImpactFactor;
	}
	public void setWhatImpactFactor(String whatImpactFactor) {
		this.whatImpactFactor = whatImpactFactor;
	}
	public String getLinkImpFactor() {
		return linkImpFactor;
	}
	public void setLinkImpFactor(String linkImpFactor) {
		this.linkImpFactor = linkImpFactor;
	}
	public String getPaidOrUnpaid() {
		return paidOrUnpaid;
	}
	public void setPaidOrUnpaid(String paidOrUnpaid) {
		this.paidOrUnpaid = paidOrUnpaid;
	}
	public String getPaymentFlag() {
		return paymentFlag;
	}
	public void setPaymentFlag(String paymentFlag) {
		this.paymentFlag = paymentFlag;
	}
	public String getPwFlag() {
		return pwFlag;
	}
	public void setPwFlag(String pwFlag) {
		this.pwFlag = pwFlag;
	}
	public String getPsFlag() {
		return psFlag;
	}
	public void setPsFlag(String psFlag) {
		this.psFlag = psFlag;
	}
	public String getPgFlag() {
		return pgFlag;
	}
	public void setPgFlag(String pgFlag) {
		this.pgFlag = pgFlag;
	}
	public String getPiFlag() {
		return piFlag;
	}
	public void setPiFlag(String piFlag) {
		this.piFlag = piFlag;
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
		return "Journal [id=" + id + ", pcn=" + pcn + ", nameOauthors=" + nameOauthors + ", deptt=" + deptt + ", title="
				+ title + ", journal=" + journal + ", nationality=" + nationality + ", year=" + year
				+ ", monthPublished=" + monthPublished + ", monthAssigned=" + monthAssigned + ", volume=" + volume
				+ ", issue=" + issue + ", pageNo=" + pageNo + ", doiNo=" + doiNo + ", impactFactor=" + impactFactor
				+ ", whatImpactFactor=" + whatImpactFactor + ", linkImpFactor=" + linkImpFactor + ", paidOrUnpaid="
				+ paidOrUnpaid + ", paymentFlag=" + paymentFlag + ", pwFlag=" + pwFlag + ", psFlag=" + psFlag
				+ ", pgFlag=" + pgFlag + ", piFlag=" + piFlag + ", publicationFileName=" + publicationFileName
				+ ", plagReportFileName=" + plagReportFileName + ", plagCopyFileName=" + plagCopyFileName + ", status="
				+ status + ", writtenBy=" + writtenBy + "]";
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
	
}
