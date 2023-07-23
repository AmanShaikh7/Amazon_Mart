package com.example.Shopping_portal.Repository;

import com.example.Shopping_portal.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    public Customer findByMobNo(String mobileNo);
}
