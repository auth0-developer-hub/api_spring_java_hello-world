package com.example.helloworld.config.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ResponseHeadersFilter implements Filter {

  @Override
  public void doFilter(
    final ServletRequest request,
    final ServletResponse response,
    final FilterChain chain
  ) throws IOException, ServletException {
    final var httpResponse = (HttpServletResponse) response;

    httpResponse.setIntHeader("X-XSS-Protection", 0);
    httpResponse.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
    httpResponse.setHeader("X-Frame-Options", "deny");
    httpResponse.setHeader("X-Content-Type-Options", "nosniff");
    httpResponse.setHeader("Content-Security-Policy", "default-src 'self'; frame-ancestors 'none';");
    httpResponse.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, max-age=0, must-revalidate");
    httpResponse.setHeader(HttpHeaders.PRAGMA, "no-cache");
    httpResponse.setIntHeader(HttpHeaders.EXPIRES, 0);

    chain.doFilter(request, response);
  }
}
