package com.romanova.bd.repository;

import com.romanova.bd.entity.TrainingBudgetaryBasis;
import com.romanova.bd.entity.TrainingPaidBasis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TrainingPaidBasisRepository extends JpaRepository<TrainingPaidBasis, Integer> {

    @Query(value = "select * from paid_basis_of_training", nativeQuery = true)
    List<TrainingPaidBasis> findAll();

    @Query(value = "select * from paid_basis_of_training where payment_receipt_number=:id", nativeQuery = true)
    TrainingPaidBasis getById(Integer id);

    @Query(value = "select * from paid_basis_of_training where availability_of_original_certificate = :value", nativeQuery = true)
    List<TrainingPaidBasis> getObjectsWhereCertificateAvailabilityEquals(String value);

    @Query(value = "select * from paid_basis_of_training where payment_receipt_number = :id", nativeQuery = true)
    List<TrainingPaidBasis> getObjectsWhereIdEquals(String id);

    @Query(value = "select * from paid_basis_of_training order by availability_of_original_certificate ASC", nativeQuery = true)
    List<TrainingPaidBasis> getObjectsOrderByCertificateAvailability();

    @Query(value = "select * from paid_basis_of_training order by availability_of_original_certificate DESC ", nativeQuery = true)
    List<TrainingPaidBasis> getObjectsOrderByCertificateAvailabilityDesc();

    @Query(value = "select * from paid_basis_of_training order by payment_receipt_number ASC", nativeQuery = true)
    List<TrainingPaidBasis> getObjectsOrderById();

    @Query(value = "select * from paid_basis_of_training order by payment_receipt_number DESC", nativeQuery = true)
    List<TrainingPaidBasis> getObjectsOrderByIdDesk();

    @Modifying
    @Transactional
    @Query(value = "update paid_basis_of_training set availability_of_original_certificate = :value where payment_receipt_number = :id", nativeQuery = true)
    int updateCertificateAvailability(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into paid_basis_of_training values (:id, :availability)", nativeQuery = true)
    void insert(String id, String availability);

    @Modifying
    @Transactional
    @Query(value = "delete from paid_basis_of_training where payment_receipt_number=:id", nativeQuery = true)
    void deleteById(Integer id);
}
