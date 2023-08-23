package com.example.Shopping_portal.Repository;

import com.example.Shopping_portal.Model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity , Integer> {
            List<OrderEntity> findTop5ByOrderByOrderTotalDesc();
}
