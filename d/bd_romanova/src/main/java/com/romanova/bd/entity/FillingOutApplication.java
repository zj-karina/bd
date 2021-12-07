package com.romanova.bd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor

@Entity
@Table(name = "filling_out_application")
public class FillingOutApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applicationNumber;

    @Column(name = "text_of_application")
    private String applicationText;

    private Integer signatureEnrollee;

    @OneToMany
    @JoinColumn(name = "filling_out_application_id", referencedColumnName = "applicationNumber")
    private Set<AdmissionOfficer> admissionOfficers;

    @OneToMany
    @JoinColumn(name = "filling_out_application_id", referencedColumnName = "applicationNumber")
    private Set<DocumentsPackage> documentsPackages;

    @ManyToOne
    @JoinColumn(name = "number_of_contest", referencedColumnName = "number_of_contest")
    private Contest contest;
}


//    `application_number` INT NOT NULL AUTO_INCREMENT,
//    `id_enrollee` INT NOT NULL,
//    `id_admission_officer` INT NOT NULL,
//    `text_of_application` TEXT NOT NULL,
//    `signature_enrolle` INT NOT NULL,
//    PRIMARY KEY(`application_number`),
//    CONSTRAINT `filling_out_application_ibfk_1` FOREIGN KEY(`id_enrollee`)
//        REFERENCES `enrollee`(`id_enrollee`),
//    CONSTRAINT `filling_out_application_ibfk_2` FOREIGN KEY(`id_admission_officer`)
//        REFERENCES `admission_officer`(`id_admission_officer`)