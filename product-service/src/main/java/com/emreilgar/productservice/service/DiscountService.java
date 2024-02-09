package com.emreilgar.productservice.service;

import com.emreilgar.grpc.DiscountRequest;
import com.emreilgar.grpc.DiscountResponse;
import com.emreilgar.productservice.model.Product;
import com.emreilgar.productservice.service.grpc.DiscountGrpcService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountService {
    private final DiscountGrpcService discountGrpcService;
    private final ProductService productService;

    public DiscountResponse getDiscount(int productId, String code){
        Product product=productService.getById(productId);
        DiscountRequest discountRequest= DiscountRequest.newBuilder()
                .setCode(code)
                .setPrice(product.getPrice().floatValue())
                .setExternalCategoryId(product.getCategory().getId())
                .build();
        return discountGrpcService.getDiscountResponse(discountRequest);
    }
}
