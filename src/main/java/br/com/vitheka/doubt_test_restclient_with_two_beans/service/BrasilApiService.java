package br.com.vitheka.doubt_test_restclient_with_two_beans.service;

import br.com.vitheka.doubt_test_restclient_with_two_beans.response.BraApiResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class BrasilApiService {

    @Value("${brasil-api.path}")
    private String path;

    private final RestClient restClient;

    public BrasilApiService(@Qualifier("brasilApiClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public BraApiResponse getCep(String cepIn) {
        return restClient
                .get()
                .uri(uriBuilder -> uriBuilder.path(path.replace("{cep}", cepIn))
                        .build())
                .retrieve()
                .body(BraApiResponse.class);
    }
}
