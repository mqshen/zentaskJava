package org.goldratio.core.velocity;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.tools.generic.DateTool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Component;

/** 
 * ClassName: VelocityManager <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 8, 2013 10:00:24 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */
public class VelocityManager {

	private static final Log LOG = LogFactory.getLog(VelocityManager.class);
	/**
	 * 模板文件的编码方式
	 */
	private static final String VELOCITY_TEMPLATE_ENCODE = "UTF-8";
	/**
	 * 模板的位置
	 */
	private static final String TEMPLATE_PATH = "config/velocity/";
	/**
	 * 模板的后缀
	 */
	private static final String TEMPLATE_SUFFIX = ".vm"; 

	/**
	 * 全局context
	 */
	private VelocityContext globalContext = new VelocityContext();
	/**
	 *  初始化模板引擎：使用单例Velocity
	 *  
	 *  @TODO 考虑实现cacheClasspathResourceLoader，缓存模板内容
	 */
	public void init(){
		try {
			//模板中的全局函数集合
			globalContext.put("date", new DateTool());
			//模板引擎初始化参数：
			Velocity.setProperty("resource.loader", "class");
			Velocity.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			Velocity.setProperty("class.resource.loader.cache", false);
			Velocity.setProperty("class.resource.loader.modificationCheckInterval ", 600);
			Velocity.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogChute");
			Velocity.setProperty("runtime.log.invalid.references", false);
			Velocity.init();
		} 
		catch(Exception ex) {
			LOG.error("模板引擎初始化出错",ex);
		}
	}
	
	/**
	 * 解析vm模板内容
	 * @param templateCode 模板位置
	 * @param model 模板参数
	 * @return
	 * @author guozp Date: 2012-12-26 上午11:19:32
	 */
	public String parseTemplate(String templateCode, Map<String, Object> model) {
		VelocityContext context = new VelocityContext(model, globalContext);
		StringWriter writer = new StringWriter();
		Velocity.mergeTemplate(getLocation(templateCode), VELOCITY_TEMPLATE_ENCODE, context, writer);
		return writer.toString();
	}

	/**
	 * 获取template文件的位置
	 * @param templateCode 模板代码
	 * @return 模板文件位置
	 * @author guozp 2012-12-26 下午7:35:59
	 */
	private String getLocation(String templateCode) {
		StringBuilder sb = new StringBuilder(TEMPLATE_PATH);
		sb.append(templateCode);
		sb.append(TEMPLATE_SUFFIX);
		return sb.toString();
	}
}
