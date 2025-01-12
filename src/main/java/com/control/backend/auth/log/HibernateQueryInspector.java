package com.control.backend.auth.log;

import java.util.regex.Pattern;

import org.hibernate.resource.jdbc.spi.StatementInspector;

//https://vladmihalcea.com/hibernate-statementinspector/
public class HibernateQueryInspector implements StatementInspector {
	
	private static final long serialVersionUID = -7501502597422388205L;
	private static final Pattern SQL_COMMENT_PATTERN = Pattern.compile("\\/\\*.*?\\*\\/\\s*");

	@Override
	public String inspect(String sql) {
		System.out.println("QUERY: " + sql);
		return SQL_COMMENT_PATTERN.matcher(sql).replaceAll("");
	}
	
}