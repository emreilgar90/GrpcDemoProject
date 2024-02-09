package com.emreilgar.productservice.service.grpc;

import com.emreilgar.grpc.DiscountRequest;
import com.emreilgar.grpc.DiscountResponse;
import com.emreilgar.grpc.DiscountServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DiscountGrpcServiceImpl implements DiscountGrpcService{

    private DiscountServiceGrpc.DiscountServiceBlockingStub discountServiceBlockingStub;
    private ManagedChannel channel; //channel oluşturuyoruz. Karşı service'in host'u ve post'u
    public DiscountGrpcServiceImpl(@Value("${discount.grpc.host}") String grpcHost, @Value("${discount.grpc.port}") int grpcPort){
        System.out.println("----> Discount grpc: " + grpcHost+ " " + grpcPort);
        channel = ManagedChannelBuilder.forAddress(grpcHost,grpcPort)
                .usePlaintext()
                .build();
    }
    //14/12:47
    @Override
    public DiscountResponse getDiscountResponse(DiscountRequest discountRequest) {
        discountServiceBlockingStub = DiscountServiceGrpc.newBlockingStub(channel);
        DiscountResponse discountResponse = discountServiceBlockingStub.getDiscount(discountRequest);
        return discountResponse;
    }

}
