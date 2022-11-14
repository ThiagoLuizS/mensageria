package br.com.miniautorization.mensageria.util;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;

public class ConstantsDefaultValueUtils {

    public static BigDecimal default_balance_value = new BigDecimal(500);

    public static String generatedRandomNumber(Integer digits) {
        if(Objects.nonNull(digits)) {
            StringBuilder text = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < digits; i++) {
                text.append(random.nextInt(10)); // gerar um número aleatório entre 0 e 9
            }
            return text.toString();
        } else {
            return String.valueOf(0);
        }
    }
}
