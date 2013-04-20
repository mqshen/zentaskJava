package org.goldratio.web.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang.time.DateUtils;
import org.goldratio.models.Project;
import org.goldratio.models.TodoItem;
import org.goldratio.models.User;
import org.goldratio.util.DateUtil;

/** 
 * ClassName: TodoItemTag <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 11, 2013 5:37:06 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public class TodoItemTag extends SimpleTagSupport {
	private static SimpleDateFormat dayFormat = new SimpleDateFormat("YY-MM-dd");
	
	private List<TodoItem> todoItems;
	
	private String contextPath;
	
	private User user;
	
	private boolean today;
	
	
	private void buildHtml(JspWriter out, TodoItem todoItem, Date currentDate) throws IOException {
		out.print("<li class=\"todo ui-draggable\" >");
		out.print("<div class=\"todo-actions actions\">");
		out.print("<div class=\"inr\">");
		out.print("<a href=\"");
		
		Project project = todoItem.getTodoList().getProject();
		
		out.print(contextPath);
		out.print("/project/");
		out.print(project.getId());
		out.print("/todoList/");
		out.print(todoItem.getTodoListId());
		out.print("/todoItem/");
		out.print(todoItem.getId());
		out.print("/running\" class=\"run\" title=\"标记成正在进行中\">执行</a>");
		
		out.print("<a href=\"");
		out.print(contextPath);
		out.print("/project/");
		out.print(project.getId());
		out.print("/todoList/");
		out.print(todoItem.getTodoListId());
		out.print("/todoItem/");
		out.print(todoItem.getId());
		out.print("/pause\" class=\"pause\" title=\"暂停\">暂停</a>");
		
		out.print("<a href=\"");
		out.print(contextPath);
		out.print("/project/");
		out.print(project.getId());
		out.print("/todoList/");
		out.print(todoItem.getTodoListId());
		out.print("/todoItem/");
		out.print(todoItem.getId());
		out.print("/edit\" class=\"edit\" title=\"编辑\">编辑</a>");
		
		out.print("<a href=\"");
		out.print(contextPath);
		out.print("/project/");
		out.print(project.getId());
		out.print("/todoList/");
		out.print(todoItem.getTodoListId());
		out.print("/todoItem/");
		out.print(todoItem.getId());
		out.print("/destroy\" class=\"del\" data-remote=\"true\" data-method=\"post\" data-confirm=\"确定要删除这条任务吗？\" title=\"删除\">删除</a>");
		
		out.print("</div>");
		out.print("</div>");
		out.print("<div class=\"todo-wrap\">");
		out.print("<input type=\"checkbox\" name=\"todo-done\">");
		out.print("<span class=\"todo-content\">");
		out.print("<span>");
		out.print(todoItem.getTitle());
		out.print("</span>");
		
		out.print("<a href=\"");
		out.print(contextPath);
		out.print("/project/");
		out.print(project.getId());
		out.print("/todoList/");
		out.print(todoItem.getTodoListId());
		out.print("/todoItem/");
		out.print(todoItem.getId());
		out.print("\" data-stack=\"\">");
		out.print(todoItem.getTitle());
		out.print("</a>");
		
		out.print("</span>");
		out.print("<a class=\"label todo-assign-due ");
		if(!DateUtils.isSameDay(currentDate, todoItem.getDeadLine())) {
			out.print("delay");
		}
		out.print("\" href=\"javascript:;\" >");
		out.print("<span class=\"assignee\" data-guid=\"a795e4b7ca5e4766972e9609696217a3\">");
		out.print(user.getName());
		out.print("</span>");
		out.print("<span class=\"due\" >");
		out.print(dayFormat.format(todoItem.getDeadLine()));
		out.print("</span>");
		out.print("</a>");
		out.print("<a href=\"");
		out.print(contextPath);
		out.print("/project/");
		out.print(project.getId());
		out.print("\" class=\"label todo-proj\"data-stack=\"true\">");
		out.print(project.getTitle());
		out.print("</a>");
		out.print("</div>");
		out.print("</li>");
	}
	
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		Date currentDate = new Date();
		if(isToday()) {
			for(TodoItem todoItem: todoItems) {
				Date deadLine = todoItem.getDeadLine();
				if(deadLine == null)
					continue;
				if(DateUtil.isBeforeOrSameDay(currentDate, deadLine)) {
					buildHtml(out, todoItem, currentDate);
				}
				else {
					break;
				}
			}
		}
		else {
			for(TodoItem todoItem: todoItems) {
				Date deadLine = todoItem.getDeadLine();
				if(deadLine == null)
					continue;
				
				if(!DateUtil.isBeforeOrSameDay(currentDate, deadLine)) {
					buildHtml(out, todoItem, currentDate);
				}
			}
		}
	}

	public List<TodoItem> getTodoItems() {
		return todoItems;
	}

	public void setTodoItems(List<TodoItem> todoItems) {
		this.todoItems = todoItems;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isToday() {
		return today;
	}

	public void setToday(boolean today) {
		this.today = today;
	}
}
