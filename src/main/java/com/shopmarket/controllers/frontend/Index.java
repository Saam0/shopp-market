package com.shopmarket.controllers.frontend;

import com.shopmarket.models.catalog.GlobalType;
import com.shopmarket.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class Index {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/index")
    public String ind(Model model){
//        List<GlobalType> catalogList = catalogService.findAll();
//        model.addAttribute("catalog", catalogList);
        return "index";
    }

}
