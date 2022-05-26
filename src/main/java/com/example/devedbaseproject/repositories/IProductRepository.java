package com.example.devedbaseproject.repositories;

import com.example.devedbaseproject.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<ProductModel, Integer> {

//    public void addProduct(ProductModel product);
//    public void updateProduct(ProductModel product);
//    public void removeProduct(int id);
//    public void findProductByID(int id);
//    public List<ProductModel> productList();
}
