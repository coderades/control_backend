package com.control.backend.auth.log;

import java.util.regex.Pattern;

import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;

import com.control.backend.auth.model.dto.LogDTO;
import com.control.backend.auth.service.LogService;

//https://vladmihalcea.com/hibernate-statementinspector/
public class HibernateQueryInspector implements StatementInspector {

	private static final long serialVersionUID = -7501502597422388205L;
	private static final Pattern SQL_COMMENT_PATTERN = Pattern.compile("\\/\\*.*?\\*\\/\\s*");

	@Override
	public String inspect(String query) {
		final var jsonObject = new JSONObject();
		final var session = RequestContextHolder.getRequestAttributes() != null
				? RequestContextHolder.currentRequestAttributes().getSessionId()
				: "--------------------------------";

		try {
			jsonObject.put("query", query);
			new LogService().save(new LogDTO(null, "INFO", session, "DATABASE", jsonObject.toString()));
		} catch (Exception e) {
			new LogService().save(new LogDTO(null, "ERROR", session, "DATABASE", e.getMessage()));
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