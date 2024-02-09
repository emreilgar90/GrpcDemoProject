package com.emreilgar.discountservice.repository;

import com.emreilgar.discountservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByExternalId(String id);
}
