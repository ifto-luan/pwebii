package com.pwebii.jpa_heranca.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pwebii.jpa_heranca.model.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository <Sale, Long>{

}
