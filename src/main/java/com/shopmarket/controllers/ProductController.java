package com.shopmarket.controllers;

import com.shopmarket.models.*;
import com.shopmarket.models.catalog.GlobalType;
import com.shopmarket.services.CatalogService;
import com.shopmarket.services.ProductService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private Logger logger;

    @GetMapping("/add")
    public String addProduct(Model model){



        model.addAttribute("productModel", new Product());




        List<GlobalType> catalogList = catalogService.findAll();
        model.addAttribute("catalogList",catalogList);
        model.addAttribute("typeList",catalogService.findAllType());
        return "admin/productForm";
    }



    @PostMapping("/add")
    public String addProductPost(@ModelAttribute("productModel")  Product productModel,
//                                 @ModelAttribute("stock") Stock stock,
                                 Model model){

        logger.info("aaaaaaaaaaaaa");
        Stock stock = productModel.getStock();
        ProductDetails productDetails = productModel.getProductDetails();
        Picture picture = productModel.getPicture();
        Supplier supplier = stock.getSupplier();

        logger.info("price : " + productModel.getStock().getPurchasePrice());
        productService.save(productModel);

        return "admin/productForm";
    }
}
