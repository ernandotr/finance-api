package dev.ernandorezende.financeapi.domain.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

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

    public Transaction(){}

    public Transaction(String target, BigDecimal transactionValue, String source, Category category) {
        this.target = target;
        this.transactionValue = transactionValue;
        this.source = source;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public BigDecimal getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(BigDecimal transactionValue) {
        this.transactionValue = transactionValue;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
