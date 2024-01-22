package com.alex.onlinestore2023.repository;

import com.alex.onlinestore2023.Model.UserAddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddressModel, Long> {
}
