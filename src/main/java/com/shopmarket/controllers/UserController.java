package com.shopmarket.controllers;

import com.shopmarket.exceptions.UserAlreadyExistException;
import com.shopmarket.models.User;
import com.shopmarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
//@Validated
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Qualifier("messageSource")
    @Autowired
    private MessageSource messages;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userForm", new User());
        return "/user/registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("userForm") @Valid final User userForm,
                                      Model model, final HttpServletRequest request) {

        System.out.println(userForm.getEmail()+"; " + userForm.getPassword());

        try {
            final User registeredUser = userService.save(userForm);
            System.out.println(registeredUser.getEmail()+"; " + registeredUser.getPassword());

//        final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
//        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));

        } catch (final UserAlreadyExistException uaeEx) {
            model.addAttribute("user", userForm);
            String errMessage = messages.getMessage("message.regError", null, request.getLocale());
            model.addAttribute("message", errMessage);
            return "/user/registration";
        } catch (final RuntimeException ex) {
            model.addAttribute("user", userForm);
            return "emailError";
        }

        return "successRegister";
    }

//    @PostMapping("/registration")
//    public String registerUserAccount(@ModelAttribute("userForm") @Valid final User userForm, BindingResult bindingResult,
//                                      Model model, final HttpServletRequest request) {
//
//        System.out.println(userForm.getEmail() + "; " + userForm.getPassword());
//
//        if (bindingResult.hasErrors()) {
//            return "emailError";
//        }
//
//        final User registeredUser = userService.save(userForm);
//
//        System.out.println(registeredUser.getEmail() + "; " + registeredUser.getPassword());
//
//
//        return "successRegister";
//    }


}
