package br.com.miniautorization.mensageria.models.enumerators;

public enum ResultTransactionCardEnum {
    INSUFFICIENT_FUNDS("SALDO_INSUFICIENTE"),
    INVALID_PASSWORD("SENHA_INVALIDA"),
    NON_EXISTING_CARD("CARTAO_INEXISTENTE"),
    OK("OK");

    private String name;

    ResultTransactionCardEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isInsufficientFunds() {
        return INSUFFICIENT_FUNDS.equals(this);
    }
}
