package com.emreilgar.discountservice.service;

import com.emreilgar.discountservice.model.Category;
import com.emreilgar.discountservice.model.Discount;
import com.emreilgar.discountservice.repository.ICategoryRepository;
import com.emreilgar.discountservice.repository.IDiscountRepository;
import com.emreilgar.grpc.DiscountRequest;
import com.emreilgar.grpc.DiscountResponse;
import com.emreilgar.grpc.DiscountServiceGrpc;
import com.emreilgar.grpc.Response;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.math.BigDecimal;
import java.util.Optional;

@GrpcService
@RequiredArgsConstructor                     //protoda oluşturduğumu extends ettim.
public class DiscountGrpcServiceImpl extends DiscountServiceGrpc.DiscountServiceImplBase {
   //ayrıca service aç direkt repository injekte etme
    private final IDiscountRepository discountRepository;
    private final ICategoryRepository categoryRepository;

    @Override //17
    public void getDiscount(DiscountRequest request, StreamObserver<DiscountResponse> responseObserver) {
        Category category= categoryRepository.findByExternalId((int)request.getExternalCategoryId())
                .orElseThrow(()->new RuntimeException("Category has not been found by external category"));

       Optional<Discount> discount= discountRepository.findByCodeAndCategoryId(request.getCode(),category.getId());
       if (discount.isPresent()){
           BigDecimal newPrice= discount.get().getDiscountPrice().subtract(BigDecimal.valueOf(request.getPrice())).multiply(BigDecimal.valueOf(-1));
           responseObserver.onNext(
                   DiscountResponse.newBuilder()
                           .setCode(discount.get().getCode())
                           .setOldPrice(request.getPrice())
                           .setNewPrice(newPrice.floatValue())
                           .setResponse(Response.newBuilder().setStatusCode(true).setMessage("Discount has been applied succesfully").build())
                           .build()
           );

       }else {
           responseObserver.onNext(DiscountResponse.newBuilder()
                           .setOldPrice(request.getPrice())
                           .setResponse(Response.newBuilder().setMessage("Code and category are in valid").setStatusCode(false)
                                   .build()).build()
                           );

       }
        responseObserver.onCompleted();  //çağrıyı tamamlamak
        super.getDiscount(request, responseObserver);
    }
}
