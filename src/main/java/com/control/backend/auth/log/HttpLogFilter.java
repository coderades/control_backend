package com.control.backend.auth.log;

import java.io.IOException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(HttpLogFilter.class);

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
		var body = requestWrapper.readInputAndDuplicate();
		if (!"".equals(body)) {
			if (body.contains("userPassword")) {
				body = String.valueOf(JsonUtil.jsonReplaceValue(body, "userPassword", "*****"));
			}
			
			logger.info("{} | HTTPSTATUS: {} | SESSION: {} | REQUEST: {}", LocalDateTime.now(), "INFO",
					RequestContextHolder.currentRequestAttributes().getSessionId(), body);
		}
	}

	private void logResponse(ContentCachingResponseWrapper responseWrapper) throws IOException {
		logger.info("{} | HTTPSTATUS: {} | SESSION: {} | REPONSE: {}", LocalDateTime.now(), "INFO",
				RequestContextHolder.currentRequestAttributes().getSessionId(),
				new String(responseWrapper.getContentAsByteArray()));
		responseWrapper.copyBodyToResponse();
	}

}
