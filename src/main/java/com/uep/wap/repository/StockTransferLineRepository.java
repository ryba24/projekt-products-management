package com.uep.wap.repository;

import com.uep.wap.model.StockTransferLine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockTransferLineRepository extends CrudRepository<StockTransferLine, Integer> {
}
