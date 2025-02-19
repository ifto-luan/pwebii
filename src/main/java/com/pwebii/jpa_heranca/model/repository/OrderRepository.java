package com.pwebii.jpa_heranca.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pwebii.jpa_heranca.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long>{

    List<Order> findByDate(LocalDate date);

    @Query("SELECT o FROM Order o WHERE o.client.id = :id")
    List<Order> findByClientId(@Param("id") Long id);

    @Query("SELECT o FROM Order o JOIN FETCH o.client")
    List<Order> findAllWithCustomer();

}
