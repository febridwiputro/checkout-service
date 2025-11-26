package app.checkout.controller;

import app.checkout.dto.BaseResponse;
import app.checkout.dto.CheckoutRequestDto;
import app.checkout.services.CheckoutService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/checkout")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Checkout", description = "APIs for checkout process (BPMN-style implementation)")
public class CheckoutController {

    @Inject
    CheckoutService checkoutService;

    @POST
    @Operation(
        summary = "Process Checkout",
        description = "Execute checkout process following BPMN flow: " +
                     "Validate Cart → Check Stock → Reserve Stock → Create Order → " +
                     "Process Payment → Confirm Deduction → Update Order Status"
    )
    public Response processCheckout(@Valid CheckoutRequestDto request) {
        var order = checkoutService.processCheckout(request);
        return Response.ok(BaseResponse.created("Checkout completed successfully", order)).build();
    }

    @GET
    @Path("/order/{orderId}")
    @Operation(summary = "Get Order Status")
    public Response getOrderStatus(@PathParam("orderId") String orderId) {
        var order = checkoutService.getOrderStatus(orderId);
        return Response.ok(BaseResponse.success("Order status retrieved", order)).build();
    }
}