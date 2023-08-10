package com.system.ShrawaniEcommerce.repo;

import com.system.ShrawaniEcommerce.entity.Queries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueriesRepo extends JpaRepository<Queries, Integer> {

}
