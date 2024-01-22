package com.alex.onlinestore2023.repository;

import com.alex.onlinestore2023.Model.CartModel;
import com.alex.onlinestore2023.dto.CartDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartModel, Long> {

    CartModel findByUserModel_Id(Long id);
}
