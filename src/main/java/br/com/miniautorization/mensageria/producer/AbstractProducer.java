package br.com.miniautorization.mensageria.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDate;

@Slf4j
public abstract class AbstractProducer {

    protected abstract KafkaTemplate<String, String> getKafkaTemplate();

    public void sendMessage(String message, String topic) {

        Message<String> msg = createMessageWithHeaders(message, topic);

        ListenableFuture<SendResult<String, String>> future = getKafkaTemplate().send(msg);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info(">> send producer message [key={}, value={}]",
                        result.getProducerRecord().key(),
                        result.getProducerRecord().value());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Not create producer message for card [message={}]", message);
            }
        });
    }

    private Message<String> createMessageWithHeaders(String message, String topic) {
        return MessageBuilder.withPayload(message)
                .setHeader("Date", LocalDate.now().plusDays(1L))
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();
    }
}
