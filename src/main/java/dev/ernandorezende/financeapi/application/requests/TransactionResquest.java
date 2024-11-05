package dev.ernandorezende.financeapi.application.requests;

import java.math.BigDecimal;

public record TransactionResquest(String target, BigDecimal value, Integer categoryId, String source) {
}
