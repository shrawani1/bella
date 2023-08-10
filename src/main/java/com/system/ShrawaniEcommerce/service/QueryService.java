package com.system.ShrawaniEcommerce.service;

import com.system.ShrawaniEcommerce.entity.Queries;
import com.system.ShrawaniEcommerce.pojo.QueriesPojo;

import java.util.List;

public interface QueryService {
    List<Queries> fetchAll();

    String save(QueriesPojo queriesPojo);
}
