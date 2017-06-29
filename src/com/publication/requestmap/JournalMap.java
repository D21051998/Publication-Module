package com.publication.requestmap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.publication.model.Journal;

public class JournalMap {
	
	Journal journal;
	
	public Journal getJournal() {
		return journal;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}

	public JournalMap(HttpServletRequest  request){
		System.out.println(request);
		journal = new Journal();
		String nameOauthors = "";
		Map<String, String[]> parameters = request.getParameterMap();
		for(String parameter : parameters.keySet()) {
		    if(parameter.equals("nameOauthors")){
		    	String[] values = parameters.get(parameter);
		        for(String s : values){
		        	nameOauthors = nameOauthors + s + ",";
		        }		    
		    }
		}
		nameOauthors = nameOauthors.replaceAll("[,]{1,}", ",");
		while(nameOauthors.charAt(0) == ','){
			nameOauthors = nameOauthors.substring(1);
		}
		while(nameOauthors.charAt(nameOauthors.length()-1) == ','){
			nameOauthors = nameOauthors.substring(0, nameOauthors.length()-1);
		}
		journal.setNameOauthors(nameOauthors);
		journal.setDeptt(request.getParameter("deptt"));
		journal.setTitle(request.getParameter("title"));
		journal.setJournal(request.getParameter("journal"));
		journal.setNationality(request.getParameter("nationality"));
		journal.setYear(Integer.parseInt(request.getParameter("year")));
		journal.setMonthPublished(request.getParameter("monthPublished"));
		journal.setVolume(Integer.parseInt(request.getParameter("volume")));
		journal.setIssue(Integer.parseInt(request.getParameter("issue")));
		journal.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		journal.setDoiNo(Integer.parseInt(request.getParameter("doiNo")));
		journal.setImpactFactor(request.getParameter("impactFactor"));
		journal.setWhatImpactFactor(request.getParameter("whatImpactFactor"));
		journal.setLinkImpFactor(request.getParameter("linkImpFactor"));
		journal.setPaidOrUnpaid(request.getParameter("paidOrUnpaid"));
		journal.setPaymentFlag(request.getParameter("paymentFlag"));
	    journal.setPwFlag(request.getParameter("pwFlag"));
		journal.setPsFlag(request.getParameter("psFlag"));
		journal.setPgFlag(request.getParameter("pgFlag"));
		journal.setPiFlag(request.getParameter("piFlag"));
		journal.setWrittenBy(request.getParameter("writtenBy"));
		journal.setStatus(Integer.parseInt(request.getParameter("status")));
	}
	
	

}
