package com.nexters.taiger.config;

import com.nexters.taiger.common.AuthUserDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Configuration
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {

	public AuthUserArgumentResolver() {
	}
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().isAssignableFrom(AuthUserDto.class);
	}

	@Override
	public Object resolveArgument(
			MethodParameter parameter,
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory
	) throws Exception {
		String parameterName = "login";
		HttpServletRequest httprequest = (HttpServletRequest) webRequest
				.getNativeRequest();
		HttpSession session = httprequest.getSession(false);
		Object result = null;
		if (session != null) {
			result = session.getAttribute(parameterName);
		}

		return result;
	}

	protected void raiseMissingParameterException(String paramName,
												  Class<?> paramType) throws Exception {
		throw new IllegalStateException("Missing parameter '" + paramName
				+ "' of type [" + paramType.getName() + "]");
	}

	protected void raiseSessionRequiredException(String paramName,
												 Class<?> paramType) throws Exception {
		throw new HttpSessionRequiredException(
				"No HttpSession found for resolving parameter '" + paramName
						+ "' of type [" + paramType.getName() + "]");
	}

}