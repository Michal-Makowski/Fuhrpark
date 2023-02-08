package com.makowski.fuhrpark.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.makowski.fuhrpark.entity.Car;
import com.makowski.fuhrpark.exception.EntityNotFoundException;
import com.makowski.fuhrpark.repository.CarRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CarService {
    
    private CarRepository carRepository;

    public Car getCar(Long id){
        Optional<Car> car = carRepository.findById(id);
        return unwrapCar(car, id);
    }

    public Car saveCar(Car car){
        return carRepository.save(car);
    }

    public void deleteCar(Long id){
        carRepository.deleteById(id);
    }

    public List<Car> getCars(){
        return (List<Car>) carRepository.findAll();
    }

    public Car updateCar(Long id, Car car){
        Optional<Car> updateCar = carRepository.findById(id);
        Car unwrappedCar = unwrapCar(updateCar, id);
        unwrappedCar.setBrand(car.getBrand());
        unwrappedCar.setCarNumber(car.getCarNumber());
        unwrappedCar.setModel(car.getModel());
        unwrappedCar.setRegistrationDate(car.getRegistrationDate());
        return carRepository.save(unwrappedCar);
    }
    
    static Car unwrapCar(Optional<Car> entity, Long id) {
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Car.class);
    }
}
