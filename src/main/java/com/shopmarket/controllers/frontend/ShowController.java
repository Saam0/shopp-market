package com.shopmarket.controllers.frontend;

import com.shopmarket.models.CartItem;
import com.shopmarket.models.Product;
import com.shopmarket.services.CatalogService;
import com.shopmarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class ShowController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CatalogService catalogService;

    @Qualifier("messageSource")
    @Autowired
    private MessageSource messages;


    @GetMapping("show/type")
    public String ShowProductsByType(@RequestParam("name") String name, Model model, final HttpServletRequest request) {


        try {
            List<Product> productList = catalogService.findByTypeName(name)
                    .orElseThrow(() -> new NullPointerException("No products found with type: " + name)).getProducts();
            model.addAttribute("productList", productList);
            productList.stream().map(Product::getProductName).forEach(System.out::println);
            model.addAttribute("cartItem",new CartItem());
        } catch (NullPointerException e) {
            e.getMessage();
            String errMessage = messages.getMessage("message.product.type.error", null, request.getLocale());
            model.addAttribute("errMessage", errMessage);
            return "show_products";
        }




        return "show_products";
    }
}
