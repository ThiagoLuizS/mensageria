package br.com.miniautorization.mensageria.service;

import br.com.miniautorization.mensageria.models.dto.NewTransactionCardForm;
import br.com.miniautorization.mensageria.models.entity.Card;
import br.com.miniautorization.mensageria.models.enumerators.ResultTransactionCardEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class TransactionService {

    private final CardService cardService;

    public ResultTransactionCardEnum resultAndValidTransaction(NewTransactionCardForm newTransactionCardForm) {
        try {
            log.info(">> resultAndValidTransaction [newTransactionCardForm = {}]", newTransactionCardForm.getNumberCard());
            Optional<Card> card = cardService.findByNumberCard(newTransactionCardForm.getNumberCard());
            log.info("<< resultAndValidTransaction [card = {}]", card);
            if(!card.isPresent()) {
                log.info("<< resultAndValidTransaction [ResultTransactionCard = {}]", ResultTransactionCardEnum.NON_EXISTING_CARD.getName());
                return ResultTransactionCardEnum.NON_EXISTING_CARD;
            } else if(!Objects.equals(newTransactionCardForm.getPasswordCard(), card.get().getPasswordCard())) {
                log.info("<< resultAndValidTransaction [ResultTransactionCard = {}]", ResultTransactionCardEnum.INVALID_PASSWORD.getName());
                return ResultTransactionCardEnum.INVALID_PASSWORD;
            } else if(card.get().getBalanceCard().compareTo(newTransactionCardForm.getBalanceCard()) < 0) {
                log.info("<< resultAndValidTransaction [ResultTransactionCard = {}]", ResultTransactionCardEnum.INSUFFICIENT_FUNDS.getName());
                return ResultTransactionCardEnum.INSUFFICIENT_FUNDS;
            }

            BigDecimal balanceUpdate = card.get().getBalanceCard().subtract(newTransactionCardForm.getBalanceCard());

            card.get().setBalanceCard(balanceUpdate);

            cardService.update(card.get());

            log.info("<< resultAndValidTransaction [ResultTransactionCard = {}]", ResultTransactionCardEnum.OK.getName());

        } catch (Exception e) {
            log.error("<< resultAndValidTransaction [error = {}]", e.getMessage());
        }

        return ResultTransactionCardEnum.OK;
    }
}
