package com.alex.onlinestore2023.repository;

import com.alex.onlinestore2023.Model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
