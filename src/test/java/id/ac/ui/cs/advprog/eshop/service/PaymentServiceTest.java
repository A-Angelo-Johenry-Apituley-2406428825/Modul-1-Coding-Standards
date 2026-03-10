package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @InjectMocks
    PaymentService paymentService;

    @Mock
    PaymentRepository paymentRepository;

    Order order;

    @BeforeEach
    void setUp() {

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
    }

    // ===============================
    // ADD PAYMENT - VOUCHER
    // ===============================

    @Test
    void testAddPaymentVoucherValid() {

        Map<String,String> data = new HashMap<>();
        data.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("1", order, "VOUCHER", data);
        payment.setStatus("SUCCESS");

        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order,"VOUCHER",data);

        assertEquals("SUCCESS", result.getStatus());
        verify(paymentRepository,times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPaymentVoucherInvalid() {

        Map<String,String> data = new HashMap<>();
        data.put("voucherCode","INVALID");

        Payment payment = new Payment("1",order,"VOUCHER",data);
        payment.setStatus("REJECTED");

        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order,"VOUCHER",data);

        assertEquals("REJECTED", result.getStatus());
    }

    // ===============================
    // SET STATUS
    // ===============================

    @Test
    void testSetStatusSuccess() {

        Payment payment = new Payment("1",order,"VOUCHER",new HashMap<>());

        Payment result = paymentService.setStatus(payment,"SUCCESS");

        assertEquals("SUCCESS",result.getStatus());
        assertEquals("SUCCESS",order.getStatus());
    }

    @Test
    void testSetStatusRejected() {

        Payment payment = new Payment("1",order,"VOUCHER",new HashMap<>());

        Payment result = paymentService.setStatus(payment,"REJECTED");

        assertEquals("REJECTED",result.getStatus());
        assertEquals("FAILED",order.getStatus());
    }

    // ===============================
    // GET PAYMENT
    // ===============================

    @Test
    void testGetPaymentFound() {

        Payment payment = new Payment("1",order,"VOUCHER",new HashMap<>());

        doReturn(payment).when(paymentRepository).findById("1");

        Payment result = paymentService.getPayment("1");

        assertEquals("1",result.getId());
    }

    @Test
    void testGetPaymentNotFound() {

        doReturn(null).when(paymentRepository).findById("99");

        Payment result = paymentService.getPayment("99");

        assertNull(result);
    }

    // ===============================
    // GET ALL PAYMENTS
    // ===============================

    @Test
    void testGetAllPayments() {

        List<Payment> payments = new ArrayList<>();

        payments.add(new Payment("1",order,"VOUCHER",new HashMap<>()));
        payments.add(new Payment("2",order,"COD",new HashMap<>()));

        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> result = paymentService.getAllPayments();

        assertEquals(2,result.size());
    }

    // ===============================
    // CASH ON DELIVERY
    // ===============================

    @Test
    void testCODValid() {

        Map<String,String> data = new HashMap<>();
        data.put("address","Jakarta");
        data.put("deliveryFee","10000");

        Payment payment = new Payment("1",order,"COD",data);
        payment.setStatus("SUCCESS");

        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order,"COD",data);

        assertEquals("SUCCESS",result.getStatus());
    }

    @Test
    void testCODInvalid() {

        Map<String,String> data = new HashMap<>();
        data.put("address","");
        data.put("deliveryFee","");

        Payment payment = new Payment("1",order,"COD",data);
        payment.setStatus("REJECTED");

        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order,"COD",data);

        assertEquals("REJECTED",result.getStatus());
    }

}