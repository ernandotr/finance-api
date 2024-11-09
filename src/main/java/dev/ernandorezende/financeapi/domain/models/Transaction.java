package dev.ernandorezende.financeapi.domain.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tb_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String target;
    private BigDecimal transactionValue;
    private String source;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
