package br.com.miniautorization.mensageria.consumer;

import br.com.miniautorization.mensageria.models.entity.Card;
import org.springframework.messaging.handler.annotation.Payload;

public interface ConsumerImpl {

    void consumer(String message);

}
