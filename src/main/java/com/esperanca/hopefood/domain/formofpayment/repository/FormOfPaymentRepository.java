package com.esperanca.hopefood.domain.formofpayment.repository;

import com.esperanca.hopefood.domain.formofpayment.entities.FormOfPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormOfPaymentRepository extends
		JpaRepository<FormOfPayment, Long> {

}
