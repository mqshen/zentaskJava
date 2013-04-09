package org.goldratio.web.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.goldratio.core.ZenTaskConstants;

/** 
 * ClassName: BaseController <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 3, 2013 8:16:59 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public class BaseController {

	public static final String RESULT_OK = "000000";
	/**
	 * 构建一个处理成功的返回，
	 * <li>result:处理结果，固定值ok
	 * <li>content:返回的内容
	 *
	 * @param content
	 * @return
	 *
	 * @author jojo 2013-1-10 上午7:12:25
	 */
	protected Map<String, Object> buildSuccessResult(Object content) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(content != null){
			result.put("content", content);
		}
		result.put("returnCode", RESULT_OK);
		return result;
	}
	
	/**
	 * 构建一个处理成功的返回，
	 * <li>result:处理结果，固定值ok
	 * <li>content:返回的内容
	 *
	 * @param content
	 * @return
	 *
	 * @author jojo 2013-1-10 上午7:12:25
	 */
	protected Map<String, Object> buildSuccessResult(HttpSession session, String name, Object object) {
		Map<String, Object> content = new HashMap<String, Object>();
		if(object!= null){
			content.put("user", session.getAttribute(ZenTaskConstants.USER_FIELD));
			content.put(name, object);
		}
		content.put("returnCode", RESULT_OK);
		return content;
	}
	
	
	/**
	 * 构建一个处理成功的返回，
	 * <li>result:处理结果，固定值ok
	 * <li>content:返回的内容
	 *
	 * @param content
	 * @return
	 *
	 * @author jojo 2013-1-10 上午7:12:25
	 */
	protected Map<String, Object> buildSuccessResult(HttpServletRequest request, HttpSession session, String name, Object object) {
		Map<String, Object> content = new HashMap<String, Object>();
		if(object!= null){
			content.put("user", session.getAttribute(ZenTaskConstants.USER_FIELD));
			content.put(name, object);
		}
		content.put("returnCode", RESULT_OK);
		content.put("contextPath", request.getContextPath());
		return content;
	}
}
