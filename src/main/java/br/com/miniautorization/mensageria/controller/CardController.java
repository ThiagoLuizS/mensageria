package br.com.miniautorization.mensageria.controller;

import br.com.miniautorization.mensageria.producer.CardProducer;
import br.com.miniautorization.mensageria.resource.CardResource;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cards")
@Api(value = "Recursos para criar dados do cartão")
public class CardController implements CardResource {

    private final CardProducer cardProducer;

    @Override
    public void createProducerCard() {
        cardProducer.createCardMessage();
    }
}
