import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class CheckoutProcessTest {

    private String createValidRequestBody() {
        return """
        {
            "request": {
                "customerId": "TEST-001",
                "customerEmail": "test@example.com",
                "items": [{
                    "productId": "60c65ff9-46a6-4b39-b481-52d8168867b7",
                    "productName": "Test Product",
                    "quantity": 1,
                    "price": 100000.0
                }],
                "paymentMethod": "CREDIT_CARD",
                "shippingAddress": {
                    "street": "Jl. Test",
                    "city": "Jakarta",
                    "province": "DKI Jakarta",
                    "postalCode": "12190",
                    "phoneNumber": "08123456789"
                },
                "totalAmount": 100000.0
            }
        }
        """;
    }

    @Test
    public void testStartCheckoutProcess() {
        given()
                .contentType("application/json")
                .body(createValidRequestBody())
                .when()
                .post("/checkout_process")
                .then()
                .statusCode(201)
                .body("id", notNullValue());
    }

    @Test
    public void testGetAllProcessInstances() {
        given()
                .when()
                .get("/checkout_process")
                .then()
                .statusCode(200)
                .body("", notNullValue());
    }
}