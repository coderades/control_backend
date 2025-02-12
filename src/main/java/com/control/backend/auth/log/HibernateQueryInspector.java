package com.control.backend.auth.log;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
			logger.info("{} | HTTPSTATUS: {} | SESSION: {} | DATABASE: {}", LocalDateTime.now(), "INFO",
					RequestContextHolder.getRequestAttributes() != null
							? RequestContextHolder.currentRequestAttributes().getSessionId()
							: "--------------------------------",
					jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}

//			// String accessToken = "Bearer
//			// eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzZWxsZXJJZCI6Niwic2NvcGUiOlsibWFy";
//			final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//			// headers.add("Authorization", accessToken);
//			// headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
//			headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
//			headers.add("TenantID", "tenant_3");
//
//			final var jsonObject2 = new JSONObject();
//			jsonObject2.put("logLevel", "INFO");
//			jsonObject2.put("logSession", "159E331385A6430FE33D01EC3F4B1F24");
//			jsonObject2.put("logAction", "REQUEST");
//			jsonObject2.put("logLogger", "11111111");
//			jsonObject2.put("logCreatedAt", "2025-01-22T18:29:47");
//
//			final var entity = new HttpEntity<String>(jsonObject2.toString(), headers);
//			final var restTemplate = new RestTemplate();
//			restTemplate.postForObject("http://localhost:8080/api/log", entity, String.class);

		return SQL_COMMENT_PATTERN.matcher(query).replaceAll("");
	}

}