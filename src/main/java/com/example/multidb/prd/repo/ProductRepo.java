package com.example.multidb.prd.repo;

import com.example.multidb.prd.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
