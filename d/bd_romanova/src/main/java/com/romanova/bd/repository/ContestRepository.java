package com.romanova.bd.repository;

import com.romanova.bd.entity.AdmissionOfficer;
import com.romanova.bd.entity.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContestRepository extends JpaRepository<Contest, Integer> {

    @Query(value = "select * from contest", nativeQuery = true)
    List<Contest> findAll();

    @Query(value = "select * from contest where number_of_contest=:id", nativeQuery = true)
    Contest getById(Integer id);

    @Query(value = "select * from contest where year_of_contest = :value", nativeQuery = true)
    List<Contest> getObjectsWhereYearEquals(Integer value);

    @Query(value = "select * from contest where number_of_contest = :id", nativeQuery = true)
    List<Contest> getObjectsWhereIdEquals(Integer id);

    @Query(value = "select * from contest where passing_score = :value", nativeQuery = true)
    List<Contest> getObjectsWherePassingScoreEquals(Integer value);

    @Query(value = "select * from contest order by year_of_contest ASC", nativeQuery = true)
    List<Contest> getObjectsOrderByYear();

    @Query(value = "select * from contest order by year_of_contest DESC ", nativeQuery = true)
    List<Contest> getObjectsOrderByYearDesc();

    @Query(value = "select * from contest order by number_of_contest ASC", nativeQuery = true)
    List<Contest> getObjectsOrderById();

    @Query(value = "select * from contest order by number_of_contest DESC", nativeQuery = true)
    List<Contest> getObjectsOrderByIdDesk();

    @Query(value = "select * from contest order by passing_score ASC", nativeQuery = true)
    List<Contest> getObjectsOrderByScore();

    @Query(value = "select * from contest order by passing_score DESC", nativeQuery = true)
    List<Contest> getObjectsOrderByScoreDesk();

    @Modifying
    @Transactional
    @Query(value = "update contest set passing_score = :value where number_of_contest = :id", nativeQuery = true)
    int updateScore(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "update contest set year_of_contest = :value where number_of_contest = :id", nativeQuery = true)
    int updateYear(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into contest values (null, :score, :year, :fk1, :fk2, :fk3)", nativeQuery = true)
    void insert(String score, String year, String fk1, String fk2, String fk3);

    @Modifying
    @Transactional
    @Query(value = "delete from contest where number_of_contest=:id", nativeQuery = true)
    void deleteById(Integer id);

}
