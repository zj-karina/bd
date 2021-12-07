package com.romanova.bd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor

@Entity
@Table(name = "consultant_teacher")
public class ConsultantTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_teacher")
    private Integer teacherId;

    private String fullName;

    private String department;

    private String institute;
}


//    `id_teacher` INT NOT NULL AUTO_INCREMENT,
//    `full_name` VARCHAR(30) NOT NULL,
//    `department` VARCHAR(20) NOT NULL,
//    `institute` VARCHAR(5) NOT NULL,
//    PRIMARY KEY(`id_teacher`)