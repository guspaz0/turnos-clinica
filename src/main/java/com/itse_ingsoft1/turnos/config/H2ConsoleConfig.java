package com.itse_ingsoft1.turnos.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;

/**
 * Registra la consola web de H2 explícitamente (la autoconfiguración de
 * Spring Boot 4 no la activa por sí sola en este proyecto).
 *
 * <p>Se usa un {@link ServletRegistrationBean} en lugar de un
 * {@code FilterRegistrationBean}: en H2 2.4.x la clase
 * {@code org.h2.server.web.JakartaWebServlet} ya no expone un constructor
 * público {@code (DataSource)}, por lo que instanciarla por reflection con
 * ese constructor falla con {@code NoSuchMethodException}. El servlet se
 * crea sin argumentos y obtiene el {@code DataSource} del servlet context
 * (lo que inyecta la autoconfiguración de Spring Boot).
 */
@Configuration
public class H2ConsoleConfig {

  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public ServletRegistrationBean<?> h2ConsoleServlet(DataSource dataSource) throws Exception {
    Class<?> servletClass = Class.forName("org.h2.server.web.JakartaWebServlet");
    var servlet = (jakarta.servlet.Servlet) servletClass.getDeclaredConstructor().newInstance();

    var registration = new ServletRegistrationBean<>(servlet, "/h2-console/*");
    registration.setLoadOnStartup(1);
    registration.addInitParameter("webAllowOthers", "true");
    registration.addInitParameter("webSSL", "false");
    return registration;
  }
}
