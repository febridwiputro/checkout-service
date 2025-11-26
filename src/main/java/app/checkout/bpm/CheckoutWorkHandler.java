package app.checkout.bpm;

import app.checkout.dto.CheckoutRequestDto;
import app.checkout.entity.OrderEntity;
import app.checkout.exception.InsufficientStockException;
import app.checkout.services.InventoryService;
import app.checkout.services.OrderService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CheckoutWorkHandler {

    @Inject
    OrderService orderService;

    @Inject
    InventoryService inventoryService;

    // BPMN Task 1
    public void validateCart(CheckoutProcessData model) {
        CheckoutRequestDto request = model.getRequest();
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }
    }

    // BPMN Task 2
    public void checkStock(CheckoutProcessData model) {
        for (var item : model.getRequest().getItems()) {
            if (!inventoryService.checkAvailability(item.getProductId(), item.getQuantity())) {
                throw new InsufficientStockException("Stock unavailable");
            }
        }
    }

    // BPMN Task 3
    public void reserveStock(CheckoutProcessData model) {
        for (var item : model.getRequest().getItems()) {
            inventoryService.reserveStock(item.getProductId(), item.getQuantity());
        }
    }

    // BPMN Task 4
    public void createOrder(CheckoutProcessData model) {
        OrderEntity order = orderService.createOrder(model.getRequest(), null);
        model.setOrder(orderService.getOrderById(order.getId()));
    }

    // BPMN Task 5
    public void processPayment(CheckoutProcessData model) {
        // always OK for demo
    }

    // BPMN Task 6
    public void confirmDeduction(CheckoutProcessData model) {
        for (var item : model.getRequest().getItems()) {
            inventoryService.confirmDeduction(item.getProductId(), item.getQuantity());
        }
    }

    // BPMN Task 7
    public void confirmOrder(CheckoutProcessData model) {
        orderService.confirmOrder(model.getOrder().getId());
    }

    // BPMN Task 8
    public void sendNotification(CheckoutProcessData model) {
        System.out.println("Sending notification for order " + model.getOrder().getOrderNumber());
    }
}
