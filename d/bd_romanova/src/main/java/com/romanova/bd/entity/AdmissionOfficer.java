package com.romanova.bd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor

@Entity
@Table(name = "admission_officer")
public class AdmissionOfficer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admission_officer")
    private Integer admissionOfficerId;

    private String busyness;

    private Integer signatureAdmissionOfficer;
}

//    `id_admission_officer` INT NOT NULL AUTO_INCREMENT,
//    `busyness` VARCHAR(50) NULL DEFAULT NULL,
//    `signature_admission_officer` INT NOT NULL,
//    `fio` VARCHAR(30) NULL DEFAULT NULL,
//    PRIMARY KEY(`id_admission_officer`)
