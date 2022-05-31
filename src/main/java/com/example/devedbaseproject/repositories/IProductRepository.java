package com.example.devedbaseproject.repositories;

import com.example.devedbaseproject.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<ProductModel, Long> {

}
