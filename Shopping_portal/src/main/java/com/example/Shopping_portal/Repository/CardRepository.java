package com.example.Shopping_portal.Repository;

import com.example.Shopping_portal.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card , Integer> {
    public Card findByCardNo(String cardNo);
}
