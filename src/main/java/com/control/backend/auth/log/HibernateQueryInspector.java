package com.control.backend.auth.log;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;

//https://vladmihalcea.com/hibernate-statementinspector/
public class HibernateQueryInspector implements StatementInspector {

	private static final long serialVersionUID = -7501502597422388205L;
	private static final Pattern SQL_COMMENT_PATTERN = Pattern.compile("\\/\\*.*?\\*\\/\\s*");
	private static final Logger logger = LoggerFactory.getLogger(HibernateQueryInspector.class);

	@Override
	public String inspect(String query) {
		final var jsonObject = new JSONObject();
		try {
			jsonObject.put("query", query);
			logger.info("{} | HTTPSTATUS: {} | SESSION: {} | DATABASE: {}", LocalDateTime.now(), "INFO", RequestContextHolder.currentRequestAttributes().getSessionId(), jsonObject);			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return SQL_COMMENT_PATTERN.matcher(query).replaceAll("");
	}

}