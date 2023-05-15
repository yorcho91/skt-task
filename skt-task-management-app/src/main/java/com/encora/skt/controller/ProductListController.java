package com.encora.skt.controller;

import com.encora.skt.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import static com.encora.skt.constant.RequestMapping.PRODUCT_LIST;

@RequiredArgsConstructor
@Controller
@RequestMapping(PRODUCT_LIST)
public class ProductListController {

    static final String ATTRIBUTE_NAME_PRODUCTS = "products";

    static final String VIEW_NAME = "product-list";

    private final ProductService productService;

    @GetMapping
    public String get(Model model) {
        model.addAttribute(ATTRIBUTE_NAME_PRODUCTS, productService.list());
        return VIEW_NAME;
    }

}
