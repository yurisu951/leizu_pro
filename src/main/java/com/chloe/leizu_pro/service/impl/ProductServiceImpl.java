package com.chloe.leizu_pro.service.impl;


import com.chloe.leizu_pro.bean.Product;
import com.chloe.leizu_pro.mapper.ProductMapper;
import com.chloe.leizu_pro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public void addProduct(Product product) {
        productMapper.addProduct(product);
    }

    @Override
    public void addProductByCrawler(Product product) {
        productMapper.addProductByCrawler(product);
    }


}
