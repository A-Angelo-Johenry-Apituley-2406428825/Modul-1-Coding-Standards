package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentTest {

    Payment payment;
    Order order;
    Map<String, String> paymentData;

    @BeforeEach
    void setUp() {

        order = new Order(
                "order-1",
                null,
                1708560000L,
                "Safira Sudrajat"
        );

        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        payment = new Payment(
                "payment-1",
                order,
                "VOUCHER",
                paymentData
        );
    }

    @Test
    void testPaymentConstructor() {
        assertEquals("payment-1", payment.getId());
        assertEquals(order, payment.getOrder());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testDefaultStatus() {
        assertEquals("PENDING", payment.getStatus());
    }

    @Test
    void testSetStatus() {
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }
}
