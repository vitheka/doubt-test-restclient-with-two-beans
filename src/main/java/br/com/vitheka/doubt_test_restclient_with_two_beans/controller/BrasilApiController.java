package br.com.vitheka.doubt_test_restclient_with_two_beans.controller;

import br.com.vitheka.doubt_test_restclient_with_two_beans.response.BraApiResponse;
import br.com.vitheka.doubt_test_restclient_with_two_beans.service.BrasilApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrasilApiController {

    private final BrasilApiService brasilApiService;

    public BrasilApiController(BrasilApiService brasilApiService) {
        this.brasilApiService = brasilApiService;
    }

    @GetMapping("/cep/{cepId}")
    public ResponseEntity<BraApiResponse> getCepController(@PathVariable String cepId) {
        return ResponseEntity.ok(brasilApiService.getCep(cepId));
    }
}
