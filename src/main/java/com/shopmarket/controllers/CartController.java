package com.shopmarket.controllers;

import com.shopmarket.models.*;
import com.shopmarket.models.DTO.CarItemDTO;
import com.shopmarket.services.CartService;
import com.shopmarket.services.ProductService;
import com.shopmarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import org.slf4j.Logger;

@Controller
@RequestMapping("/cart")
//@SessionAttributes("cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private Logger logger;

    @GetMapping("/add/product")
    public String addCart(@ModelAttribute("cart") Cart cart,
                          @RequestParam("id") Long id,
                          @RequestParam("quantity") double quantity,
                          Principal principal,
                          Model model, HttpServletRequest request) {

        String url = request.getHeader("referer");
        String substringURL = url.substring(6);

//         If there is no cart, create a new cart;
        cart = cartService.getOrCreateCart(principal.getName());
        cartService.addItemToCart(cart,null, id, quantity);
        model.addAttribute("cart", cart);
        return "redirect:/" + substringURL;
    }


//    @GetMapping("/get/product")
//    public Cart addCart() {
//        return cartService.getOrCreateCart("Samvelyazhyan@gmail.com");
//    }

//
//    @ModelAttribute("cart")
//    public Cart cart(Principal principal) {
//        return cartService.getOrCreateCart(principal.getName());
//    }

    @GetMapping("/my-cart")
    public String getMyCart(Model model, Principal principal){
        model.addAttribute("myCart",cartService.getOrCreateCart(principal.getName()));
        return "user/my_cart";
    }

    @GetMapping("/del-item/item")
    public String delItemFromMyCart(@RequestParam("id")Long id,  Model model, HttpServletRequest request, Principal principal){
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        model.addAttribute("myCart",cartService.removeItemById(cart,id));
        return "user/my_cart";
    }

    @GetMapping("/clear-cart")
    public String clearMyCart(Model model, HttpServletRequest request, Principal principal){
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        cartService.clearCart(cart);
        return "redirect:/cart/my-cart";
    }

    @GetMapping("/item/{id}")
    public String getItem(@PathVariable("id")Long id, Model model, HttpServletRequest request, Principal principal){
        Product product = productService.findById(id).orElseThrow(() -> new NullPointerException("No product found with id: " + id));
        model.addAttribute("product",product);
        return "product/product-details";
    }

    /**
     * Adding via AJAX
     *
     * @return updated cart
     */
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Cart updateCartByAjax(
            Principal principal,
            HttpServletRequest request,
            @RequestBody CarItemDTO carItemDTO,
            @ModelAttribute(value = "cart") Cart cartDto
    ) {
        String userEmail =  principal.getName();
        Cart cart = cartService.getOrCreateCart(userEmail);
        cartService.addItemToCart(cart,carItemDTO.getId(),carItemDTO.getProductId(),carItemDTO.getQuantity());

        request.getSession().setAttribute("cart",cartService.getOrCreateCart(userEmail));
        return cart;
    }

}
