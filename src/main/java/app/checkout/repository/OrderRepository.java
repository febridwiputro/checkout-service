package app.checkout.repository;

import app.checkout.entity.OrderEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import javax.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrderRepository implements PanacheRepositoryBase<OrderEntity, String> {

    public Optional<OrderEntity> findByOrderNumber(String orderNumber) {
        return find("orderNumber", orderNumber).firstResultOptional();
    }

    public List<OrderEntity> findByCustomerId(String customerId) {
        return list("customerId", customerId);
    }

    public List<OrderEntity> findByOrderStatus(String orderStatus) {
        return list("orderStatus", orderStatus);
    }

    public boolean existsByOrderNumber(String orderNumber) {
        return count("orderNumber", orderNumber) > 0;
    }
}