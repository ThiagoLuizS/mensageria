package br.com.miniautorization.mensageria.config.card;

import br.com.miniautorization.mensageria.models.entity.Card;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class CardProducerConfig {

    @Bean
    public KafkaTemplate<String, String> cardTemplate(ProducerFactory<String, String> factory) {
        return new KafkaTemplate<>(factory);
    }

}
