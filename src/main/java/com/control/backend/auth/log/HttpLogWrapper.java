package com.control.backend.auth.log;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;

public class HttpLogWrapper extends ContentCachingRequestWrapper {
	
    private HttpLogStream inputStream;

    public HttpLogWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public ServletInputStream getInputStream() {
        return this.inputStream;
    }

    public String readInputAndDuplicate() throws IOException {
        if (inputStream == null) {
            final byte[] body = super.getInputStream().readAllBytes();           
            this.inputStream = new HttpLogStream(body);
        }

        return new String(super.getContentAsByteArray());
    }
    
}
