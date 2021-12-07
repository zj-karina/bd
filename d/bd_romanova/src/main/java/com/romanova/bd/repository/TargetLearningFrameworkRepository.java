package com.romanova.bd.repository;

import com.romanova.bd.entity.TargetLearningFramework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TargetLearningFrameworkRepository extends JpaRepository<TargetLearningFramework, Integer> {

    @Query(value = "select * from target_learning_framework", nativeQuery = true)
    List<TargetLearningFramework> findAll();

    @Query(value = "select * from target_learning_framework where number_of_contract_with_company=:id", nativeQuery = true)
    TargetLearningFramework getById(Integer id);

    @Query(value = "select * from target_learning_framework where receipt_of_payment = :value", nativeQuery = true)
    List<TargetLearningFramework> getObjectsWhereReceiptEquals(String value);

    @Query(value = "select * from target_learning_framework where number_of_contract_with_company = :id", nativeQuery = true)
    List<TargetLearningFramework> getObjectsWhereIdEquals(String id);

    @Query(value = "select * from target_learning_framework where availability_of_original_certificate = :value", nativeQuery = true)
    List<TargetLearningFramework> getObjectsWhereCertificateAvailabilityEquals(String value);

    @Query(value = "select * from target_learning_framework order by receipt_of_payment ASC", nativeQuery = true)
    List<TargetLearningFramework> getObjectsOrderByReceipt();

    @Query(value = "select * from target_learning_framework order by receipt_of_payment DESC ", nativeQuery = true)
    List<TargetLearningFramework> getObjectsOrderByReceiptDesk();

    @Query(value = "select * from target_learning_framework order by number_of_contract_with_company ASC", nativeQuery = true)
    List<TargetLearningFramework> getObjectsOrderById();

    @Query(value = "select * from target_learning_framework order by number_of_contract_with_company DESC", nativeQuery = true)
    List<TargetLearningFramework> getObjectsOrderByIdDesk();

    @Query(value = "select * from target_learning_framework order by availability_of_original_certificate ASC", nativeQuery = true)
    List<TargetLearningFramework> getObjectsOrderByCertificateAvailability();

    @Query(value = "select * from target_learning_framework order by availability_of_original_certificate DESC", nativeQuery = true)
    List<TargetLearningFramework> getObjectsOrderByCertificateAvailabilityDesk();

    @Modifying
    @Transactional
    @Query(value = "update target_learning_framework set receipt_of_payment = :value where number_of_contract_with_company = :id", nativeQuery = true)
    int updateReceipt(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "update target_learning_framework set availability_of_original_certificate = :value where number_of_contract_with_company = :id", nativeQuery = true)
    int updateCertificateAvailability(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into target_learning_framework values (:id, :receipt, :availability)", nativeQuery = true)
    void insert(String id, String receipt, String availability);

    @Modifying
    @Transactional
    @Query(value = "delete from target_learning_framework where number_of_contract_with_company=:id", nativeQuery = true)
    void deleteById(Integer id);
}
