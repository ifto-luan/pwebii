package com.pwebii.jpa_heranca.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pwebii.jpa_heranca.model.entity.Address;
import com.pwebii.jpa_heranca.model.entity.Client;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByClient(Client client);

}
