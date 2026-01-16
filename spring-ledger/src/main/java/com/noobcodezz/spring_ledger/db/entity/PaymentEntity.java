package com.noobcodezz.spring_ledger.db.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "payments")
public class PaymentEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "loan_id")
    private long loanId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "emi_number")
    private int emiNumber;

    @CreationTimestamp
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    public PaymentEntity() {

    }
}
