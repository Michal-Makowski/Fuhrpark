package com.makowski.fuhrpark.repository;

import org.springframework.data.repository.CrudRepository;

import com.makowski.fuhrpark.entity.Car;

public interface CarRepository extends CrudRepository<Car, Long>{
    
}
