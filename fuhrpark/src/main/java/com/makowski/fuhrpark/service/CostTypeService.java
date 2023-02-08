package com.makowski.fuhrpark.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.makowski.fuhrpark.entity.CostType;
import com.makowski.fuhrpark.exception.EntityNotFoundException;
import com.makowski.fuhrpark.repository.CostTypeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CostTypeService {
    
    private CostTypeRepository costTypeRepository;

    public CostType saveCostType(CostType costType){
        return costTypeRepository.save(costType);
    }

    public CostType getCostType(Long id){
        Optional<CostType> costType = costTypeRepository.findById(id);
        return unwrapCostType(costType, id);
    }

    public List<CostType> getCostTypes(){
        return (List<CostType>)costTypeRepository.findAll();
    }

    public CostType updateCostType(Long id, CostType costType){
        Optional<CostType> updateCostType = costTypeRepository.findById(id);
        CostType unwrappedCostType = unwrapCostType(updateCostType, id);
        unwrappedCostType.setCostType(costType.getCostType());
        return costTypeRepository.save(unwrappedCostType);
    }

    public void deleteCostType(Long id){
        costTypeRepository.deleteById(id);
    }

    static CostType unwrapCostType(Optional<CostType> entity, Long id) {
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, CostType.class);
    }
}
