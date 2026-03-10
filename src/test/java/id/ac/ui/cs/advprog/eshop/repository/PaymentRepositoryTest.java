package id.ac.ui.cs.advprog.eshop.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentRepositoryTest {

    PaymentRepository paymentRepository;
    Payment payment;
    Order order;

    @BeforeEach
    void setUp() {

        paymentRepository = new PaymentRepository();

        Product product = new Product();
        product.setProductId("p1");
        product.setProductName("Test Product");
        product.setProductQuantity(1);

        List<Product> products = new ArrayList<>();
        products.add(product);

        order = new Order(
                "order-1",
                products,
                1708560000L,
                "Safira Sudrajat"
        );

        Map<String,String> data = new HashMap<>();
        data.put("voucherCode","ESHOP1234ABC5678");

        payment = new Payment(
                "payment-1",
                order,
                "VOUCHER",
                data
        );
    }

    @Test
    void testSavePayment() {

        Payment result = paymentRepository.save(payment);

        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testFindByIdIfFound() {

        paymentRepository.save(payment);

        Payment result = paymentRepository.findById("payment-1");

        assertNotNull(result);
        assertEquals("payment-1", result.getId());
    }

    @Test
    void testFindByIdIfNotFound() {

        Payment result = paymentRepository.findById("not-exist");

        assertNull(result);
    }

    @Test
    void testFindAllPayments() {

        paymentRepository.save(payment);

        Map<String,String> data2 = new HashMap<>();
        data2.put("voucherCode","ESHOP1234ABC5678");

        Payment payment2 = new Payment(
                "payment-2",
                order,
                "VOUCHER",
                data2
        );

        paymentRepository.save(payment2);

        List<Payment> payments = paymentRepository.findAll();

        assertEquals(2, payments.size());
    }
}
