package app.checkout.mapper;

import app.checkout.dto.CartItemDto;
import app.checkout.dto.OrderItemDto;
import app.checkout.dto.OrderResponseDto;
import app.checkout.dto.ShippingAddressDto;
import app.checkout.entity.OrderEntity;
import app.checkout.entity.OrderItemEntity;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class OrderMapper {

    private OrderMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static OrderItemDto toOrderItemDto(OrderItemEntity entity) {
        if (entity == null) {
            return null;
        }

        OrderItemDto dto = new OrderItemDto();
        dto.setId(entity.getId());
        dto.setProductId(entity.getProductId());
        dto.setProductName(entity.getProductName());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        dto.setSubtotal(entity.getSubtotal());
        return dto;
    }

    public static OrderItemEntity toOrderItemEntity(CartItemDto dto) {
        if (dto == null) {
            return null;
        }

        OrderItemEntity entity = new OrderItemEntity();
        entity.setProductId(dto.getProductId());
        entity.setProductName(dto.getProductName());
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
        entity.setSubtotal(calculateSubtotal(dto.getQuantity(), dto.getPrice()));

        return entity;
    }

    public static OrderResponseDto toOrderResponseDto(OrderEntity entity) {
        if (entity == null) {
            return null;
        }

        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setCustomerId(entity.getCustomerId());
        dto.setCustomerEmail(entity.getCustomerEmail());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setPaymentMethod(entity.getPaymentMethod());
        dto.setPaymentStatus(entity.getPaymentStatus());
        dto.setOrderStatus(entity.getOrderStatus());
        dto.setShippingAddress(entity.getShippingAddress());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setProcessInstanceId(entity.getProcessInstanceId());

        if (entity.getItems() != null && !entity.getItems().isEmpty()) {
            dto.setItems(
                    entity.getItems()
                            .stream()
                            .map(OrderMapper::toOrderItemDto)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public static String formatShippingAddress(ShippingAddressDto address) {
        if (address == null) {
            return "";
        }

        return String.format("%s, %s, %s, %s - Phone: %s",
                address.getStreet(),
                address.getCity(),
                address.getProvince(),
                address.getPostalCode(),
                address.getPhoneNumber()
        );
    }

    private static BigDecimal calculateSubtotal(Integer quantity, BigDecimal price) {
        if (quantity == null || price == null) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(quantity).multiply(price);
    }
}