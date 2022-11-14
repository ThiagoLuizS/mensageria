package br.com.miniautorization.mensageria.producer;

import br.com.miniautorization.mensageria.models.entity.Card;
import br.com.miniautorization.mensageria.service.CardService;
import br.com.miniautorization.mensageria.util.ConstantsDefaultValueUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CardProducer extends AbstractProducer {

    @Value("${CREATE_NEW_CARD}")
    private String topic;

    @Autowired
    private CardService cardService;

    @Autowired
    private KafkaTemplate<String, String> cardTemplate;

    public void createCardMessage() {
        try {
            Card card = Card.builder()
                    .numberCard(cardService.findAndValidNumberCard())
                    .passwordCard(Integer.parseInt(ConstantsDefaultValueUtils.generatedRandomNumber(4)))
                    .balanceCard(ConstantsDefaultValueUtils.default_balance_value)
                    .build();

            String json = new Gson().toJson(card);

            sendCardMessage(json);

        }catch (Exception e) {
            log.error("Exception error [error={}]", e.getMessage());
        }

    }

    public void sendCardMessage(String message) {
        sendMessage(message, topic);
    }

    @Override
    protected KafkaTemplate<String, String> getKafkaTemplate() {
        return cardTemplate;
    }
}
