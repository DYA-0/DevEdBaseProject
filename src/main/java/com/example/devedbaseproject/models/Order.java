package com.example.devedbaseproject.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customerId;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employeeId;

    @Column(name="date")
    private Date currentDate;

    @Column(name="order_cost")
    private Integer orderCost;

    @Column(name="order_status")
    private String orderStatus;

}