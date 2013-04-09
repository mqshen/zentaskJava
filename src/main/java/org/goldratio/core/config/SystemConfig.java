package org.goldratio.core.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

/** 
 * ClassName: SystemConfig <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 1, 2013 2:43:18 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public class SystemConfig {
	@Autowired
	private ApplicationContext applicationContext;

	public SystemConfig() {
		this.properties = new Properties();
	}
	
	public SystemConfig(Properties configProperties) {
		this.properties = configProperties;
	}
	
	public void init() throws IOException {
		
		if(applicationContext instanceof WebApplicationContext){
			WebApplicationContext webApplicationContext = (WebApplicationContext)applicationContext;
			this.applicationLocation = webApplicationContext.getServletContext().getRealPath("");
		}
	}
	
	public String getConfig(String key){
		return properties.getProperty(key);
	}

	private Properties properties;
	
	private String applicationLocation;
	
	/**
	 * 默认密码
	 */
	private String defaultPassword = "123456";
	
	//管理系统中表单的默认设置
	/**
	 * 表单中数字类型字段的默认宽度
	 */
	private int numberFieldWidth = 80;
	/**
	 * 表单中日期类型字段的默认宽度
	 */
	private int dateFieldWidth = 140;
	
	private String formPosition = "label-left";
	private String formLabelAlign = "right";
	private String formLabelWidth = "90";
	private String formInputWidth = "200";
	private String formFieldsetWidth = "370";

	public String getDefaultPassword() {
		return defaultPassword;
	}
	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}
	public int getNumberFieldWidth() {
		return numberFieldWidth;
	}
	public void setNumberFieldWidth(int numberFieldWidth) {
		this.numberFieldWidth = numberFieldWidth;
	}
	public int getDateFieldWidth() {
		return dateFieldWidth;
	}
	public void setDateFieldWidth(int dateFieldWidth) {
		this.dateFieldWidth = dateFieldWidth;
	}
	public String getFormPosition() {
		return formPosition;
	}
	public void setFormPosition(String formPosition) {
		this.formPosition = formPosition;
	}
	public String getFormLabelAlign() {
		return formLabelAlign;
	}
	public void setFormLabelAlign(String formLabelAlign) {
		this.formLabelAlign = formLabelAlign;
	}
	public String getFormLabelWidth() {
		return formLabelWidth;
	}
	public void setFormLabelWidth(String formLabelWidth) {
		this.formLabelWidth = formLabelWidth;
	}
	public String getFormInputWidth() {
		return formInputWidth;
	}
	public void setFormInputWidth(String formInputWidth) {
		this.formInputWidth = formInputWidth;
	}
	public String getFormFieldsetWidth() {
		return formFieldsetWidth;
	}
	public void setFormFieldsetWidth(String formFieldsetWidth) {
		this.formFieldsetWidth = formFieldsetWidth;
	}
	public String getApplicationLocation() {
		return applicationLocation;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
