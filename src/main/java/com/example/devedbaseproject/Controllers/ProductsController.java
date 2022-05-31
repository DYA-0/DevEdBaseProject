package com.example.devedbaseproject.Controllers;

import com.example.devedbaseproject.models.ProductModel;
import com.example.devedbaseproject.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private final IProductRepository ipr;

    @Autowired
    public ProductsController(IProductRepository ipr) {
        this.ipr = ipr;
    }

    @GetMapping()
    public String findAll(Model model) {
        List<ProductModel> productList = ipr.findAll();
        model.addAttribute("products", productList);
        return "showall";
    }

    @GetMapping("/{id}")
    public String findProductById(@PathVariable("id") Long id, Model model) {
//        List<ProductModel> productList = ipr.findAll();
//        ProductModel product = new ProductModel();
//        for (ProductModel p: productList) {
//            if (p.getId()==id) {
//                product = p;
//                break;
//            }
//            else {
//                System.out.println("Id not found");
//            }
//        }
//        model.addAttribute("product", product);

        Optional<ProductModel> product = ipr.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            System.out.println(product);
        }
        else {
            System.out.println("Error Found");
        }
        return "product";
    }

    @GetMapping("/new")
    public String newProduct(@ModelAttribute("product") ProductModel product) {
        return "new";
    }

    @PostMapping()
    public String createProduct(@ModelAttribute("product") ProductModel product) {
        ipr.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        Optional<ProductModel> product = ipr.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            System.out.println(product);
        }
        else {
            System.out.println("Error Found");
        }

        return "product/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("product") ProductModel product, @PathVariable("id") Long id) {
        ipr.save(product);

        return "redirect:/products";
    }







//    @PostMapping("/createproduct")
//    public String createProduct(@RequestParam("productName") String productName,
//                                @RequestParam("productQuantity") int productQuantity,
//                                @RequestParam("productDescription") String productDescription,
//                                @RequestParam("productSubTypeID") int productSubTypeID,
//                                @RequestParam("manufacturerID") int manufacturerID,
//                                Model model) {
//        ProductModel product = new ProductModel();
//        product.setProductName(productName);
//        product.setProductQuantity(productQuantity);
//        product.setProductDescription(productDescription);
//        product.setProductSubTypeID(productSubTypeID);
//        product.setManufacturerID(manufacturerID);
//        model.addAttribute("product", product);
//
////        if (result.hasErrors()){
////            return "createProduct";
////        }
//        ipr.save(product);
//        return "redirect:/products";
//    }


}
