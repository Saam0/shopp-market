package com.shopmarket.services.impl;

import com.shopmarket.models.*;
import com.shopmarket.models.catalog.Type;
import com.shopmarket.repositories.ProductDetailsRepository;
import com.shopmarket.repositories.ProductRepository;
import com.shopmarket.repositories.StockRepository;
import com.shopmarket.repositories.SupplierRepository;
import com.shopmarket.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private ProductDetailsService productDetailsService;

    @Autowired
    private StockService stockService;


    @Transactional
    @Override
    public Product save(Product product, MultipartFile file) {

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
    public Product update(Product product, Long productId, MultipartFile file) {

        Product productToBeUpdated = findById(productId).get();
        productToBeUpdated.setProductName(product.getProductName());
        productToBeUpdated.setType(catalogService.findByTypeName(product.getType().getTypeName()).get());

        stockService.update(product.getStock(), productToBeUpdated.getStock().getId());
        productDetailsService.update(product.getProductDetails(), productToBeUpdated.getProductDetails().getId());
        pictureService.update(file, productId);
        return productRepository.save(productToBeUpdated);
    }
    @Transactional
    @Override
    public void delete(Long productId){

        Product product = findById(productId).get();
        Stock stock = product.getStock();
        stockService.delete(stock);

//        productRepository.delete(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }


    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByProductName(name);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Page<Product> findPage(int pageNumber){

        Pageable pageable = PageRequest.of(pageNumber-1,5);
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findByKeyword(String keyword) {
        return productRepository.findByKeyword(keyword);
    }

    @Override
    public Page<Product> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort= Sort.by(Sort.Direction.ASC.name());
        if (direction.equalsIgnoreCase(Sort.Direction.ASC.name())){
            sort = Sort.by(field).ascending();
        }else {
            sort = Sort.by(field).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber-1, 5, sort);


        return productRepository.findAll(pageable);
    }
}
