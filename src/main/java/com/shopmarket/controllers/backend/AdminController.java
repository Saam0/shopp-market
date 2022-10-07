package com.shopmarket.controllers.backend;

import com.shopmarket.models.OrderProduct;
import com.shopmarket.models.Product;
import com.shopmarket.services.OrderService;
import com.shopmarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @GetMapping("/admin-panel")
    public String getAdminPanel(Model model) {

        model.addAttribute("numberOfProducts", productService.findAll().size());

        return "admin/adminPanel";
    }

//    @GetMapping("/products-table")
//    public String getProductsTable(Model model, String keyword) {
//        List<Product> products = null;
//        if (keyword == null) {
//            products = productService.findAll();
//        } else {
//            products = productService.findByKeyword(keyword);
////            products = productService.findByName(keyword);
//            System.err.println(products.size());
//        }
//        model.addAttribute("products", products);
//        return "admin/products-table";
//    }

    @GetMapping("/products-table/page/{pageNumber}/{field}")
    public String getPageWithSort(Model model,
                                   @PathVariable("pageNumber") int currentPage,
                                   @PathVariable("field") String field,
                                   @PathParam("sortDir") String sortDir) {

        Page<Product> page = productService.findAllWithSort(field,sortDir,currentPage);
        List<Product> products = page.getContent();
        int totalPage = page.getTotalPages();
        long totalItems = page.getTotalElements();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("totalItems", totalItems);

        model.addAttribute("field", field);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reversSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("products", products);

        return "admin/products-table";
    }


    @GetMapping("/products-table")
    public String getProductsAllPages(Model model) {

        return getProductsOnePage(model,1);
    }

    @GetMapping("/products-table/page/{pageNumber}")
    public String getProductsOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<Product> page = productService.findPage(currentPage);
        int totalPage = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Product> products = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("products", products);
        return "admin/products-table";
    }
}
