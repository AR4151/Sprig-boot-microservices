package com.microservices.inventory_service.sevices;


import com.microservices.inventory_service.reposistory.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public boolean isInStock(String skuCode, Integer quantity){
        log.info("Start --> Received Request to Check Stock for the skuCode {},with quantity {}",skuCode,quantity);
        boolean isInStock = inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode,quantity);
        log.info(" End -- Product with skuCode {}, and quantity {}, is in stock - {}", skuCode, quantity, isInStock);
        return isInStock;

    }
}
