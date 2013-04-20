package org.goldratio.atmosphere.support;

import javax.servlet.http.HttpServletRequest;

import org.atmosphere.cpr.Meteor;
import org.goldratio.atmosphere.AtmosphereUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/** 
 * ClassName: MeteorArgumentResolver <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 13, 2013 9:35:17 AM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public class MeteorArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Meteor.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object resolveArgument(final MethodParameter parameter,
            final ModelAndViewContainer mavContainer,
            final NativeWebRequest webRequest,
            final WebDataBinderFactory binderFactory) {
        return AtmosphereUtils.getMeteor(webRequest.getNativeRequest(HttpServletRequest.class));
    }
}