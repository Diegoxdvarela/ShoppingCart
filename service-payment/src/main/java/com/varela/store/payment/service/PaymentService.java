package com.varela.store.payment.service;

import com.varela.store.payment.entity.Payment;

import java.util.List;

public interface PaymentService {
    public List<Payment> allPayments();
    public Payment save(Payment payment);
    public  Payment update(Payment payment);
    public Payment getById(Long ig);
    public Payment delete(Long id);
}
