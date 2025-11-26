package app.checkout.bpm;

import app.checkout.dto.CheckoutRequestDto;
import app.checkout.dto.OrderResponseDto;
import java.io.Serializable;

public class CheckoutProcessData implements Serializable {
    private CheckoutRequestDto request;
    private OrderResponseDto order;
    private String orderId;
    private Boolean stockAvailable;
    private Boolean paymentSuccess;
    private String errorMessage;

    public CheckoutProcessData() {}

    public CheckoutProcessData(CheckoutRequestDto request) {
        this.request = request;
    }

    public CheckoutRequestDto getRequest() {
        return request;
    }

    public void setRequest(CheckoutRequestDto request) {
        this.request = request;
    }

    public OrderResponseDto getOrder() {
        return order;
    }

    public void setOrder(OrderResponseDto order) {
        this.order = order;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Boolean getStockAvailable() {
        return stockAvailable;
    }

    public Boolean isStockAvailable() {  // ← ADD THIS
        return stockAvailable;
    }

    public void setStockAvailable(Boolean stockAvailable) {
        this.stockAvailable = stockAvailable;
    }

    public Boolean getPaymentSuccess() {
        return paymentSuccess;
    }

    public Boolean isPaymentSuccess() {  // ← ADD THIS
        return paymentSuccess;
    }

    public void setPaymentSuccess(Boolean paymentSuccess) {
        this.paymentSuccess = paymentSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "CheckoutProcessData{" +
                "orderId='" + orderId + '\'' +
                ", stockAvailable=" + stockAvailable +
                ", paymentSuccess=" + paymentSuccess +
                '}';
    }
}