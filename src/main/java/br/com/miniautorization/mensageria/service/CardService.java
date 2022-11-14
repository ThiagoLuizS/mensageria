package br.com.miniautorization.mensageria.service;

import br.com.miniautorization.mensageria.models.entity.Card;
import br.com.miniautorization.mensageria.repository.CardRepository;
import br.com.miniautorization.mensageria.util.ConstantsDefaultValueUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class CardService {

    private final CardRepository cardRepository;

    public Card save() {
        log.info(">> init save card");
        Card card = Card.builder()
                .numberCard(findAndValidNumberCard())
                .passwordCard(Integer.parseInt(ConstantsDefaultValueUtils.generatedRandomNumber(4)))
                .balanceCard(ConstantsDefaultValueUtils.default_balance_value)
                .build();
        log.info(">> save [card={}]", card);
        card = cardRepository.save(card);
        log.info("<< save [id={}]", card.getNumberCard());
        return card;
    }

    private Long findAndValidNumberCard() {
        boolean numberValid = true;
        Long numberCard = 0L;
        while (numberValid) {
            numberCard = Long.parseLong(ConstantsDefaultValueUtils.generatedRandomNumber(10));
            Optional<Card> card = cardRepository.findByNumberCard(numberCard);
            if(!card.isPresent()) {
                numberValid = false;
            }
        }
        return numberCard;
    }
}
