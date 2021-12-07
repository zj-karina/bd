package com.romanova.bd.repository;

import com.romanova.bd.entity.ConsultantTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ConsultantTeacherRepository extends JpaRepository<ConsultantTeacher, Integer> {

    @Query(value = "select * from consultant_teacher", nativeQuery = true)
    List<ConsultantTeacher> findAll();

    @Query(value = "select * from consultant_teacher where id_teacher=:id", nativeQuery = true)
    ConsultantTeacher getById(Integer id);

    @Query(value = "select * from consultant_teacher where full_name like :like", nativeQuery = true)
    List<ConsultantTeacher> getObjectsWhereFullNameLike(String like);

    @Query(value = "select * from consultant_teacher where department like :like", nativeQuery = true)
    List<ConsultantTeacher> getObjectsWhereDepartmentLike(String like);

    @Query(value = "select * from consultant_teacher where institute like :like", nativeQuery = true)
    List<ConsultantTeacher> getObjectsWhereInstituteLike(String like);

    @Query(value = "select * from consultant_teacher where full_name = :value", nativeQuery = true)
    List<ConsultantTeacher> getObjectsWhereFullNameEquals(String value);

    @Query(value = "select * from consultant_teacher where id_teacher = :id", nativeQuery = true)
    List<ConsultantTeacher> getObjectsWhereIdEquals(Integer id);

    @Query(value = "select * from consultant_teacher where department = :value", nativeQuery = true)
    List<ConsultantTeacher> getObjectsWhereDepartmentEquals(String value);

    @Query(value = "select * from consultant_teacher where institute = :value", nativeQuery = true)
    List<ConsultantTeacher> getObjectsWhereInstituteEquals(String value);

    @Query(value = "select * from consultant_teacher order by department ASC", nativeQuery = true)
    List<ConsultantTeacher> getObjectsOrderByDepartment();

    @Query(value = "select * from consultant_teacher order by department DESC", nativeQuery = true)
    List<ConsultantTeacher> getObjectsOrderByDepartmentDesc();

    @Query(value = "select * from consultant_teacher order by id_teacher ASC", nativeQuery = true)
    List<ConsultantTeacher> getObjectsOrderById();

    @Query(value = "select * from consultant_teacher order by id_teacher DESC", nativeQuery = true)
    List<ConsultantTeacher> getObjectsOrderByIdDesk();

    @Query(value = "select * from consultant_teacher order by full_name ASC", nativeQuery = true)
    List<ConsultantTeacher> getObjectsOrderByFullName();

    @Query(value = "select * from consultant_teacher order by full_name DESC ", nativeQuery = true)
    List<ConsultantTeacher> getObjectsOrderByFullNameDesc();

    @Query(value = "select * from consultant_teacher order by institute ASC", nativeQuery = true)
    List<ConsultantTeacher> getObjectsOrderByInstitute();

    @Query(value = "select * from consultant_teacher order by institute DESC ", nativeQuery = true)
    List<ConsultantTeacher> getObjectsOrderByInstituteDesc();

    @Modifying
    @Transactional
    @Query(value = "update consultant_teacher set full_name = :value where id_teacher = :id", nativeQuery = true)
    int updateFullName(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "update consultant_teacher set department = :value where id_teacher = :id", nativeQuery = true)
    int updateDepartment(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "update consultant_teacher set institute = :value where id_teacher = :id", nativeQuery = true)
    int updateInstitute(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into consultant_teacher values (null, :fullName, :department, :institute, :fk)", nativeQuery = true)
    void insert(String fullName, String department, String institute, Integer fk);

    @Modifying
    @Transactional
    @Query(value = "delete from consultant_teacher where id_teacher=:id", nativeQuery = true)
    void deleteById(Integer id);
}
