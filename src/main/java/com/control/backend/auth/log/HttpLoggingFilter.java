package com.control.backend.auth.log;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.control.backend.auth.util.JsonUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HttpLoggingFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final var requestWrapper = new RepeatableContentCachingRequestWrapper(request);
		final var responseWrapper = new ContentCachingResponseWrapper(response);

		logRequest(requestWrapper);

		filterChain.doFilter(requestWrapper, responseWrapper);

		logResponse(responseWrapper);
	}

	private void logRequest(RepeatableContentCachingRequestWrapper requestWrapper) throws IOException {
		final var body = requestWrapper.readInputAndDuplicate();
		if (body.contains("userPassword")) {
			log.info("Request {}", String.valueOf(JsonUtil.jsonReplaceValue(body, "userPassword", "*****")));
		} else {
			log.info("Request {}", body);
		}
	}

	private void logResponse(ContentCachingResponseWrapper responseWrapper) throws IOException {
		log.info("Response {}", new String(responseWrapper.getContentAsByteArray()));
		responseWrapper.copyBodyToResponse();
	}
	
}
