package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {

        String id = UUID.randomUUID().toString();
        Payment payment = new Payment(id, order, method, paymentData);

        if (method.equals("VOUCHER")) {
            validateVoucher(payment, paymentData);
        }
        if (method.equals("COD")) {
            validateCOD(payment, paymentData);
        }
        return paymentRepository.save(payment);
    }

    public Payment setStatus(Payment payment, String status) {

        payment.setStatus(status);

        if (status.equals("SUCCESS")) {
            payment.getOrder().setStatus("SUCCESS");
        }

        if (status.equals("REJECTED")) {
            payment.getOrder().setStatus("FAILED");
        }

        return payment;
    }

    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    private void validateVoucher(Payment payment, Map<String,String> data) {

        String voucher = data.get("voucherCode");

        if(voucher == null) {
            payment.setStatus("REJECTED");
            return;
        }

        boolean validLength = voucher.length() == 16;
        boolean validPrefix = voucher.startsWith("ESHOP");

        int digitCount = 0;
        for(char c : voucher.toCharArray()) {
            if(Character.isDigit(c)) {
                digitCount++;
            }
        }

        if(validLength && validPrefix && digitCount == 8) {
            payment.setStatus("SUCCESS");
        } else {
            payment.setStatus("REJECTED");
        }
    }

    private void validateCOD(Payment payment, Map<String,String> data) {

        String address = data.get("address");
        String deliveryFee = data.get("deliveryFee");

        if(address == null || address.isEmpty()
                || deliveryFee == null || deliveryFee.isEmpty()) {

            payment.setStatus("REJECTED");
        } else {
            payment.setStatus("SUCCESS");
        }
    }
}