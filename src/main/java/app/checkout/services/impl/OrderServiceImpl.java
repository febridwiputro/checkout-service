package app.checkout.services.impl;

import app.checkout.dto.CheckoutRequestDto;
import app.checkout.dto.OrderResponseDto;
import app.checkout.entity.OrderEntity;
import app.checkout.entity.OrderItemEntity;
import app.checkout.exception.ResourceNotFoundException;
import app.checkout.mapper.OrderMapper;
import app.checkout.repository.OrderRepository;
import app.checkout.services.OrderService;
import app.checkout.utils.HelperUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrderServiceImpl implements OrderService {

    @Inject
    OrderRepository orderRepository;

    @Override
    @Transactional
    public OrderEntity createOrder(CheckoutRequestDto request, String processInstanceId) {
        String orderNumber = HelperUtil.generateOrderNumber();
        String shippingAddress = OrderMapper.formatShippingAddress(request.getShippingAddress());

        OrderEntity order = new OrderEntity();
        order.setOrderNumber(orderNumber);
        order.setCustomerId(request.getCustomerId());
        order.setCustomerEmail(request.getCustomerEmail());
        order.setTotalAmount(request.getTotalAmount());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setShippingAddress(shippingAddress);
        order.setProcessInstanceId(processInstanceId);

        for (var item : request.getItems()) {
            OrderItemEntity orderItem = OrderMapper.toOrderItemEntity(item);
            orderItem.setOrder(order);
            order.getItems().add(orderItem);
        }

        orderRepository.persist(order);
        return order;
    }

    @Override
    public OrderResponseDto getOrderById(String id) {
        OrderEntity order = orderRepository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", id));
        return OrderMapper.toOrderResponseDto(order);
    }

    @Override
    public OrderResponseDto getOrderByNumber(String orderNumber) {
        OrderEntity order = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderNumber));
        return OrderMapper.toOrderResponseDto(order);
    }

    @Override
    public List<OrderResponseDto> getOrdersByCustomerId(String customerId) {
        return orderRepository.findByCustomerId(customerId).stream()
                .map(OrderMapper::toOrderResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateOrderStatus(String orderId, String orderStatus, String paymentStatus) {
        OrderEntity order = orderRepository.findByIdOptional(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));

        order.setOrderStatus(orderStatus);
        order.setPaymentStatus(paymentStatus);
        orderRepository.persist(order);
    }

    @Override
    @Transactional
    public void confirmOrder(String orderId) {
        updateOrderStatus(orderId, "CONFIRMED", "PAID");
    }

    @Override
    @Transactional
    public void cancelOrder(String orderId, String reason) {
        updateOrderStatus(orderId, "CANCELLED", "FAILED");
    }

    @Override
    @Transactional
    public void processOrder(String orderId) {
        updateOrderStatus(orderId, "PROCESSING", "PAID");
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.listAll().stream()
                .map(OrderMapper::toOrderResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDto> getOrdersByStatus(String orderStatus) {
        return orderRepository.findByOrderStatus(orderStatus).stream()
                .map(OrderMapper::toOrderResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByOrderNumber(String orderNumber) {
        return orderRepository.existsByOrderNumber(orderNumber);
    }
}