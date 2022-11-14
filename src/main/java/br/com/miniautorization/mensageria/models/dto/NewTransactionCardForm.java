package br.com.miniautorization.mensageria.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewTransactionCardForm {
    @NotNull(message = "O número do cartão é obrigatório")
    private Long numberCard;

    @NotNull(message = "A senha do cartão é obrigatório")
    private Integer passwordCard;

    @DecimalMin(value = "0.1", message = "O valor deve ser no minimo 0.1 centavos")
    @NotNull(message = "O valor da transação é obrigatório")
    private BigDecimal balanceCard;
}
