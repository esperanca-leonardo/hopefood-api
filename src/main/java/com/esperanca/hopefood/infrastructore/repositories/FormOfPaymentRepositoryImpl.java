package com.esperanca.hopefood.infrastructore.repositories;

import com.esperanca.hopefood.domain.models.FormOfPayment;
import com.esperanca.hopefood.domain.repositories.FormOfPaymentRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class FormOfPaymentRepositoryImpl implements FormOfPaymentRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<FormOfPayment> findAll() {
    return this.entityManager
        .createQuery("from FormOfPayment", FormOfPayment.class)
        .getResultList();
  }

  @Override
  public FormOfPayment findById(Long id) {
    return this.entityManager.find(FormOfPayment.class, id);
  }

  @Override
  @Transactional
  public FormOfPayment save(FormOfPayment formOfPayment) {
    return this.entityManager.merge(formOfPayment);
  }

  @Override
  @Transactional
  public void remove(Long id) {
    var formOfPayment = this.findById(id);
    this.entityManager.remove(formOfPayment);
  }
}
