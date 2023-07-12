package com.esperanca.hopefood.domain.repositories;

import com.esperanca.hopefood.domain.models.FormOfPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormOfPaymentRepository extends
		JpaRepository<FormOfPayment, Long> {

}
