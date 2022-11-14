package br.com.miniautorization.mensageria.producer.card;

import br.com.miniautorization.mensageria.models.entity.Card;
import br.com.miniautorization.mensageria.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
public class CardProducerImpl {

    @Value("${CREATE_NEW_CARD}")
    private String topic;

    @Autowired
    private CardService cardService;

    @Autowired
    private KafkaTemplate<String, Card> cardTemplate;

    public CardProducerImpl() {
    }

    public void create() {
        sendCardMessage();
    }

    private void sendCardMessage() {
        Card card = cardService.save();
        Message<Card> message = createMessageWithHeaders(card);
        ListenableFuture<SendResult<String, Card>> future = cardTemplate.send(message);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, Card> result) {
                log.info("", result.getProducerRecord().key(),
                        result.getProducerRecord().value(),
                        result.getProducerRecord().partition());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Not create producer for card [numberCard={}]", card.getNumberCard());
            }
        });
    }

    private Message<Card> createMessageWithHeaders(Card card) {
        return MessageBuilder.withPayload(card)
                .setHeader("numberCard", card.getNumberCard())
                .setHeader("passwordCard", card.getPasswordCard())
                .setHeader("balanceCard", card.getBalanceCard())
                .setHeader("Date", LocalDate.now().plusDays(1L))
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader(KafkaHeaders.MESSAGE_KEY, card.getNumberCard())
                .build();
    }
}
