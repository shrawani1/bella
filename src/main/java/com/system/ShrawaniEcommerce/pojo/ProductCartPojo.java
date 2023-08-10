package com.system.ShrawaniEcommerce.pojo;

import com.system.ShrawaniEcommerce.entity.ProductCart;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCartPojo {
    private Integer id;

    private Integer user_id;
    private Integer products_id;

    public ProductCartPojo(ProductCart productCart) {
        this.id = productCart.getId();
        this.user_id = productCart.getUser().getId();
        this.products_id = productCart.getProducts().getId();
    }
}
