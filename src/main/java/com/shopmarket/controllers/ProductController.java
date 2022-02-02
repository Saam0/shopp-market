package com.shopmarket.controllers;

import com.shopmarket.models.*;
import com.shopmarket.models.catalog.GlobalType;
import com.shopmarket.services.CatalogService;
import com.shopmarket.services.ProductService;
import com.shopmarket.services.StockService;
import com.shopmarket.services.SupplierService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private StockService stockService;

    @Autowired
    private Logger logger;

    @GetMapping("/add")
    public String addProduct(Model model){

        model.addAttribute("productModel", new  Product());

        List<GlobalType> catalogList = catalogService.findAll();
        model.addAttribute("catalogList",catalogList);
        model.addAttribute("typeList",catalogService.findAllType());
        model.addAttribute("supplierList",supplierService.findAll());
        return "admin/productForm";
    }



    @PostMapping("/add")
    public String addProductPost(@ModelAttribute("productModel") @Valid Product productModel,
                                 BindingResult bindingResult,
                                 Model model){
        if (bindingResult.hasErrors()) {
            List<GlobalType> catalogList = catalogService.findAll();
            model.addAttribute("catalogList",catalogList);
            model.addAttribute("typeList",catalogService.findAllType());
            model.addAttribute("supplierList",supplierService.findAll());
            return "admin/productForm";
        }

//        Product product = new Product();
//        product.setStock(stockService.save(productModel.getStock()));
//        product.getStock().setSupplier(productModel.getStock().getSupplier());
//        productService.save(productModel);
//

//        stockService.save(productModel.getStock());
//
//       if (supplierService.findById(productModel.getStock().getSupplier().getId()).isPresent()){
//
//       }
//






        logger.info("price : " + productModel.getStock().getPurchasePrice());
        logger.info("price : " + productModel.getStock().getSupplier().getSupplierName());
        logger.info("price : " + productModel.getType().getTypeName());
        productService.save(productModel);

        return "redirect:/product/add";
    }
}
