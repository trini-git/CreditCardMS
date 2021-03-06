package com.creditcard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Bean
  public WebClient registerWebClient() {
    return WebClient.create("http://localhost:8020/api/bankAccountMain");
  }
}
