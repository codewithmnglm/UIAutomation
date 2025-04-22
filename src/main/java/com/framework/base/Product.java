package com.framework.base;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    String addToCartXpath;
    String addToWishlistXpath;
    String productName;
    String productId;
    Double productPrice;

}

