package com.sharad.e_commerce.repo;

import com.sharad.e_commerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {


//    @Query("Select p from Product p where " +
//            " p.name like '% :keyword %' OR "+
//            " p.price like '% :keyword %' OR " +
//            " p.brand like '% :keyword %' OR " +
//            " p.productAvailable like '% :keyword %' OR "+
//            " p.category like '% :keyword %' OR " +
//            " p.description like '% :keyword %' ")
@Query("SELECT p from Product p WHERE "+
        "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
        +"LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
        +"LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
        +"LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchProducts(String keyword);
}
