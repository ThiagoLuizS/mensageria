package br.com.miniautorization.mensageria.resource;

import br.com.miniautorization.mensageria.models.dto.NewTransactionCardForm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface TransactionResource {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Recurso responsavel por realizar uma transação")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Requisição feita com sucesso")
    })
    void createProducerTransaction(NewTransactionCardForm newTransactionCardForm) throws Exception;

}
