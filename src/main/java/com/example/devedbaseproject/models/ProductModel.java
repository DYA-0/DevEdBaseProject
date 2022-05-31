package com.example.devedbaseproject.models;

//import lombok.Data;

import javax.persistence.*;

@Entity
@Table (name = "Products")
public class ProductModel  {

    public ProductModel() {

    }

    public ProductModel(Long Id, String productName, int productQuantity, String productDescription, int productSubTypeID, int manufacturerID) {
        this.Id = Id;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productDescription = productDescription;
        this.productSubTypeID = productSubTypeID;
        this.manufacturerID = manufacturerID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "productQuantity", nullable = false)
    private int productQuantity; //рассчитываемое поле

    @Column(name = "productDescription", nullable = false)
    private String productDescription;

    @Column(name = "productSubTypeID", nullable = false)
    private int productSubTypeID;

    @Column(name = "manufacturerID", nullable = false)
    private int manufacturerID;


    public long getId() {
        return Id;
    }

    public void setId(Long productID) {
        this.Id = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductSubTypeID() {
        return productSubTypeID;
    }

    public void setProductSubTypeID(int productSubTypeID) {
        this.productSubTypeID = productSubTypeID;
    }

    public int getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(int manufacturerID) {
        this.manufacturerID = manufacturerID;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "productID=" + Id +
                ", productName='" + productName + '\'' +
                ", productQuantity=" + productQuantity +
                ", productDescription='" + productDescription + '\'' +
                ", productSubTypeID=" + productSubTypeID +
                ", manufacturerID=" + manufacturerID +
                '}';
    }

    //    @Override
//    public String toString() {
//        return
//    }

//    @Email
//    private String email;
//
//    private LocalDate birthday;
}
