package com.romanova.bd.repository;

import com.romanova.bd.entity.Contest;
import com.romanova.bd.entity.DocumentsPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DocumentsPackageRepository extends JpaRepository<DocumentsPackage, Integer> {

    @Query(value = "select * from package_of_documents", nativeQuery = true)
    List<DocumentsPackage> findAll();

    @Query(value = "select * from package_of_documents where id_enrollee=:id", nativeQuery = true)
    DocumentsPackage getById(Integer id);

    @Query(value = "select * from package_of_documents where way_of_filling like :value", nativeQuery = true)
    List<DocumentsPackage> getObjectsFillingWayLike(String value);

    @Query(value = "select * from package_of_documents where photo = :value", nativeQuery = true)
    List<DocumentsPackage> getObjectsWherePhotoEquals(String value);

    @Query(value = "select * from package_of_documents where id_enrollee = :id", nativeQuery = true)
    List<DocumentsPackage> getObjectsWhereIdEquals(Integer id);

    @Query(value = "select * from package_of_documents where medical_certificate = :value", nativeQuery = true)
    List<DocumentsPackage> getObjectsWhereMedicalCertificateEquals(String value);

    @Query(value = "select * from package_of_documents where IA_documents = :value", nativeQuery = true)
    List<DocumentsPackage> getObjectsWhereIADocumentsEquals(String value);

    @Query(value = "select * from package_of_documents where way_of_filling = :value", nativeQuery = true)
    List<DocumentsPackage> getObjectsWhereWayOfFillingEquals(String value);

    @Query(value = "select * from package_of_documents order by photo ASC", nativeQuery = true)
    List<DocumentsPackage> getObjectsOrderByPhoto();

    @Query(value = "select * from package_of_documents order by photo DESC ", nativeQuery = true)
    List<DocumentsPackage> getObjectsOrderByPhotoDesc();

    @Query(value = "select * from package_of_documents order by id_enrollee ASC", nativeQuery = true)
    List<DocumentsPackage> getObjectsOrderById();

    @Query(value = "select * from package_of_documents order by id_enrollee DESC", nativeQuery = true)
    List<DocumentsPackage> getObjectsOrderByIdDesk();

    @Query(value = "select * from package_of_documents order by medical_certificate ASC", nativeQuery = true)
    List<DocumentsPackage> getObjectsOrderByMedicalCertificate();

    @Query(value = "select * from package_of_documents order by medical_certificate DESC", nativeQuery = true)
    List<DocumentsPackage> getObjectsOrderByMedicalCertificateDesk();

    @Query(value = "select * from package_of_documents order by IA_documents ASC", nativeQuery = true)
    List<DocumentsPackage> getObjectsOrderByIADocuments();

    @Query(value = "select * from package_of_documents order by IA_documents DESC", nativeQuery = true)
    List<DocumentsPackage> getObjectsOrderByIADocumentsDesk();

    @Query(value = "select * from package_of_documents order by way_of_filling ASC", nativeQuery = true)
    List<DocumentsPackage> getObjectsOrderByFillingWay();

    @Query(value = "select * from package_of_documents order by way_of_filling DESC", nativeQuery = true)
    List<DocumentsPackage> getObjectsOrderByFillingWayDesk();

    @Modifying
    @Transactional
    @Query(value = "update package_of_documents set way_of_filling = :value where id_enrollee = :id", nativeQuery = true)
    int updateWay(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "update package_of_documents set IA_documents = :value where id_enrollee = :id", nativeQuery = true)
    int updateIADocuments(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "update package_of_documents set medical_certificate = :value where id_enrollee = :id", nativeQuery = true)
    int updateCertificate(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "update package_of_documents set photo = :value where id_enrollee = :id", nativeQuery = true)
    int updatePhoto(String value, Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into package_of_documents values (:id, :way, :photo, :certificate, :IADocuments, :fk)", nativeQuery = true)
    void insert(Integer id, String way, String photo, String certificate, String IADocuments, Integer fk);

    @Modifying
    @Transactional
    @Query(value = "delete from package_of_documents where id_enrollee=:id", nativeQuery = true)
    void deleteById(Integer id);

}
