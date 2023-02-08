package com.makowski.fuhrpark.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.makowski.fuhrpark.entity.Cost;

public interface CostRepository extends CrudRepository<Cost, Long>{
    List<Cost> findByCarId(Long carId);
    List<Cost> findByCarIdAndCostTypeId(Long carId, Long costTypeId);
}
