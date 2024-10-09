package br.com.vitheka.doubt_test_restclient_with_two_beans.service;

import br.com.vitheka.doubt_test_restclient_with_two_beans.response.ViaCepResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ViaCepApiService {

    @Value("${viacep-api.path}")
    private String path;

    private final RestClient restClient;

    public ViaCepApiService(@Qualifier("viaCepApiClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public ViaCepResponse getViaCep() {
        return restClient
                .get()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .build())
                .retrieve()
                .body(ViaCepResponse.class);
    }
}
