package com.emreilgar.productservice.service.grpc;

import com.emreilgar.grpc.DiscountRequest;
import com.emreilgar.grpc.DiscountResponse;

public interface DiscountGrpcService {
    //DiscountService ile alakalı logicler olacak, başka service olsaydı başka service adı olurdu.

    DiscountResponse getDiscountResponse(DiscountRequest discountRequest);
}
