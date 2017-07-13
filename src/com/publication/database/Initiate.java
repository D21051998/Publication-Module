package com.publication.database;

import java.sql.Connection;
import java.sql.Statement;

public class Initiate {
	
	
	public Initiate(){

		Statement stmt;
		Connection conn = null;
	    try{
	    	conn = ConnectionFactory.getConnection();
	    	stmt = conn.createStatement();
	    	String q1 = "create table if not exists login"
	    			+ "( username varchar(200) primary key,"
	    			+ "password varchar(200) not null,"
	    			+ "role varchar(200),"
	    			+ "status varchar(200),"
	    			+ "salt varchar(200),"
	    			+ "sid varchar(200) unique"
	    			+ ");";
	    	stmt.executeUpdate(q1);
	    	
	    	String f1 = "create table if not exists  forgotreq"
	    			+ "(token varchar(200) primary key,"
	    			+ "username varchar(200),"
	    			+ "time varchar(200),"
	    			+ " FOREIGN KEY (username) REFERENCES login(username)"
	    			+ ");";
	    	stmt.executeUpdate(f1);
	    	
	    	String q2 = "create table if not exists book_chapter("
	    			+ "id varchar(200) primary key,"
	    			+ "pcn varchar(200),"
	    			+ "nameOauthors varchar(200),"
	    			+ "deptt varchar(200),"
	    			+ "chapterNo varchar(200),"
	    			+ "chapterTitle varchar(200),"
	    			+ "bookTitle varchar(200),"
	    			+ "publisher varchar(200),"
	    			+ "nationality varchar(200),"
	    			+ "year int,"
	    			+ "monthPublished varchar(200),"
	    			+ "monthAssigned date,"
	    			+ "pageNo varchar(200),"
	    			+ "isbn varchar(200),"
	    			+ "hyperLink varchar(200),"
	    			+ "indexFlag varchar(200),"
	    			+ "indexLink varchar(200),"
	    			+ "status int,"
	    			+ "publicationfilename varchar(500),"
	    			+ "plagreportfilename varchar(500),"
	    			+ "plagcopyfilename varchar(500),"
	    			+ "writtenBy varchar(200));";
	    	stmt.executeUpdate(q2);
	    	
	    	
	    	String rej_q2 = "create table if not exists rej_book_chapter("
	    			+ "rej int NOT NULL AUTO_INCREMENT,"
	    			+ "id varchar(200),"
	    			+ "pcn varchar(200),"
	    			+ "nameOauthors varchar(200),"
	    			+ "deptt varchar(200),"
	    			+ "chapterNo varchar(200),"
	    			+ "chapterTitle varchar(200),"
	    			+ "bookTitle varchar(200),"
	    			+ "publisher varchar(200),"
	    			+ "nationality varchar(200),"
	    			+ "year int,"
	    			+ "monthPublished varchar(200),"
	    			+ "monthAssigned date,"
	    			+ "pageNo varchar(200),"
	    			+ "isbn varchar(200),"
	    			+ "hyperLink varchar(200),"
	    			+ "indexFlag varchar(200),"
	    			+ "indexLink varchar(200),"
	    			+ "status int,"
	    			+ "publicationfilename varchar(500),"
	    			+ "plagreportfilename varchar(500),"
	    			+ "plagcopyfilename varchar(500),"
	    			+ "writtenBy varchar(200)"
	    			+ "primary key(rej,id));";
	    	stmt.executeUpdate(q2);
	    	
	    	
	    	
	    	String q3 = "create table if not exists journal("
	    			+ "id varchar(200) primary key,"
	    			+ "pcn varchar(200) unique,"
	    			+ "nameOauthors varchar(200),"
	    			+ "deptt varchar(200),"
	    			+ "title varchar(200),"
	    			+ "journal varchar(200),"
	    			+ "nationality varchar(200),"
	    			+ "year int,"
	    			+ "monthPublished varchar(200),"
	    			+ "monthAssigned date,"
	    			+ "volume int,"
	    			+ "issue int,"
	    			+ "pageNo int,"
	    			+ "doiNo int,"
	    			+ "impactFactor varchar(200),"
	    			+ "whatImpactFactor varchar(200),"
	    			+ "linkImpFactor varchar(200),"
	    			+ "paidOrUnpaid varchar(200),"
	    			+ "paymentFlag varchar(200),"
	    			+ "pwFlag varchar(200),"
	    			+ "psFlag varchar(200),"
	    			+ "pgFlag varchar(200),"
	    			+ "piFlag varchar(200),"
	    			+ "status int,"
	    			+ "publicationfilename varchar(500),"
	    			+ "plagreportfilename varchar(500),"
	    			+ "plagcopyfilename varchar(500),"
	    			+ "writtenBy varchar(200));";
	    	stmt.executeUpdate(q3);
	    	
	    	String r1 = "create table if not exists rej_journal("
	    			+ "rej int NOT NULL AUTO_INCREMENT,"
	    			+ "id varchar(200),"
	    			+ "pcn varchar(200) unique,"
	    			+ "nameOauthors varchar(200),"
	    			+ "deptt varchar(200),"
	    			+ "title varchar(200),"
	    			+ "journal varchar(200),"
	    			+ "nationality varchar(200),"
	    			+ "year int,"
	    			+ "monthPublished varchar(200),"
	    			+ "monthAssigned date,"
	    			+ "volume int,"
	    			+ "issue int,"
	    			+ "pageNo int,"
	    			+ "doiNo int,"
	    			+ "impactFactor varchar(200),"
	    			+ "whatImpactFactor varchar(200),"
	    			+ "linkImpFactor varchar(200),"
	    			+ "paidOrUnpaid varchar(200),"
	    			+ "paymentFlag varchar(200),"
	    			+ "pwFlag varchar(200),"
	    			+ "psFlag varchar(200),"
	    			+ "pgFlag varchar(200),"
	    			+ "piFlag varchar(200),"
	    			+ "status int,"
	    			+ "publicationfilename varchar(500),"
	    			+ "plagreportfilename varchar(500),"
	    			+ "plagcopyfilename varchar(500),"
	    			+ "writtenBy varchar(200),"
	    			+ "message varchar(500),"
	    			+ "primary key(rej,id));";
	    	stmt.executeUpdate(r1);
	
	    	String patent = "create table if not exists patent("
	    			+ "id varchar(200) primary key,"
	    			+ "pcn varchar(200),"
	    			+ "faculty varchar(200),"
	    			+ "deptt varchar(200),"
	    			+ "title varchar(200),"
	    			+ "nationality varchar(200),"
	    			+ "country varchar(200),"
	    			+ "applicationNo varchar(200),"
	    			+ "applicationYear int,"
	    			+ "applicationDate varchar(200),"
	    			+ "patentYear int,"
	    			+ "patentNo int,"
	    			+ "awardDate varchar(200),"
	    			+ "monthAssigned date,"
	    			+ "publicationFileName varchar(500),"
	    			+ "plagReportFileName varchar(500),"
	    			+ "status int,"
	    			+ "writtenBy varchar(200)"
	    			+ ");"; 
	    	stmt.executeUpdate(patent);
	    	
	    	
	    	String rej_patent = "create table if not exists rej_patent("
	    			+ "rej int NOT NULL AUTO_INCREMENT,"
	    			+ "id varchar(200),"
	    			+ "pcn varchar(200),"
	    			+ "faculty varchar(200),"
	    			+ "deptt varchar(200),"
	    			+ "title varchar(200),"
	    			+ "nationality varchar(200),"
	    			+ "country varchar(200),"
	    			+ "applicationNo varchar(200),"
	    			+ "applicationYear int,"
	    			+ "applicationDate varchar(200),"
	    			+ "patentYear int,"
	    			+ "patentNo int,"
	    			+ "awardDate varchar(200),"
	    			+ "monthAssigned date,"
	    			+ "publicationFileName varchar(500),"
	    			+ "plagReportFileName varchar(500),"
	    			+ "status int,"
	    			+ "writtenBy varchar(200),"
	    			+ "message varchar(500),"
	    			+ "primary key(rej,id)"
	    			+ ");"; 
	    	stmt.executeUpdate(rej_patent);
	    	
	    	String tech_rep = "create table if not exists tech_rep("
	    			+ "id varchar(200) primary key,"
	    			+ "pcn varchar(200),"
	    			+ "faculty varchar(200),"
	    			+ "deptt varchar(200),"
	    			+ "title varchar(200),"
	    			+ "year int,"
	    			+ "date varchar(200),"
	    			+ "remarks varchar(200),"
	    			+ "monthPublished varchar(200),"
	    			+ "monthAssigned date,"
	    			+ "publicationFileName  varchar(200),"
	    			+ "plagReportFileName varchar(2000),"
	    			+ "plagCopyFileName varchar(200),"
	    			+ "status int,"
	    			+ "writtenBy varchar(200)"
	    			+ ");";
	    	String rej_tech_rep = "create table if not exists rej_tech_rep("
	    			+ "rej int NOT NULL AUTO_INCREMENT,"
	    			+ "id varchar(200),"
	    			+ "pcn varchar(200),"
	    			+ "faculty varchar(200),"
	    			+ "deptt varchar(200),"
	    			+ "title varchar(200),"
	    			+ "year int,"
	    			+ "date varchar(200),"
	    			+ "remarks varchar(200),"
	    			+ "monthPublished varchar(200),"
	    			+ "monthAssigned date,"
	    			+ "publicationFileName  varchar(200),"
	    			+ "plagReportFileName varchar(2000),"
	    			+ "plagCopyFile11Name varchar(200),"
	    			+ "status int,"
	    			+ "writtenBy varchar(200),"
	    			+ "primary key(rej,id)"
	    			+ ");";
	    	stmt.executeUpdate(tech_rep);
	    	stmt.executeUpdate(rej_tech_rep);
	    	
	    	String conf_proc = "create table if not exists conf_proc("
	    			+ "id varchar(200) primary key,"
	    			+ "pcn varchar(200),"
	    			+ "nameOauthors varchar(200),"
	    			+ "deptt varchar(200),"
	    			+ "title varchar(200),"
	    			+ "proceedingsOf varchar(200),"
	    			+ "nationality varchar(200),"
	    			+ "venue varchar(200),"
	    			+ "year int,"
	    			+ "monthPublished varchar(200),"
	    			+ "monthAssigned date,"
	    			+ "publisher varchar(500),"
	    			+ "pageNo int,"
	    			+ "hyperLink varchar(500),"
	    			+ "indices varchar(200),"
	    			+ "link varchar(200),"
	    			+ "publicationFileName  varchar(200),"
	    			+ "plagReportFileName varchar(2000),"
	    			+ "plagCopyFileName varchar(200),"
	    			+ "status int,"
	    			+ "writtenBy varchar(200)"
	    			+ ");";
	    	
	    	String rej_conf_proc = "create table if not exists rej_conf_proc("
	    			+ "rej int NOT NULL AUTO_INCREMENT,"
	    			+ "id varchar(200) ,"
	    			+ "pcn varchar(200),"
	    			+ "nameOauthors varchar(200),"
	    			+ "deptt varchar(200),"
	    			+ "title varchar(200),"
	    			+ "proceedingsOf varchar(200),"
	    			+ "nationality varchar(200),"
	    			+ "venue varchar(200),"
	    			+ "year int,"
	    			+ "monthPublished varchar(200),"
	    			+ "monthAssigned date,"
	    			+ "publisher varchar(500),"
	    			+ "pageNo int,"
	    			+ "hyperLink varchar(500),"
	    			+ "indices varchar(200),"
	    			+ "link varchar(200),"
	    			+ "publicationFileName  varchar(200),"
	    			+ "plagReportFileName varchar(2000),"
	    			+ "plagCopyFileName varchar(200),"
	    			+ "status int,"
	    			+ "writtenBy varchar(200),"
	    			+ "primary key (rej,id)"
	    			+ ");";
	    	
	    	stmt.executeUpdate(conf_proc);
	    	stmt.executeUpdate(rej_conf_proc);
	    	
	    	
	    	
	    	String conf_pres = "create table if not exists conf_pres("
	    			+ "id varchar(200) primary key,"
	    			+ "pcn varchar(200),"
	    			+ "faculty varchar(200),"
	    			+ "deptt varchar(200),"
	    			+ "title varchar(200),"
	    			+ "conferencePresentation varchar(200),"
	    			+ "nationality varchar(200),"
	    			+ "organisedBy varchar(200),"
	    			+ "venue varchar(200),"
	    			+ "year int,"
	    			+ "dates varchar(500),"
	    			+ "hyperLink varchar(500),"
	    			+ "monthPublished varchar(200),"
	    			+ "monthAssigned date,"
	    			+ "publicationFileName  varchar(200),"
	    			+ "plagReportFileName varchar(2000),"
	    			+ "status int,"
	    			+ "writtenBy varchar(200)"
	    			+ ");";
	    	String rej_conf_pres = "create table if not exists rej_conf_pres("
	    			+ "rej int NOT NULL AUTO_INCREMENT,"
	    			+ "id varchar(200),"
	    			+ "pcn varchar(200),"
	    			+ "faculty varchar(200),"
	    			+ "deptt varchar(200),"
	    			+ "title varchar(200),"
	    			+ "conferencePresentation varchar(200),"
	    			+ "nationality varchar(200),"
	    			+ "organisedBy varchar(200),"
	    			+ "venue varchar(200),"
	    			+ "year int,"
	    			+ "dates varchar(500),"
	    			+ "hyperLink varchar(500),"
	    			+ "monthPublished varchar(200),"
	    			+ "monthAssigned date,"
	    			+ "publicationFileName  varchar(200),"
	    			+ "plagReportFileName varchar(2000),"
	    			+ "status int,"
	    			+ "writtenBy varchar(200),"
	    			+ "primary key (rej,id)"
	    			+ ");";
	    stmt.executeUpdate(conf_pres);
	    stmt.executeUpdate(rej_conf_pres);
	    
	    String book = "create table if not exists book("
		+ "id varchar(200) primary key,"
		+ "pcn varchar(200),"
		+ "nameOauthors varchar(200),"
		+ "deptt varchar(200),"
		+ "chapterNo varchar(200),"
		+ "title varchar(200),"
		+ "publisher varchar(200),"
		+ "nationality varchar(200),"
		+ "year int,"
		+ "monthPublished varchar(200),"
		+ "monthAssigned date,"
		+ "pageNo varchar(200),"
		+ "isbn varchar(200),"
		+ "hyperLink varchar(200),"
		+ "indices varchar(200),"
		+ "link varchar(200),"
		+ "status int,"
		+ "publicationfilename varchar(500),"
		+ "plagreportfilename varchar(500),"
		+ "plagcopyfilename varchar(500),"
		+ "writtenBy varchar(200));";
stmt.executeUpdate(book);


String rej_book = "create table if not exists rej_book("
		+ "rej int NOT NULL AUTO_INCREMENT,"
		+ "id varchar(200),"
		+ "pcn varchar(200),"
		+ "nameOauthors varchar(200),"
		+ "deptt varchar(200),"
		+ "chapterNo varchar(200),"
		+ "title varchar(200),"
		+ "publisher varchar(200),"
		+ "nationality varchar(200),"
		+ "year int,"
		+ "monthPublished varchar(200),"
		+ "monthAssigned date,"
		+ "pageNo varchar(200),"
		+ "isbn varchar(200),"
		+ "hyperLink varchar(200),"
		+ "indices varchar(200),"
		+ "link varchar(200),"
		+ "status int,"
		+ "publicationfilename varchar(500),"
		+ "plagreportfilename varchar(500),"
		+ "plagcopyfilename varchar(500),"
		+ "writtenBy varchar(200),"
		+ "primary key (rej,id)"
		+ ");";
stmt.executeUpdate(rej_book);
	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally {
			ConnectionFactory.close(conn);
			stmt=null;
		}	
	}
	public static void main(String[] args) {
		new Initiate();
	}
}
