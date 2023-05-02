package com.alex.onlinestore2023.Model;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;

    private double totalCost;


}
