package br.com.miniautorization.mensageria.resource;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface CardResource {

    @PostMapping("/producer")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Recurso responsavel por produzir uma mensagem para criação de um novo cartão")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Requisição feita com sucesso")
    })
    void createProducerCard();

}
