package com.alex.onlinestore2023.Model;

import javax.persistence.*;

@Entity
@Table(name = "order_line")
public class OrderLineModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}
