package dev.ernandorezende.financeapi.domain.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transaction {

    private Long id;
    private String target;
    private BigDecimal value;
    private Category category;
    private String source;
}
