package com.makowski.fuhrpark.web;

import java.util.List;

import javax.validation.Valid;

import com.makowski.fuhrpark.entity.Car;
import com.makowski.fuhrpark.service.CarService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/car")
public class CarController {
    
    CarService carService;

    @PostMapping
    public ResponseEntity<Car> saveCar(@Valid @RequestBody Car car){
        return new ResponseEntity<>(carService.saveCar(car), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id){
        return new ResponseEntity<>(carService.getCar(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getCars(){
        return new ResponseEntity<>(carService.getCars() , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@Valid @RequestBody Car car, @PathVariable Long id ){
        return new ResponseEntity<>(carService.updateCar(id, car), HttpStatus.OK);
    }
}
