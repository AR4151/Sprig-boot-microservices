package com.microservices.inventory_service.reposistory;

import com.microservices.inventory_service.model.Inventory;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);
}
