package com.emreilgar.productservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DiscountResponseDto {

    private float newPrice;
    private float oldPrice;
    private String code;

}
