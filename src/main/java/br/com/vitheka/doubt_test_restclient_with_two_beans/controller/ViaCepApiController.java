package br.com.vitheka.doubt_test_restclient_with_two_beans.controller;

import br.com.vitheka.doubt_test_restclient_with_two_beans.response.BraApiResponse;
import br.com.vitheka.doubt_test_restclient_with_two_beans.response.ViaCepResponse;
import br.com.vitheka.doubt_test_restclient_with_two_beans.service.ViaCepApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViaCepApiController {

    private final ViaCepApiService viaCepApiService;

    public ViaCepApiController(ViaCepApiService viaCepApiService) {
        this.viaCepApiService = viaCepApiService;
    }

    @GetMapping("/viacep")
    public ResponseEntity<ViaCepResponse> getViaepController() {
        return ResponseEntity.ok(viaCepApiService.getViaCep());
    }
}
