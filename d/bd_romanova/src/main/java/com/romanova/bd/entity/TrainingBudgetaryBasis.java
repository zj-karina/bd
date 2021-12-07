package com.romanova.bd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor

@Entity
@Table(name = "budgetary_basis_of_training")
public class TrainingBudgetaryBasis {
    @Id
    @Column(name = "state_budget_reciept_number")
    private Integer stateBudgetReceiptNumber;

    @Column(name = "availability_of_original_certificate")
    private String originalCertificateAvailability;
}