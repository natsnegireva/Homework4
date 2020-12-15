package ru.geekbrains.boot.Homework4.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.boot.Homework4.services.ProductService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showAll(Model model,
                          @RequestParam(required = false, name = "min_price") Integer minPrice,
                          @RequestParam(required = false, name = "max_price") Integer maxPrice
    ) {
        model.addAttribute("products", productService.getProduct (minPrice, maxPrice));
        return "products";
    }


    @GetMapping("/remove/{id}")
    public String removeById (@PathVariable Long id, HttpServletResponse response) {
        productService.removeById (id);
        return "redirect:/products";
    }
//
//    @GetMapping("/count/")
//    public String calculatePrices (Model model) {
//        model.addAttribute("products", productService.calculatePrices());
//        return "redirect:/products";
//    }

}
