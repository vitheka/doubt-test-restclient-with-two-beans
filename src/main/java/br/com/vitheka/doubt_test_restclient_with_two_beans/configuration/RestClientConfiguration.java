package br.com.vitheka.doubt_test_restclient_with_two_beans.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {

    @Value("${brasil-api.base-url}")
    private String baseUrlBrasilApi;

    @Value("${viacep-api.base-url}")
    private String baseUrlViaCep;


    @Primary
    @Bean(name = "brasilApiClient")
    public RestClient brasilApiClient(RestClient.Builder builder) {
        return builder.baseUrl(baseUrlBrasilApi).build();
    }

    @Bean(name = "viaCepApiClient")
    public RestClient viaCepApiClient(RestClient.Builder builder) {
        return builder.baseUrl(baseUrlViaCep).build();
    }
}
