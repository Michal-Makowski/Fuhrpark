package com.makowski.fuhrpark.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makowski.fuhrpark.entity.Cost;
import com.makowski.fuhrpark.service.CostService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/cost")
public class CostController {
    
    CostService costService;

    @PostMapping("/car/{carId}/costType/{costTypeId}")
    public ResponseEntity<Cost> saveCost(@Valid @RequestBody Cost cost, @PathVariable Long carId, @PathVariable Long costTypeId){
        return new ResponseEntity<>(costService.saveCost(cost, carId, costTypeId), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cost> getCost(@PathVariable Long id){
        return new ResponseEntity<>(costService.getCost(id), HttpStatus.OK);
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<Cost>> getCostsByCarId(@PathVariable Long carId){
        return new ResponseEntity<>(costService.getCostsByCarId(carId), HttpStatus.OK);
    }

    @GetMapping("/car/{carId}/costType/{costTypeId}")
    public ResponseEntity<List<Cost>> getCostByCarIdAndCostTypeId(@PathVariable Long carId, @PathVariable Long costTypeId){
        return new ResponseEntity<>(costService.getCostByCarIdAndCostTypeId(carId, costTypeId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cost>> getCosts(){
        return new ResponseEntity<>(costService.getCosts(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cost> deleteCost(@PathVariable Long id){
        costService.deleteCost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cost> updateCost(@Valid @RequestBody Cost cost, @PathVariable Long id ){
        return new ResponseEntity<>(costService.updateCost(id, cost), HttpStatus.OK);
    }
}
