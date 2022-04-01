package com.shopmarket.controllers;

import com.shopmarket.exceptions.UserAlreadyExistException;
import com.shopmarket.models.Address;
import com.shopmarket.models.Cart;
import com.shopmarket.models.User;
import com.shopmarket.services.AddressService;
import com.shopmarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;


    @Qualifier("messageSource")
    @Autowired
    private MessageSource messages;


    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        User user = new User();
        Address address = new Address();
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        user.setAddresses(addresses);
        user.setCart(new Cart());

        model.addAttribute("userForm", user);
        return "user/registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("userForm") @Valid final User userForm,
                                      BindingResult bindingResult,
                                      Model model, final HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "user/registration";
        }
        try {
            final User registeredUser = userService.save(userForm);
            final Address address = userForm.getAddresses().get(0);
            address.setUser(registeredUser);
            addressService.save(address);

//        final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
//            System.out.println(appUrl);
//        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));

        } catch (final UserAlreadyExistException uaeEx) {
            System.err.println(uaeEx.getMessage());
            model.addAttribute("user", userForm);
            String errMessage = messages.getMessage("message.regError", null, request.getLocale());
            model.addAttribute("message", errMessage);
            return "/user/registration";
        } catch (final RuntimeException ex) {
            model.addAttribute("user", userForm);
            return "emailError";
        }

        return "/user/successRegister";
    }



}
