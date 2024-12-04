package com.pwebii.jpa_heranca.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pwebii.jpa_heranca.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE LOWER(p.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    List<Product> findAllContainingDescription(@Param("description") String description);

}
