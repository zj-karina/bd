package com.romanova.bd.repository;

import com.romanova.bd.entity.AdmissionOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdmissionOfficerRepository extends JpaRepository<AdmissionOfficer, Integer> {

    @Query(value = "select * from admission_officer", nativeQuery = true)
    List<AdmissionOfficer> findAll();

    @Query(value = "select * from admission_officer where id_admission_officer=:id", nativeQuery = true)
    AdmissionOfficer getById(Integer id);

    @Query(value = "select * from admission_officer where busyness like :like", nativeQuery = true)
    List<AdmissionOfficer> getObjectsWhereBusynessLike(String like);

    @Query(value = "select * from admission_officer where busyness = :value", nativeQuery = true)
    List<AdmissionOfficer> getObjectsWhereBusynessEquals(String value);

    @Query(value = "select * from admission_officer where id_admission_officer = :id", nativeQuery = true)
    List<AdmissionOfficer> getObjectsWhereIdEquals(Integer id);

    @Query(value = "select * from admission_officer where signature_admission_officer = :value", nativeQuery = true)
    List<AdmissionOfficer> getObjectsWhereSignatureEquals(Integer value);

    @Query(value = "select * from admission_officer order by busyness ASC", nativeQuery = true)
    List<AdmissionOfficer> getObjectsOrderByBusyness();

    @Query(value = "select * from admission_officer order by busyness DESC ", nativeQuery = true)
    List<AdmissionOfficer> getObjectsOrderByBusynessDesk();

    @Query(value = "select * from admission_officer order by id_admission_officer ASC", nativeQuery = true)
    List<AdmissionOfficer> getObjectsOrderById();

    @Query(value = "select * from admission_officer order by id_admission_officer DESC", nativeQuery = true)
    List<AdmissionOfficer> getObjectsOrderByIdDesk();

    @Query(value = "select * from admission_officer order by signature_admission_officer ASC", nativeQuery = true)
    List<AdmissionOfficer> getObjectsOrderBySignature();

    @Query(value = "select * from admission_officer order by signature_admission_officer DESC", nativeQuery = true)
    List<AdmissionOfficer> getObjectsOrderBySignatureDesk();

    @Modifying
    @Transactional
    @Query(value = "update admission_officer set busyness = :value where id_admission_officer = :id", nativeQuery = true)
    int updateBusyness(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "update admission_officer set signature_admission_officer = :value where id_admission_officer = :id", nativeQuery = true)
    int updateSignature(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into admission_officer values (null, :busyness, :signature, :fk)", nativeQuery = true)
    void insert(String busyness, String signature, Integer fk);

    @Modifying
    @Transactional
    @Query(value = "delete from admission_officer where id_admission_officer=:id", nativeQuery = true)
    void deleteById(Integer id);

}
