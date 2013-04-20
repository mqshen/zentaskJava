package org.goldratio.web.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang.time.DateUtils;
import org.goldratio.models.Operation;

/** 
 * ClassName: OperationTag <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 11, 2013 10:24:49 AM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public class OperationTag extends SimpleTagSupport {

	private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	private static SimpleDateFormat dayFormat = new SimpleDateFormat("MM/dd");
	private static String[] WeekDayArray = {
		"周一",
		"周二",
		"周三",
		"周四",
		"周五",
		"周六",
		"周日"
	};
	
	/**
	 * 
	 */
	private Iterable<Operation> operations;
	
	private String contextPath;
	
	public void doTag() throws JspException, IOException {
	    JspWriter out = getJspContext().getOut();
	    Date currDate = null;
	    for(Operation operation : operations) {
	    	if(operation.getAuthor() == null)
	    			continue;
	    	Date date = operation.getCreateTime();
	    	if(currDate == null || !DateUtils.isSameDay(date, currDate)) {
		    	if(currDate != null) {
		    		out.print("</div></div><div class=\"progress-day \">");
		    	}
	    		Calendar calendar = Calendar.getInstance();  
	            calendar.setTime(date);
	    		out.print("<h4><span class=\"date\">" + dayFormat.format(date) + "</span><span class=\"day\">" + WeekDayArray[calendar.get(Calendar.DAY_OF_WEEK) - 1] + "</span></h4>");
	    		out.print("<div class=\"progress-project\" >");
	    	}
	    	out.print("<div class=\"activity comment_add\" ><span class=\"time\">");
	    	out.print(timeFormat.format(date));
	    	out.print("</span><span class=\"datetime\">");
	    	out.print(date);
	    	out.print("</span>");
	    	out.print("<div class=\"reply\"><a class=\"member\" ><img class=\"avatar\" src=\"");
	    	out.print(contextPath);
	    	out.print("/avatar/");
	    	out.print(operation.getAuthor().getAvatarUrl());
	    	out.print("\" alt=\"");
	    	out.print(operation.getAuthor().getName());
	    	out.print("\"></a><div class=\"title\"><em><a>");
	    	out.print(operation.getAuthor().getName());
	    	out.print("</a></em>");
	    	out.print(operation.getOperationStr());
	    	out.print("<span class=\"sp\">：</span>");
	    	out.print("<a href=\"");
	    	out.print(contextPath);
	    	out.print(operation.getTargetUrl());
	    	out.print("/");
	    	out.print(operation.getTargetId());
	    	out.print("\">");
	    	out.print(operation.getTitle());
	    	out.print("</a></div>");
	    	out.print("<div class=\"reply-content\">");
    		out.print("<p>");
	    	if(operation.hasDigest()) {
	    		out.print(operation.getDigest());
	    	}
	    	else {
				out.print("&nbsp;");
			}
    		out.print("</p>");
	    	out.print("</div>");
	    	out.print("</div></div>");
	    	currDate = operation.getCreateTime();
	    }
		out.print("</div>");
	}

	public Iterable<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Iterable<Operation> operations) {
		this.operations = operations;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
}
