package com.paul1204.inventoryservice.service;

import com.paul1204.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode){
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<String> deleteStock(Long id){
        inventoryRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted stock with ID: " + id);
    }
}
