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

    public Card save(Card card) {
        log.info(">> init save [card={}]", card);
        card = cardRepository.save(card);
        log.info("<< save finished [id={}]", card.getNumberCard());
        return card;
    }

    public Optional<Card> findByNumberCard(Long numberCard) {
        log.info(">> findByNumberCard [numberCard={}]", numberCard);
        Optional<Card> card = cardRepository.findByNumberCard(numberCard);
        log.info("<< findByNumberCard [card={}]", card);
        return card;
    }

    public void update(Card card) {
        cardRepository.save(card);
    }

    public Long findAndValidNumberCard() {
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
