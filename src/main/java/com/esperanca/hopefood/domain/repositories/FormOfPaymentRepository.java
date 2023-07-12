package com.esperanca.hopefood.domain.repositories;

import com.esperanca.hopefood.domain.models.FormOfPayment;

import java.util.List;

public interface FormOfPaymentRepository {

  List<FormOfPayment> findAll();

  FormOfPayment findById(Long id);

  FormOfPayment save(FormOfPayment formOfPayment);

  void delete(Long id);
}
