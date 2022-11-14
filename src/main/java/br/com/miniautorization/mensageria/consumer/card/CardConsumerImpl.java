package br.com.miniautorization.mensageria.consumer.card;

import br.com.miniautorization.mensageria.models.entity.Card;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CardConsumerImpl {

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
    private void consumer(@Payload Card card) {
        log.info(">> recept consumer card [numberCard={}, passwordCard={}, balanceCard={}]",
                card.getNumberCard(),
                card.getPasswordCard(),
                card.getBalanceCard());
    }
}
