package com.romanova.bd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor

@Entity
@Table(name = "contest")
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number_of_contest")
    private Integer contestNumber;

    @Column(name = "year_of_contest")
    private Integer contestYear;

    private Integer passingScore;

    @ManyToOne
    @JoinColumn(name = "budgetary_basis_of_training_id", referencedColumnName = "state_budget_reciept_number")
    private TrainingBudgetaryBasis trainingBudgetaryBasis;

    @ManyToOne
    @JoinColumn(name = "paid_basis_of_training_id", referencedColumnName = "payment_receipt_number")
    private TrainingPaidBasis trainingPaidBasis;

    @ManyToOne
    @JoinColumn(name = "target_learning_framework_id", referencedColumnName = "number_of_contract_with_company")
    private TargetLearningFramework targetLearningFramework;
}


//    `number_of_contest` INT NOT NULL AUTO_INCREMENT,
//    `application_number` INT NOT NULL,
//    `passing_score` INT NOT NULL,
//    `year_of_contest` INT NOT NULL,
//    PRIMARY KEY(`number_of_contest`),
//    CONSTRAINT `contest_ibfk_1` FOREIGN KEY(`application_number`)
//        REFERENCES `filling_out_an_application`(`application_number`)