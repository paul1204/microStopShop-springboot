package com.paul1204.inventoryservice.service;

import com.paul1204.inventoryservice.dto.InventoryResponse;
import com.paul1204.inventoryservice.model.Inventory;
import com.paul1204.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory -> InventoryResponse.builder().skuCode(inventory.getSkuCode()).isInStock(inventory.getQuantity()> 0).build()
        ).toList();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<String> deleteStock(Long id){
        inventoryRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted stock with ID: " + id);
    }
//    public ResponseEntity<?> getAllSupply(){
//        //public ResponseEntity<List<YourObject>> getListOfObjects() {
//            // Assuming YourObject is your data model class
//            Optional<List<Inventory>> optionalList = Optional.of(inventoryRepository.findAll());
//
//        return optionalList.map(objects -> {
//            if (!objects.isEmpty()) {
//                return new ResponseEntity<>(objects, HttpStatus.OK);
//            } else {
//                // If the list is empty, you can return HttpStatus.NO_CONTENT
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        }).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)); // Handle unexpected null case
//    }
    }


//}
