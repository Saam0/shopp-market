package com.shopmarket.controllers.backend;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    //------------------------------------------------------- Creating new product
    @GetMapping("/add")
    public String addProduct(Model model) {

        model.addAttribute("productModel", new Product());
        model.addAttribute("addOrEdit", "/product/add");

        List<GlobalType> catalogList = catalogService.findAll();
        model.addAttribute("catalogList", catalogList);
        model.addAttribute("typeList", catalogService.findAllType());
        model.addAttribute("supplierList", supplierService.findAll());
        return "admin/productForm";
    }

    @PostMapping("/add")
    public String addProductPost(@ModelAttribute("productModel") @Valid Product productModel,
                                 BindingResult bindingResult,
                                 Model model,
                                 @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("addOrEdit", "/product/add");
            List<GlobalType> catalogList = catalogService.findAll();
            model.addAttribute("catalogList", catalogList);
            model.addAttribute("typeList", catalogService.findAllType());
            model.addAttribute("supplierList", supplierService.findAll());
            return "admin/productForm";
        }
        productService.save(productModel, file);
        return "redirect:/product/add";
    }

    //------------------------------------------------------- Creating new product
    @GetMapping("/edit")
    public String editProduct(@RequestParam("id") Long id, Model model) {
        model.addAttribute("productModel", productService.findById(id));
        model.addAttribute("addOrEdit", "/product/edit");

        List<GlobalType> catalogList = catalogService.findAll();
        model.addAttribute("catalogList", catalogList);
        model.addAttribute("typeList", catalogService.findAllType());
        model.addAttribute("supplierList", supplierService.findAll());
        return "admin/productForm";
    }

    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute("productModel") @Valid Product productModel,
                                  @RequestParam("productId") Long productId,
                                  BindingResult bindingResult,
                                  Model model,
                                  @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("addOrEdit", "/product/edit");
            List<GlobalType> catalogList = catalogService.findAll();
            model.addAttribute("catalogList", catalogList);
            model.addAttribute("typeList", catalogService.findAllType());
            model.addAttribute("supplierList", supplierService.findAll());
            return "admin/productForm";
        }
        productService.update(productModel, productId, file);

        return "redirect:/admin/products-table";
    }

    @DeleteMapping("/delete/{id}")
    String deleteProduct(@PathVariable("id") Long id, Model model) {

        // FIXME: 25.09.2022 Երբ orderProduct - ում այդ ապրանքը կա delete չի լինում։ ուղղել:

        productService.delete(id);
        return "redirect:/admin/products-table";
    }

}
