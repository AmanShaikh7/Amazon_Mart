package com.example.Shopping_portal.Repository;

import com.example.Shopping_portal.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
        public Seller findByEmailId(String emailId);
}
