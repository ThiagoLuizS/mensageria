package br.com.miniautorization.mensageria.consumer.card;

import br.com.miniautorization.mensageria.models.entity.Card;
import br.com.miniautorization.mensageria.service.CardService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CardConsumer implements CardConsumerImpl {

    @Autowired
    private CardService cardService;

    @Override
    @KafkaListener(
            id = "card-consumer",
            groupId = "card-consumer",
            topicPartitions = @TopicPartition(
                    topic = "${CREATE_NEW_CARD}",
                    partitions = "0",
                    partitionOffsets = {
                            @PartitionOffset(partition = "*", initialOffset = "0")
                    }
            )
    )
    public void consumer(@Payload String message) {
        log.info(">> receiving consumer message [card={}]", message);
        Card card = new Gson().fromJson(message, Card.class);
        card = cardService.save(card);
        log.info("<< consumer message finished processing [numberCard={}]",
                card.getNumberCard());
    }
}
