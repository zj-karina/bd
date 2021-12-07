package com.romanova.bd.repository;

import com.romanova.bd.entity.Enrollee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EnrolleeRepository extends JpaRepository<Enrollee, Integer> {

    @Query(value = "select * from enrollee", nativeQuery = true)
    List<Enrollee> findAll();

    @Query(value = "select * from enrollee where id_enrollee=:id", nativeQuery = true)
    Enrollee getById(Integer id);

    @Query(value = "select * from enrollee where full_name like :value", nativeQuery = true)
    List<Enrollee> getObjectsFullNameLike(String value);

    @Query(value = "select * from enrollee where full_name = :value", nativeQuery = true)
    List<Enrollee> getObjectsWhereFullNameEquals(String value);

    @Query(value = "select * from enrollee where id_enrollee = :id", nativeQuery = true)
    List<Enrollee> getObjectsWhereIdEquals(Integer id);

    @Query(value = "select * from enrollee where sum_exam_points = :value", nativeQuery = true)
    List<Enrollee> getObjectsWhereSumExamPointEquals(String value);

    @Query(value = "select * from enrollee where sum_IA_points = :value", nativeQuery = true)
    List<Enrollee> getObjectsWhereSumIAPointEquals(String value);

    @Query(value = "select * from enrollee where passport_numbers = :value", nativeQuery = true)
    List<Enrollee> getObjectsWherePassportNumbersEquals(String value);

    @Query(value = "select * from enrollee where certificate_numbers = :value", nativeQuery = true)
    List<Enrollee> getObjectsWhereCertificateNumbersEquals(String value);

    @Query(value = "select * from enrollee where direction = :value", nativeQuery = true)
    List<Enrollee> getObjectsWhereDirectionEquals(String value);

    @Query(value = "select * from enrollee order by full_name ASC", nativeQuery = true)
    List<Enrollee> getObjectsOrderByFullName();

    @Query(value = "select * from enrollee order by full_name DESC ", nativeQuery = true)
    List<Enrollee> getObjectsOrderByFullNameDesc();

    @Query(value = "select * from enrollee order by id_enrollee ASC", nativeQuery = true)
    List<Enrollee> getObjectsOrderById();

    @Query(value = "select * from enrollee order by id_enrollee DESC", nativeQuery = true)
    List<Enrollee> getObjectsOrderByIdDesk();

    @Query(value = "select * from enrollee order by sum_exam_points ASC", nativeQuery = true)
    List<Enrollee> getObjectsOrderBySumExamPoints();

    @Query(value = "select * from enrollee order by sum_exam_points DESC", nativeQuery = true)
    List<Enrollee> getObjectsOrderBySumExamPointsDesc();

    @Query(value = "select * from enrollee order by sum_IA_points ASC", nativeQuery = true)
    List<Enrollee> getObjectsOrderBySumIAPoint();

    @Query(value = "select * from enrollee order by sum_IA_points DESC", nativeQuery = true)
    List<Enrollee> getObjectsOrderBySumIAPointDesc();

    @Query(value = "select * from enrollee order by passport_numbers ASC", nativeQuery = true)
    List<Enrollee> getObjectsOrderByPassportNumbers();

    @Query(value = "select * from enrollee order by passport_numbers DESC", nativeQuery = true)
    List<Enrollee> getObjectsOrderByPassportNumbersDesc();

    @Query(value = "select * from enrollee order by certificate_numbers ASC", nativeQuery = true)
    List<Enrollee> getObjectsOrderByCertificateNumbers();

    @Query(value = "select * from enrollee order by certificate_numbers DESC", nativeQuery = true)
    List<Enrollee> getObjectsOrderByCertificateNumbersDesc();

    @Query(value = "select * from enrollee order by direction ASC", nativeQuery = true)
    List<Enrollee> getObjectsOrderByDirection();

    @Query(value = "select * from enrollee order by direction DESC", nativeQuery = true)
    List<Enrollee> getObjectsOrderByDirectionDesc();

    @Modifying
    @Transactional
    @Query(value = "update enrollee set direction = :value where id_enrollee = :id", nativeQuery = true)
    int updateDirection(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "update enrollee set full_name = :value where id_enrollee = :id", nativeQuery = true)
    int updateFullName(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "update enrollee set certificate_numbers = :value where id_enrollee = :id", nativeQuery = true)
    int updateCertificate(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "update enrollee set passport_numbers = :value where id_enrollee = :id", nativeQuery = true)
    int updatePassport(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "update enrollee set sum_IA_points = :value where id_enrollee = :id", nativeQuery = true)
    int updateSumIAPoints(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "update enrollee set sum_exam_points = :value where id_enrollee = :id", nativeQuery = true)
    int updateSumExamPoints(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into enrollee values (null, :name, :exam, :ia, :passport, :certificate, :direction, :fk)", nativeQuery = true)
    void insert(String name, String exam, String ia, String passport, String certificate, String direction, String fk);

    @Modifying
    @Transactional
    @Query(value = "delete from enrollee where id_enrollee=:id", nativeQuery = true)
    void deleteById(Integer id);
}
