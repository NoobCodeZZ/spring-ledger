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
@Table(name = "loans")
public class LoanEntity {

    // helpful for marking an entity as the primary key of our database
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "reference_id")
    private String referenceId;

    @PrePersist
    public void generateReferenceId() {
        if(this.referenceId == null){
                this.referenceId = UUID.randomUUID().toString();
        }
    }
    // it is important to save the timestamps at which the project was created at and also that which the project was last updated at
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "bank_id")
    private long bankId;

    @Column(name = "roi")
    private double roi;

    @Column(name = "years")
    private long years;

    @Column(name = "principal")
    private double principal;


    public LoanEntity(long userId, long bankId, double roi, long years, double principal) {
        this.userId = userId;
        this.bankId = bankId;
        this.roi = roi;
        this.years = years;
        this.principal = principal;
    }

    public LoanEntity() {

    }
}
