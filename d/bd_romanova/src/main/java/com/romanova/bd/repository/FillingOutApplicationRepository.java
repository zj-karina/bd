package com.romanova.bd.repository;

import com.romanova.bd.entity.AdmissionOfficer;
import com.romanova.bd.entity.FillingOutApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FillingOutApplicationRepository extends JpaRepository<FillingOutApplication, Integer> {

    @Query(value = "select * from filling_out_application", nativeQuery = true)
    List<FillingOutApplication> findAll();

    @Query(value = "select * from filling_out_application where application_number=:id", nativeQuery = true)
    FillingOutApplication getById(Integer id);

    @Query(value = "select * from filling_out_application where text_of_application like :like", nativeQuery = true)
    List<FillingOutApplication> getObjectsWhereAppTextLike(String like);

    @Query(value = "select * from filling_out_application where text_of_application = :value", nativeQuery = true)
    List<FillingOutApplication> getObjectsWhereAppTextEquals(String value);

    @Query(value = "select * from filling_out_application where application_number = :id", nativeQuery = true)
    List<FillingOutApplication> getObjectsWhereIdEquals(Integer id);

    @Query(value = "select * from filling_out_application where signature_enrollee = :value", nativeQuery = true)
    List<FillingOutApplication> getObjectsWhereSignatureEnrolleeEquals(Integer value);

    @Query(value = "select * from filling_out_application order by text_of_application ASC", nativeQuery = true)
    List<FillingOutApplication> getObjectsOrderByAppText();

    @Query(value = "select * from filling_out_application order by text_of_application DESC ", nativeQuery = true)
    List<FillingOutApplication> getObjectsOrderByAppTextDesk();

    @Query(value = "select * from filling_out_application order by application_number ASC", nativeQuery = true)
    List<FillingOutApplication> getObjectsOrderById();

    @Query(value = "select * from filling_out_application order by application_number DESC", nativeQuery = true)
    List<FillingOutApplication> getObjectsOrderByIdDesk();

    @Query(value = "select * from filling_out_application order by signature_enrollee ASC", nativeQuery = true)
    List<FillingOutApplication> getObjectsOrderBySignatureEnrollee();

    @Query(value = "select * from filling_out_application order by signature_enrollee DESC", nativeQuery = true)
    List<FillingOutApplication> getObjectsOrderBySignatureEnrolleeDesk();

    @Modifying
    @Transactional
    @Query(value = "update filling_out_application set text_of_application = :value where application_number = :id", nativeQuery = true)
    int updateText(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "update filling_out_application set signature_enrollee = :value where application_number = :id", nativeQuery = true)
    int updateSignature(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into filling_out_application values (null, :text, :signature, :fk)", nativeQuery = true)
    void insert(String text, String signature, String fk);

    @Modifying
    @Transactional
    @Query(value = "delete from filling_out_application where application_number=:id", nativeQuery = true)
    void deleteById(Integer id);
}
