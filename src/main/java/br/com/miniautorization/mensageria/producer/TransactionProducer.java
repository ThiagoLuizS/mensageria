package br.com.miniautorization.mensageria.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransactionProducer extends AbstractProducer {

    @Value("${CREATE_NEW_TRANSACTION}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> cardTemplate;

    public void createTransactionMessage(String message) {
        try {
            sendMessage(message);
        }catch (Exception e) {
            log.error("Exception error [error={}]", e.getMessage());
        }
    }


    public void sendMessage(String message) {
        sendMessage(message, topic);
    }

    @Override
    protected KafkaTemplate<String, String> getKafkaTemplate() {
        return cardTemplate;
    }
}
