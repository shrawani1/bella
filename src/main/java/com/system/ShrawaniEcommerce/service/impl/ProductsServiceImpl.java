package com.system.ShrawaniEcommerce.service.impl;

import com.system.ShrawaniEcommerce.entity.Products;
import com.system.ShrawaniEcommerce.pojo.ProductsPojo;
import com.system.ShrawaniEcommerce.repo.ProductCartRepo;
import com.system.ShrawaniEcommerce.repo.ProductsRepo;
import com.system.ShrawaniEcommerce.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {
    public final ProductsRepo productsRepo;
    public final ProductCartRepo productCartRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/EazyMart";
    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/Eazymart/";
        File file = new File(filePath + fileName);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return base64;
    }


    @Override
    public String save(ProductsPojo productsPojo) throws IOException {
        Products products;
        if (productsPojo.getId() != null) {
            products = productsRepo.findById(productsPojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            products = new Products();
        }
        if(productsPojo.getId()!=null){
            products.setId(productsPojo.getId());
        }
        products.setName(productsPojo.getName());
        products.setQuantity(productsPojo.getQuantity());
        products.setPrice(productsPojo.getPrice());
        if(productsPojo.getPhoto()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, productsPojo.getPhoto().getOriginalFilename());
            fileNames.append(productsPojo.getPhoto().getOriginalFilename());
            Files.write(fileNameAndPath, productsPojo.getPhoto().getBytes());

            products.setPhoto(productsPojo.getPhoto().getOriginalFilename());
        }

        productsRepo.save(products);
        return "created";
    }

    @Override
    public List<Products> fetchAll() {
        return findAllInList(productsRepo.findAll());
    }

    private List<Products> findAllInList(List<Products> list) {
        Stream<Products> allCart=list.stream().map(products ->
                Products.builder()
                        .id(products.getId())
                        .imageBase64(getImageBase64(products.getPhoto()))
                        .name(products.getName())
                        .quantity(products.getQuantity())
                        .price(products.getPrice())
                        .build()
        );

        list = allCart.toList();
        return list;
    }

    @Override
    public Products fetchById(Integer id) {
        return productsRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));
    }

    @Override
    public void deleteById(Integer id) {
        productsRepo.deleteById(id);
    }

}