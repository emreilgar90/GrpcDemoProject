package com.emreilgar.productservice.controller;

import com.emreilgar.grpc.DiscountResponse;
import com.emreilgar.productservice.dto.DiscountResponseDto;
import com.emreilgar.productservice.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/discount")
public class DiscountController {

    private final DiscountService discountService;
    @GetMapping("/getdiscount")
    public ResponseEntity<DiscountResponseDto> getDiscount(@RequestParam("code") String code, @RequestParam("productId") int productId){
        DiscountResponse discountResponse = discountService.getDiscount(productId, code);
        return ResponseEntity.ok(DiscountResponseDto.builder()
                .newPrice(discountResponse.getNewPrice())
                .oldPrice(discountResponse.getOldPrice())
                .code(discountResponse.getCode()).build());
    }
}
