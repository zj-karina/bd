package com.romanova.bd.entity;

import com.romanova.bd.entity.enums.IADocuments;
import com.romanova.bd.entity.enums.MedicalCertificate;
import com.romanova.bd.entity.enums.Photo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor

@Entity
@Table(name = "package_of_documents")
public class DocumentsPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enrollee")
    private Integer enrolleeId;

    @Column(name = "photo")
    private String photo;

    @Column(name = "medical_certificate")
    private String medicalCertificate;

    @Column(name = "IA_documents")
    private String IADocuments;

    @Column(name = "way_of_filling")
    private String fillingWay;

}
//    `id_enrollee` INT NOT NULL,
//    `way_of_filling` VARCHAR(10) NOT NULL,
//    `photo` ENUM('да','нет') NOT NULL,
//    `medical_certificate` ENUM('да','нет') NOT NULL,
//    `IA_documents` ENUM('да','нет') NOT NULL,
//    PRIMARY KEY(`id_enrollee`)