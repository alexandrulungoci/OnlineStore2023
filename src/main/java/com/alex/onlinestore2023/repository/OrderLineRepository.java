package com.alex.onlinestore2023.repository;

import com.alex.onlinestore2023.Model.OrderLineModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLineModel, Long> {
}
