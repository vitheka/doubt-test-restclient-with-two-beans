package br.com.vitheka.doubt_test_restclient_with_two_beans.service;

import br.com.vitheka.doubt_test_restclient_with_two_beans.configuration.RestClientConfiguration;
import br.com.vitheka.doubt_test_restclient_with_two_beans.response.BraApiResponse;
import br.com.vitheka.doubt_test_restclient_with_two_beans.response.ViaCepResponse;
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

@RestClientTest({
        ViaCepApiService.class,
        RestClientConfiguration.class
})
class ViaCepApiServiceTest {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ViaCepApiService viaCepApiService;

    private ViaCepResponse viaCepResponse;

    @BeforeEach
    void setUp() {

        viaCepResponse = new ViaCepResponse(
                "88804-576",
                "Rua Albina Milanez",
                "",
                "",
                "Milanese",
                "Criciúma",
                "SC",
                "Santa Catarina",
                "Sul",
                "4204608",
                "",
                "48",
                "8089"
        );
    }

    @Test
    void getCep() throws JsonProcessingException {

        String baseUrl = "https://viacep.com.br";
        String path = "/ws/88804576/json/";

        var jsonResponse = mapper.writeValueAsString(viaCepResponse);

        var requestTo = MockRestRequestMatchers
                .requestToUriTemplate(baseUrl.concat(path));

        var withSuccess = MockRestResponseCreators.withSuccess(jsonResponse, MediaType.APPLICATION_JSON);

        server.expect(requestTo).andRespond(withSuccess);

        Assertions
                .assertThat(viaCepApiService.getViaCep())
                .isNotNull()
                .isEqualTo(viaCepResponse);

    }
}