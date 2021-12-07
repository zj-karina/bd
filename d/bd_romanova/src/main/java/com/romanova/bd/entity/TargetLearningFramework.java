package com.romanova.bd.entity;

import com.romanova.bd.entity.enums.OriginalCertificateAvailability;
import com.romanova.bd.entity.enums.PaymentReceipt;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor

@Entity
@Table(name = "target_learning_framework")
public class TargetLearningFramework {
    @Id
    @Column(name = "number_of_contract_with_company")
    private Integer companyContractNumber;

    @Column(name = "receipt_of_payment")
    private String paymentReceipt;

    @Column(name = "availability_of_original_certificate")
    private String originalCertificateAvailability;

}

//    `number_of_the_contract_with_the_company` INT NOT NULL,
//    `receipt_of_payment` ENUM ('да','нет') NOT NULL,
//    `availability_of_the_original_certificate` ENUM('да','нет') NOT NULL,
//    `number_of_contest` INT NOT NULL,
//    PRIMARY KEY(`number_of_the_contract_with_the_company`)