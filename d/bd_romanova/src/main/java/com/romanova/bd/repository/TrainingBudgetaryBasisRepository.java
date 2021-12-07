package com.romanova.bd.repository;

import com.romanova.bd.entity.TargetLearningFramework;
import com.romanova.bd.entity.TrainingBudgetaryBasis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TrainingBudgetaryBasisRepository extends JpaRepository<TrainingBudgetaryBasis, Integer> {

    @Query(value = "select * from budgetary_basis_of_training", nativeQuery = true)
    List<TrainingBudgetaryBasis> findAll();

    @Query(value = "select * from budgetary_basis_of_training where state_budget_reciept_number=:id", nativeQuery = true)
    TrainingBudgetaryBasis getById(Integer id);

    @Query(value = "select * from budgetary_basis_of_training where availability_of_original_certificate = :value", nativeQuery = true)
    List<TrainingBudgetaryBasis> getObjectsWhereCertificateAvailabilityEquals(String value);

    @Query(value = "select * from budgetary_basis_of_training where state_budget_reciept_number = :id", nativeQuery = true)
    List<TrainingBudgetaryBasis> getObjectsWhereIdEquals(String id);

    @Query(value = "select * from budgetary_basis_of_training order by availability_of_original_certificate ASC", nativeQuery = true)
    List<TrainingBudgetaryBasis> getObjectsOrderByCertificateAvailability();

    @Query(value = "select * from budgetary_basis_of_training order by availability_of_original_certificate DESC ", nativeQuery = true)
    List<TrainingBudgetaryBasis> getObjectsOrderByCertificateAvailabilityDesc();

    @Query(value = "select * from budgetary_basis_of_training order by state_budget_reciept_number ASC", nativeQuery = true)
    List<TrainingBudgetaryBasis> getObjectsOrderById();

    @Query(value = "select * from budgetary_basis_of_training order by state_budget_reciept_number DESC", nativeQuery = true)
    List<TrainingBudgetaryBasis> getObjectsOrderByIdDesk();

    @Modifying
    @Transactional
    @Query(value = "update budgetary_basis_of_training set availability_of_original_certificate = :value where state_budget_reciept_number = :id", nativeQuery = true)
    int updateCertificateAvailability(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into budgetary_basis_of_training values (:id, :availability)", nativeQuery = true)
    void insert(String id, String availability);

    @Modifying
    @Transactional
    @Query(value = "delete from budgetary_basis_of_training where state_budget_reciept_number=:id", nativeQuery = true)
    void deleteById(Integer id);
}
