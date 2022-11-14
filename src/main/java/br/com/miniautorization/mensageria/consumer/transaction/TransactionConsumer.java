package br.com.miniautorization.mensageria.consumer.transaction;

import br.com.miniautorization.mensageria.models.dto.NewTransactionCardForm;
import br.com.miniautorization.mensageria.models.entity.Card;
import br.com.miniautorization.mensageria.models.enumerators.ResultTransactionCardEnum;
import br.com.miniautorization.mensageria.service.TransactionService;
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
public class TransactionConsumer implements TransactionConsumerImpl {

    @Autowired
    private TransactionService transactionService;

    @Override
    @KafkaListener(
            id = "transaction-consumer",
            groupId = "transaction-consumer",
            topicPartitions = @TopicPartition(
                    topic = "${CREATE_NEW_TRANSACTION}",
                    partitions = "0",
                    partitionOffsets = {
                            @PartitionOffset(partition = "*", initialOffset = "0")
                    }
            )
    )
    public void consumer(@Payload String message) {
        log.info(">> receiving consumer message [transaction={}]", message);
        NewTransactionCardForm newTransactionCardForm = new Gson().fromJson(message, NewTransactionCardForm.class);
        ResultTransactionCardEnum result = transactionService.resultAndValidTransaction(newTransactionCardForm);
        log.info("<< consumer message finished processing [result={}]", result.getName());

        /**
         * TODO: AQUI NESSA LINHA
         * EM UMA IMPLEMENTAÇÃO QUE SEJA FACTIVEL NESSA ETAPA IMPLEMENTARIA O ENVIO DE SMS OU PUSH NOTIFICATION
         * RETORNANDO PARA O USUÁRIO A MENSAGEM
         * */
    }
}
