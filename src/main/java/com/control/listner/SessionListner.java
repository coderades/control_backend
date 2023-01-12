package com.control.listner;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.MDC;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;

@WebListener
@Slf4j
public class SessionListner implements HttpSessionListener {

	private final AtomicInteger counter = new AtomicInteger();

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		MDC.put("sessionId", httpSessionEvent.getSession().getId());
		log.info("Session: Created");
		counter.incrementAndGet();
		updateSessionCounter(httpSessionEvent);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		try {
			MDC.put("sessionId", httpSessionEvent.getSession().getId());
			log.info("Session: Destroyed");
			counter.decrementAndGet();
			updateSessionCounter(httpSessionEvent);
		} catch (Exception e) {
			log.error("Session: It's already destroyed");
		}
	}

	private void updateSessionCounter(HttpSessionEvent httpSessionEvent) {
		MDC.put("sessionId", httpSessionEvent.getSession().getId());
		log.info("Session: Total active session are {}", counter.get());
		httpSessionEvent.getSession().getServletContext().setAttribute("activeSession", counter.get());
	}

}