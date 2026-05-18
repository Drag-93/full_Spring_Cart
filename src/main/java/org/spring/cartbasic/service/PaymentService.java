package org.spring.cartbasic.service;

import org.spring.cartbasic.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    void insertPayment(PaymentDto paymentDto);

    List<PaymentDto> paymentListFn(Long memberId);
}
