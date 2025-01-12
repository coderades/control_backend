package com.control.backend.auth.log;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.control.backend.auth.util.JsonUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class HttpLogFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final var requestWrapper = new HttpLogWrapper(request);
		final var responseWrapper = new ContentCachingResponseWrapper(response);

		logRequest(requestWrapper);

		filterChain.doFilter(requestWrapper, responseWrapper);

		logResponse(responseWrapper);
	}

	private void logRequest(HttpLogWrapper requestWrapper) throws IOException {
		final var body = requestWrapper.readInputAndDuplicate();
		if (!"".equals(body)) {
			if (body.contains("userPassword")) {
				System.out.println("REQUEST: " + String.valueOf(JsonUtil.jsonReplaceValue(body, "userPassword", "*****")));
			} else {
				System.out.println("REQUEST: " + body);
			}
		}
	}

	private void logResponse(ContentCachingResponseWrapper responseWrapper) throws IOException {
		System.out.println(RequestContextHolder.currentRequestAttributes().getSessionId());
		System.out.println("REPONSE: " + new String(responseWrapper.getContentAsByteArray()));
		responseWrapper.copyBodyToResponse();
	}

}
