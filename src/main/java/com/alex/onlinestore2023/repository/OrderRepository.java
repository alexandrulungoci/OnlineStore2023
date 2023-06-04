package com.alex.onlinestore2023.repository;

import com.alex.onlinestore2023.Model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {

    List<OrderModel> findByUserModel_Id(Long userId);

}
