package com.example.Shopping_portal.Repository;

import com.example.Shopping_portal.Enum.ProductCategory;
import com.example.Shopping_portal.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("select p from Product p where p.price >= :price and p.category = :category")
    public List<Product> getProdByCategoryAndPriceGreaterThan(int price, ProductCategory category);

    List<Product> findTop5ByCategoryOrderByPriceAsc(ProductCategory category);

    @Query(value = "SELECT * FROM product p WHERE p.price = (SELECT MIN(price) FROM product)", nativeQuery = true)
    List<Product> findAllWithCheapestPrice();

}
