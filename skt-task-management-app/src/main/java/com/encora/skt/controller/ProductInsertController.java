package com.encora.skt.controller;

import com.encora.skt.entity.Product;
import com.encora.skt.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import static com.encora.skt.constant.RequestMapping.PRODUCT_INSERT;

@RequiredArgsConstructor
@Controller
@RequestMapping(PRODUCT_INSERT)
public class ProductInsertController {

    static final String ATTRIBUTE_NAME_PRODUCT = "product";

    static final String ATTRIBUTE_NAME_RESULT = "result";

    static final String VIEW_NAME = "product-insert";

    private final ProductService productService;

    @GetMapping
    public String get(Model model) {
        if (!model.containsAttribute(ATTRIBUTE_NAME_PRODUCT)) {
            model.addAttribute(ATTRIBUTE_NAME_PRODUCT, new Product());
        }
        return VIEW_NAME;
    }

    @PostMapping
    public RedirectView post(
        RedirectAttributes redirectAttributes,
        @ModelAttribute(ATTRIBUTE_NAME_PRODUCT) Product product
    ) {
        redirectAttributes.addFlashAttribute(ATTRIBUTE_NAME_PRODUCT, product);
        redirectAttributes.addFlashAttribute(ATTRIBUTE_NAME_RESULT, productService.insert(product));
        return new RedirectView(PRODUCT_INSERT);
    }

}
