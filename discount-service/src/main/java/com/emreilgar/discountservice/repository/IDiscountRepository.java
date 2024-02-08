package com.emreilgar.discountservice.repository;

import com.emreilgar.discountservice.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDiscountRepository extends JpaRepository<Discount,Integer> {

    Optional<Discount> findByCodeAndCategoryId(String code,Integer categoryId);
}
