package com.romanova.bd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor

@Entity
@Table(name = "enrollee")
public class Enrollee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enrollee")
    private Integer enrolleeId;

    private String fullName;

    private Integer sumExamPoints;

    @Column(name = "sum_IA_points")
    private Integer sumIAPoints;

    private Integer passportNumbers;

    private Integer certificateNumbers;

    private Integer direction;

    @OneToMany
    @JoinColumn(name = "id_enrollee", referencedColumnName = "id_enrollee")
    private Set<ConsultantTeacher> consultantTeachers;

    @ManyToOne
    @JoinColumn(name = "filling_out_application_id", referencedColumnName = "applicationNumber")
    private FillingOutApplication fillingOutApplication;
}

//    `id_enrollee` INT NOT NULL AUTO_INCREMENT,
//    `full_name` VARCHAR (50) NOT NULL,
//    `sum_exam_points` INT NOT NULL,
//    `sum_IA_points` INT NOT NULL,
//    `passport_numbers` INT NULL DEFAULT NULL,
//    `certificate_numbers` INT NULL DEFAULT NULL,
//    `direction` INT NOT NULL,
//    `id_teacher` INT NOT NULL,
//    PRIMARY KEY(`id_enrollee`),
//    CONSTRAINT `enrollee_ibfk_1` FOREIGN KEY (`id_teacher` )
//        REFERENCES `consultant_teacher` (`id_teacher`)