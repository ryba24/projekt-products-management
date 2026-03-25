package com.uep.wap.repository;

import com.uep.wap.model.PaymentStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentStatusRepository extends CrudRepository<PaymentStatus, Integer> {
}
