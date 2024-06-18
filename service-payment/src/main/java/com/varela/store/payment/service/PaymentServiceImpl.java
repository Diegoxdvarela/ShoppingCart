package com.varela.store.payment.service;

import com.varela.store.payment.entity.Payment;
import com.varela.store.payment.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService{
    private final PaymentRepository paymentRepository;

    @Override
    public List<Payment> allPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment update(Payment payment) {
        Payment payment1 = getById(payment.getId());
        if(payment1!=null){
            payment1.setDate(new Date());
            payment1.setStatus("A");
            payment1.setOrderId(payment.getOrderId());
            return paymentRepository.save(payment1);
        }else{
            return null;
        }
    }

    @Override
    public Payment getById(Long id) {
        Optional<Payment> optional = paymentRepository.findById(id);
        return optional.orElse(null);

    }

    @Override
    public Payment delete(Long id) {
        return null;
    }
}
