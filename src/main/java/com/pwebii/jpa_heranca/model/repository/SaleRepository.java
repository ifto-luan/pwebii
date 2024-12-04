package com.pwebii.jpa_heranca.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pwebii.jpa_heranca.model.entity.Sale;


@Repository
public interface SaleRepository extends JpaRepository <Sale, Long>{

    List<Sale> findByDate(LocalDate date);

    @Query("SELECT s FROM Sale s WHERE s.client.id = :id")
    List<Sale> findByClientId(@Param("id") Long id);

}
