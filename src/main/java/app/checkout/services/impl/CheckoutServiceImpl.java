package app.checkout.services.impl;

import app.checkout.dto.CheckoutRequestDto;
import app.checkout.dto.OrderResponseDto;
import app.checkout.exception.ResourceNotFoundException;
import app.checkout.services.CheckoutService;
import app.checkout.services.OrderService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.kie.kogito.Model;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CheckoutServiceImpl implements CheckoutService {

    @Inject
    @Named("checkout_process")
    Process<? extends org.kie.kogito.Model> checkoutProcess;

    @Inject
    OrderService orderService;

    @Override
    @Transactional
    public OrderResponseDto processCheckout(CheckoutRequestDto request) {
        try {
            System.out.println("=================================");
            System.out.println("Starting checkout process");
            System.out.println("Customer: " + request.getCustomerId());
            System.out.println("Items: " + request.getItems().size());
            System.out.println("=================================");

            // Create model and set request
            Model model = checkoutProcess.createModel();

            // Set the request variable
            Map<String, Object> params = new HashMap<>();
            params.put("request", request);
            model.fromMap(params);

            // Create and start process instance
            ProcessInstance<? extends org.kie.kogito.Model> instance =
                    checkoutProcess.createInstance(model);
            instance.start();

            System.out.println("Process instance created: " + instance.id());
            System.out.println("Process status: " + instance.status());

            // Get result
            org.kie.kogito.Model result = instance.variables();
            java.util.Map<String, Object> resultMap = result.toMap();

            // Extract order from result
            OrderResponseDto order = (OrderResponseDto) resultMap.get("order");

            if (order == null) {
                System.err.println("WARNING: Order not found in process result");
                // Check if orderId exists at least
                String orderId = (String) resultMap.get("orderId");
                if (orderId != null) {
                    System.out.println("Order ID from process: " + orderId);
                    // Fallback: query order by ID from database
                    order = orderService.getOrderById(orderId);
                }
            } else {
                System.out.println("✓ Order created successfully: " + order.getOrderNumber());
            }

            System.out.println("=================================");

            return order;

        } catch (Exception e) {
            System.err.println("Error in checkout process: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Checkout process failed", e);
        }
    }

    @Override
    public OrderResponseDto getOrderStatus(String orderId) {
        try {
            System.out.println("=================================");
            System.out.println("Fetching order status");
            System.out.println("Order ID: " + orderId);
            System.out.println("=================================");

            // Query order from database
            OrderResponseDto order = orderService.getOrderById(orderId);

            if (order == null) {
                throw new ResourceNotFoundException("Order not found with ID: " + orderId);
            }

            System.out.println("✓ Order found:");
            System.out.println("  Order Number: " + order.getOrderNumber());
            System.out.println("  Customer: " + order.getCustomerId());
            System.out.println("  Total: " + order.getTotalAmount());
            System.out.println("=================================");

            return order;

        } catch (ResourceNotFoundException e) {
            System.err.println("Order not found: " + orderId);
            throw e;
        } catch (Exception e) {
            System.err.println("Error fetching order status: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to get order status", e);
        }
    }
}