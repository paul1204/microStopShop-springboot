package com.paul1204.inventoryservice.controller;

import com.paul1204.inventoryservice.dto.InventoryResponse;
import com.paul1204.inventoryservice.model.Inventory;
import com.paul1204.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
    return inventoryService.isInStock(skuCode);
    }

//    @GetMapping()
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<?> getAllSupply(){
//        return inventoryService.getAllSupply();
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteStock(@PathVariable("id") Long id){
        return inventoryService.deleteStock(id);
    }
}
