package app.checkout.services;

import app.checkout.entity.InventoryEntity;
import java.util.List;

public interface InventoryService {
    InventoryEntity getInventoryByProductId(String productId);
    boolean checkAvailability(String productId, int quantity);
    void reserveStock(String productId, int quantity);
    void releaseStock(String productId, int quantity);
    void confirmDeduction(String productId, int quantity);
    void addStock(String productId, int quantity);
    List<InventoryEntity> findLowStock(int threshold);
}