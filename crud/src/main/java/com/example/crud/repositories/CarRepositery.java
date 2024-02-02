package com.example.crud.repositories;

import com.example.crud.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepositery extends JpaRepository<Car, Long> {
}
