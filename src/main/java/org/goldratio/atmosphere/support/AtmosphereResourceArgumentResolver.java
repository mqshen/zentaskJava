package org.goldratio.atmosphere.support;

import javax.servlet.http.HttpServletRequest;

import org.atmosphere.cpr.AtmosphereResource;
import org.goldratio.atmosphere.AtmosphereUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
/** 
 * ClassName: AtmosphereResourceArgumentResolver <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 13, 2013 9:37:55 AM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public class AtmosphereResourceArgumentResolver implements WebArgumentResolver {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object resolveArgument(final MethodParameter methodParameter, final NativeWebRequest webRequest) {

        if (AtmosphereResource.class.isAssignableFrom(methodParameter.getParameterType())) {
            return AtmosphereUtils.getAtmosphereResource(webRequest.getNativeRequest(HttpServletRequest.class));
        } else {
            return WebArgumentResolver.UNRESOLVED;
        }

    }

}