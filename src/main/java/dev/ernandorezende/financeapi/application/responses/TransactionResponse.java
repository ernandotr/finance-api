package dev.ernandorezende.financeapi.application.responses;

import java.math.BigDecimal;

public record TransactionResponse(Long id, String target, String source, BigDecimal value, CategoryResponse category) {
}
