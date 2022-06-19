package com.shopmarket.controllers;

import com.shopmarket.models.Address;
import com.shopmarket.models.User;
import com.shopmarket.services.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CheckoutController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private Logger logger;

    @GetMapping("/checkout")
    public String getCheckout(Model model, Principal principal) {
        List<Address> addresses = userService.findUserByEmail(principal.getName()).get().getAddresses();
        model.addAttribute("address", addresses.get(addresses.size() - 1));
        return "user/checkout";
    }

    @PostMapping("/pay-process")
    public String getPeyProcess(@ModelAttribute("address") Address address,
                                @RequestParam("radio") String radio,
                                BindingResult bindingResult,
                                Principal principal,
                                Model model) {

//        if (bindingResult.hasErrors()) {
//            return "user/checkout";
//        }

        logger.info("address - " + radio);
        User user = userService.findUserByEmail(principal.getName()).get();
        if (!user.getAddresses().contains(address) && radio.equals("Enable") ) {

            logger.info("hash - " + address.hashCode());
            logger.info("hashUser- " + user.getAddresses().get(0).hashCode());
            address.setUser(user);
            user.getAddresses().add(0, address);
            userService.update(user);
        }
        model.addAttribute("address", user.getAddresses().get(0));
        return "user/pay_form";
//        return "redirect:/cart/checkout";
    }

    @PostMapping("/payout")
    public String payout(@RequestParam("bankCardNumber") String bankCardNumber,
                         Principal principal,
                         Model model){

        if (isTrueBankCardNumber(bankCardNumber)){
            String userEmail = principal.getName();
            orderService.saveOrder(userEmail);

            // TODO: 12.06.2022 avelacnel: ordery stanaluc heto maqrel Cart@
            return "user/payout";
        }
        return "user/bad-payout";
    }

    private boolean isTrueBankCardNumber(String bankCardNumber){
        String userBankCardNumber="1111000011110000";
        return userBankCardNumber.equals(bankCardNumber);
    }

}
