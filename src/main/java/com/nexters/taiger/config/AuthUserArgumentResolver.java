package com.nexters.taiger.config;

import com.nexters.taiger.common.AuthUserDto;
import com.nexters.taiger.common.exception.InvalidAuthException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
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
		HttpServletRequest httpRequest = (HttpServletRequest) webRequest
				.getNativeRequest();
		HttpSession session = httpRequest.getSession(false);
		Object result = null;
		if (session != null && (result = session.getAttribute(parameterName)) != null) {
			return result;
		} else {
			throw new InvalidAuthException();
		}
	}
}