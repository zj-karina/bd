package com.romanova.bd.entity;

import com.romanova.bd.entity.enums.OriginalCertificateAvailability;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor

@Entity
@Table(name = "paid_basis_of_training")
public class TrainingPaidBasis {
    @Id
    @Column(name = "payment_receipt_number")
    private Integer paymentReceiptNumber;

    @Column(name = "availability_of_original_certificate")
    private String originalCertificateAvailability;

}

//    `payment_receipt_number` INT NOT NULL,
//    `availability_of_original_certificate` ENUM ('да', 'нет') NOT NULL,
//    `number_of_contest` INT NOT NULL,
//    PRIMARY KEY (`payment_receipt_number`)