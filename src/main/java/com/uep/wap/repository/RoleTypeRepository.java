package com.uep.wap.repository;

import com.uep.wap.model.RoleType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleTypeRepository extends CrudRepository<RoleType, Integer> {
}
