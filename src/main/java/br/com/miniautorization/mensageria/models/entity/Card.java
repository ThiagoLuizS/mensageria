package br.com.miniautorization.mensageria.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_card")
public class Card {

    @Id
    @Column(name = "id_card")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "number_card")
    private Long numberCard;

    @Column(name = "balance_card")
    private BigDecimal balanceCard;

    @NotNull
    @Column(name = "password_card")
    private Integer passwordCard;

}
