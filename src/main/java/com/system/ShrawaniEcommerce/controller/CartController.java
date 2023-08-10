package com.system.ShrawaniEcommerce.controller;
import com.system.ShrawaniEcommerce.entity.ProductCart;
import com.system.ShrawaniEcommerce.pojo.ProductCartPojo;
import com.system.ShrawaniEcommerce.service.ProductCartService;
import com.system.ShrawaniEcommerce.service.ProductsService;
import com.system.ShrawaniEcommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final ProductsService productsService;

    private final UserService userService;
    private final ProductCartService productCartService;

    @GetMapping()
    public String displayCart(Principal principal, Model model, ProductCartPojo productCartPojo){
        Integer id = userService.findByEmail(principal.getName()).getId();
        List<ProductCart> list = productCartService.fetchAll(id);
        model.addAttribute("cart", productCartPojo);
        model.addAttribute("cartItems", list);
        return "cart";
    }

    @PostMapping("/updateQuantity/{id}")
    public String updateQuantity(@Valid ProductCartPojo productCartPojo){
        ProductCart productCart = productCartService.fetchOne(productCartPojo.getId());
        productCartService.updateQuantity(productCart);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String deleteCartItem(@PathVariable("id") Integer id){
        productCartService.deleteFromCart(id);
        return "redirect:/cart";
    }
}