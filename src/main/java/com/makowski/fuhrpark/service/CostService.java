package com.makowski.fuhrpark.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.makowski.fuhrpark.entity.Cost;
import com.makowski.fuhrpark.entity.CostType;
import com.makowski.fuhrpark.entity.Car;
import com.makowski.fuhrpark.repository.CarRepository;
import com.makowski.fuhrpark.repository.CostRepository;
import com.makowski.fuhrpark.repository.CostTypeRepository;
import com.makowski.fuhrpark.exception.EntityNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CostService {
    
    private CostRepository costRepository;
    private CarRepository carRepository;
    private CostTypeRepository costTypeRepository;

    public Cost saveCost(Cost cost, Long carId, Long CostTypeId){
        Car car =  CarService.unwrapCar(carRepository.findById(carId), carId);
        cost.setCar(car);
        CostType costType = CostTypeService.unwrapCostType(costTypeRepository.findById(CostTypeId), CostTypeId);
        cost.setCostType(costType);
        return costRepository.save(cost);
    }
    
    public Cost getCost(Long id){
        Optional<Cost> cost = costRepository.findById(id);
        return unwrapCost(cost, id);
    }

    public List<Cost> getCostsByCarId(Long carId){
        return costRepository.findByCarId(carId);
    }

    public List<Cost> getCostByCarIdAndCostTypeId(Long carId, Long costTypeId){
        return costRepository.findByCarIdAndCostTypeId(carId, costTypeId);
    }

    public void deleteCost(Long id){
        costRepository.deleteById(id);
    }

    public List<Cost> getCosts(){
        return (List<Cost>)costRepository.findAll();
    }

    public Cost updateCost(Long id, Cost cost){
        Optional<Cost> updateCost = costRepository.findById(id);
        Cost unwrappedCost = unwrapCost(updateCost, id);
        unwrappedCost.setCostType(cost.getCostType());
        unwrappedCost.setCar(cost.getCar());
        unwrappedCost.setComment(cost.getComment());
        unwrappedCost.setCost(cost.getCost());
        unwrappedCost.setDate(cost.getDate());
        unwrappedCost.setKilometer(cost.getKilometer());
        return costRepository.save(unwrappedCost);
    }

    static Cost unwrapCost(Optional<Cost> entity, Long id) {
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Cost.class);
    }
}
