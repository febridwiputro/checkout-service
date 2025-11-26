package app.checkout.services.impl;

import app.checkout.entity.InventoryEntity;
import app.checkout.exception.InsufficientStockException;
import app.checkout.exception.ResourceNotFoundException;
import app.checkout.repository.InventoryRepository;
import app.checkout.services.InventoryService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class InventoryServiceImpl implements InventoryService {

    @Inject
    InventoryRepository inventoryRepository;

    @Override
    public InventoryEntity getInventoryByProductId(String productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory", productId));
    }

    @Override
    public boolean checkAvailability(String productId, int quantity) {
        InventoryEntity inventory = getInventoryByProductId(productId);
        return inventory.getAvailableStock() >= quantity;
    }

    @Override
    @Transactional
    public void reserveStock(String productId, int quantity) {
        InventoryEntity inventory = getInventoryByProductId(productId);

        if (inventory.getAvailableStock() < quantity) {
            throw new InsufficientStockException(
                    "Insufficient stock for product: " + productId +
                            ". Available: " + inventory.getAvailableStock() +
                            ", Requested: " + quantity
            );
        }

        inventory.setAvailableStock(inventory.getAvailableStock() - quantity);
        inventory.setReservedStock(inventory.getReservedStock() + quantity);
        inventory.setUpdatedAt(LocalDateTime.now());

        inventoryRepository.persist(inventory);
    }

    @Override
    @Transactional
    public void releaseStock(String productId, int quantity) {
        InventoryEntity inventory = getInventoryByProductId(productId);

        if (inventory.getReservedStock() < quantity) {
            throw new IllegalStateException(
                    "Cannot release more than reserved. Product: " + productId +
                            ", Reserved: " + inventory.getReservedStock() +
                            ", Requested: " + quantity
            );
        }

        inventory.setReservedStock(inventory.getReservedStock() - quantity);
        inventory.setAvailableStock(inventory.getAvailableStock() + quantity);
        inventory.setUpdatedAt(LocalDateTime.now());

        inventoryRepository.persist(inventory);
    }

    @Override
    @Transactional
    public void confirmDeduction(String productId, int quantity) {
        InventoryEntity inventory = getInventoryByProductId(productId);

        if (inventory.getReservedStock() < quantity) {
            throw new IllegalStateException(
                    "Cannot confirm more than reserved. Product: " + productId +
                            ", Reserved: " + inventory.getReservedStock() +
                            ", Requested: " + quantity
            );
        }

        inventory.setReservedStock(inventory.getReservedStock() - quantity);
        inventory.setTotalStock(inventory.getTotalStock() - quantity);
        inventory.setUpdatedAt(LocalDateTime.now());

        inventoryRepository.persist(inventory);
    }

    @Override
    @Transactional
    public void addStock(String productId, int quantity) {
        InventoryEntity inventory = getInventoryByProductId(productId);

        inventory.setTotalStock(inventory.getTotalStock() + quantity);
        inventory.setAvailableStock(inventory.getAvailableStock() + quantity);
        inventory.setUpdatedAt(LocalDateTime.now());

        inventoryRepository.persist(inventory);
    }

    @Override
    public List<InventoryEntity> findLowStock(int threshold) {
        return inventoryRepository.findLowStock(threshold);
    }
}