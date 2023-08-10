package com.system.ShrawaniEcommerce.service;

import com.system.ShrawaniEcommerce.entity.Products;
import com.system.ShrawaniEcommerce.pojo.ProductsPojo;

import java.io.IOException;
import java.util.List;

public interface ProductsService {

    String save(ProductsPojo productsPojo) throws IOException;

    List<Products> fetchAll();

    Products fetchById(Integer id);

    void deleteById(Integer id);

}
