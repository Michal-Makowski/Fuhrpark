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

import com.makowski.fuhrpark.entity.CostType;
import com.makowski.fuhrpark.service.CostTypeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/costType")
public class CostTypeController {
    
    CostTypeService costTypeService;

    @PostMapping
    public ResponseEntity<CostType> saveCostType(@Valid @RequestBody CostType costType){
        return new ResponseEntity<>(costTypeService.saveCostType(costType), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostType> getCostType(@PathVariable Long id){
        return new ResponseEntity<>(costTypeService.getCostType(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CostType>> getCostTypes(){
        return new ResponseEntity<>(costTypeService.getCostTypes(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CostType> updateCostType(@PathVariable Long id, @Valid @RequestBody CostType costType){
        return new ResponseEntity<>(costTypeService.updateCostType(id, costType), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CostType> deleteCostType(@PathVariable Long id){
        costTypeService.deleteCostType(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
