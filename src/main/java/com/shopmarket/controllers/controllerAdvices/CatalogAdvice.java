package com.shopmarket.controllers.controllerAdvices;

import com.shopmarket.models.catalog.GlobalType;
import com.shopmarket.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class CatalogAdvice {
    @Autowired
    private CatalogService catalogService;

    @ModelAttribute
    public void dropDownMenu(Model model) {
        List<GlobalType> catalogList = catalogService.findAll();
        model.addAttribute("catalog", catalogList);

    }

//    @ModelAttribute
//    public void handleRequest(HttpServletRequest request, Model model) {
//        String requestURI = request.getRequestURI();
//
//        List<GlobalType> all = catalogService.findAll();
//        model.addAttribute("catalog", "adddddddddaaaaaa");
//
////        //counter increment for each access to a particular uri
////        counterMap.computeIfAbsent(requestURI, key -> new LongAdder())
////                .increment();
////        //populating counter in the model
////        model.addAttribute("counter", counterMap.get(requestURI).sum());
////        //populating request URI in the model
////        model.addAttribute("uri", requestURI);
//    }
//

}
