package app.checkout.services;

import app.checkout.dto.CheckoutRequestDto;
import app.checkout.dto.OrderResponseDto;

public interface CheckoutService {
    OrderResponseDto processCheckout(CheckoutRequestDto request);
    OrderResponseDto getOrderStatus(String orderId);
}