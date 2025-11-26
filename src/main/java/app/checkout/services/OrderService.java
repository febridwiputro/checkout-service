package app.checkout.services;

import app.checkout.dto.CheckoutRequestDto;
import app.checkout.dto.OrderResponseDto;
import app.checkout.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    OrderEntity createOrder(CheckoutRequestDto request, String processInstanceId);
    OrderResponseDto getOrderById(String id);
    OrderResponseDto getOrderByNumber(String orderNumber);
    List<OrderResponseDto> getOrdersByCustomerId(String customerId);
    void updateOrderStatus(String orderId, String orderStatus, String paymentStatus);
    void confirmOrder(String orderId);
    void cancelOrder(String orderId, String reason);
    void processOrder(String orderId);
    List<OrderResponseDto> getAllOrders();
    List<OrderResponseDto> getOrdersByStatus(String orderStatus);
    boolean existsByOrderNumber(String orderNumber);
}