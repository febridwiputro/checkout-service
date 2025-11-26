package app.checkout.repository;

import app.checkout.entity.InventoryEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import javax.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class InventoryRepository implements PanacheRepositoryBase<InventoryEntity, String> {

    public Optional<InventoryEntity> findByProductId(String productId) {
        return find("productId", productId).firstResultOptional();
    }

    public List<InventoryEntity> findLowStock(int threshold) {
        return list("availableStock < ?1", threshold);
    }
}