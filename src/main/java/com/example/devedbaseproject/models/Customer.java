package com.example.devedbaseproject.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="customer_name")
    private String name;
    @Column(name="customer_surname")
    private String surname;
    @Column(name="customer_age")
    private Integer age;
    @Column(name="customer_email")
    private String email;
    @Column(name="customer_phone_number")
    private String phoneNumber;

}