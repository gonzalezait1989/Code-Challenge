package com.wefox.challenge.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.gateway.MessagingGatewaySupport;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.integration.http.inbound.RequestMapping;
import org.springframework.integration.http.support.DefaultHttpHeaderMapper;
import org.springframework.integration.mapping.HeaderMapper;

/**
 * Defines a DSL Integration.
 * 
 * @author aitor
 */
@Configuration
@EnableIntegration
@ComponentScan
@IntegrationComponentScan
public class InfrastructureConfiguration {

  @Bean
  public ExpressionParser parser() {
    return new SpelExpressionParser();
  }

  @Bean
  public HeaderMapper<HttpHeaders> headerMapper() {
    return new DefaultHttpHeaderMapper();
  }

  /**
   * Defines a Gateway for http requests.
   * 
   * @return the Gateway.
   */
  @Bean
  public MessagingGatewaySupport httpGetGate() {
    HttpRequestHandlingMessagingGateway handler = new HttpRequestHandlingMessagingGateway();
    handler.setRequestMapping(
        createMapping(new HttpMethod[] { HttpMethod.GET }, "/api/v1/toys/{id:[0-9]+}"));
    handler.setPayloadExpression(parser().parseExpression("#pathVariables.id"));
    handler.setHeaderMapper(headerMapper());
    return handler;
  }

  /**
   * Defines a Integration Flow.
   * 
   * @return the Integration Flow.
   */
  @Bean
  public IntegrationFlow httpGetFlow() {
    return IntegrationFlows.from(httpGetGate()).channel("httpGetChannel")
        .handle("toyEndpoint", "get").get();
  }

  /**
   * Creates a mapping between different paths to the ending request.
   * 
   * @param method the method used by the request.
   * @param path   the path of the request.
   * @return the RequestMapping.
   */
  private RequestMapping createMapping(HttpMethod[] method, String... path) {
    RequestMapping requestMapping = new RequestMapping();
    requestMapping.setMethods(method);
    requestMapping.setConsumes("application/json");
    requestMapping.setProduces("application/json");
    requestMapping.setPathPatterns(path);
    return requestMapping;
  }
}
