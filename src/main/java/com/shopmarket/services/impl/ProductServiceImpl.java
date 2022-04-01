package com.shopmarket.services.impl;

import com.shopmarket.models.*;
import com.shopmarket.models.catalog.Type;
import com.shopmarket.repositories.ProductDetailsRepository;
import com.shopmarket.repositories.ProductRepository;
import com.shopmarket.repositories.StockRepository;
import com.shopmarket.repositories.SupplierRepository;
import com.shopmarket.services.CatalogService;
import com.shopmarket.services.PictureService;
import com.shopmarket.services.ProductService;
import com.shopmarket.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductDetailsRepository productDetailsRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private CatalogService catalogService;

    @Autowired
    private PictureService pictureService;

    @Transactional
    @Override
    public Product save(Product product, MultipartFile file) {
//        if (product.getProductDetails()!=null){
//            p
//        }
        Picture picture = pictureService.save(file);
        ProductDetails productDetails = productDetailsRepository.save(product.getProductDetails());
        Supplier supplier = supplierService.findBySupplierName(product.getStock().getSupplier().getSupplierName()).get();
        Type type = catalogService.findByTypeName(product.getType().getTypeName()).get();

        product.setProductDetails(productDetails);
        product.setType(type);
        product.setPicture(picture);
        productRepository.save(product);

        Stock stock = product.getStock();
        stock.setSupplier(supplier);
        stockRepository.save(stock);
        stock.setProduct(product);
        return productRepository.save(product);
    }


    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }


    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByProductName(name);
    }
}
