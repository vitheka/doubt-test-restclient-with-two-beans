package br.com.vitheka.doubt_test_restclient_with_two_beans.service;

import br.com.vitheka.doubt_test_restclient_with_two_beans.configuration.RestClientConfiguration;
import br.com.vitheka.doubt_test_restclient_with_two_beans.response.BraApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import static org.junit.jupiter.api.Assertions.*;

@RestClientTest({
        BrasilApiService.class,
        RestClientConfiguration.class
})
class BrasilApiServiceTest {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private BrasilApiService brasilApiService;

    private BraApiResponse braApiResponse;

    @BeforeEach
    void setUp() {

        braApiResponse = new BraApiResponse(
                "89010025",
                "SC",
                "Blumenau",
                "Centro",
                "Rua Doutor Luiz de Freitas Melro",
                "viacep",
                null
        );
    }

    @Test
    void getCep() throws JsonProcessingException {

        String baseUrl = "https://brasilapi.com.br";
        String path = "/api/cep/v2/{cep}";
        String cepId = "89010025";

        var jsonResponse = mapper.writeValueAsString(braApiResponse);

        var requestTo = MockRestRequestMatchers
                .requestToUriTemplate(baseUrl.concat(path).replace("{cep}", cepId));

        var withSuccess = MockRestResponseCreators.withSuccess(jsonResponse, MediaType.APPLICATION_JSON);

        server.expect(requestTo).andRespond(withSuccess);

        Assertions
                .assertThat(brasilApiService.getCep(cepId))
                .isNotNull()
                .isEqualTo(braApiResponse);

    }
}