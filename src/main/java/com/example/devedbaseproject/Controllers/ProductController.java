package com.example.devedbaseproject.Controllers;

import com.example.devedbaseproject.models.ProductModel;
import com.example.devedbaseproject.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    private final IProductRepository ipr;

//    @Autowired
//    public ProductController() {
//    }


    @Autowired
    public ProductController(IProductRepository ipr) {
        this.ipr = ipr;
    }


    @GetMapping("/products")
    public String findAll(Model model) {
        List<ProductModel> productList = ipr.findAll();
        model.addAttribute("products", productList);
        return "products";
    }

//    @GetMapping("/product")
//    public String findProductById(int id) {
//        model.addAttribute
//        return "product";
//    }

    @GetMapping("/createProduct")
    public String createProductForm(ProductModel product) {
        return "createProduct";
    }

    @GetMapping("/createProduct")
    public String createProduct(ProductModel product) {
        ipr.save(product);
        return "redirect:/products";
    }
}
