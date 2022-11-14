package br.com.miniautorization.mensageria.controller;

import br.com.miniautorization.mensageria.models.dto.NewTransactionCardForm;
import br.com.miniautorization.mensageria.producer.TransactionProducer;
import br.com.miniautorization.mensageria.resource.TransactionResource;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transacoes")
@Api(value = "Recursos para realizar uma transação")
public class TransactionController implements TransactionResource {

    private final TransactionProducer transactionProducer;

    @Override
    public void createProducerTransaction(@Valid @RequestBody NewTransactionCardForm newTransactionCardForm) throws Exception {
        transactionProducer.createTransactionMessage(new Gson().toJson(newTransactionCardForm));
    }
}
