package io.amit.user.service;

import io.amit.library.LibDto;
import io.amit.library.LibRequestByBookType;
import io.amit.library.LibResponse;
import io.amit.library.LibServiceGrpc;
import io.amit.user.*;
import io.amit.user.repository.UserRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private UserRepository userRepository;

    //this library service client coming from properties file.
    @GrpcClient(value = "library-service")
    private LibServiceGrpc.LibServiceBlockingStub libServiceBlockingStub;



    public List<LibDto> getBooksByUserId(Integer userId) {
        //get book type by user id
        String bookType = userRepository.getBookType(userId);
        //get books by book type
        LibRequestByBookType request = LibRequestByBookType
                .newBuilder().setBookType(bookType)
                .build();
        LibResponse libResponse = libServiceBlockingStub.getBooksByBookType(request);
        return
                libResponse.getLibsList();
    }

    @Override
    public void getUserByUserId(UserRequestById request, StreamObserver<UserResponse> responseObserver) {
//        int userId = request.getUserId();

    }

    @Override
    public void getUserByBookType(UserRequestByBookType request, StreamObserver<UserResponse> responseObserver) {
//        String bookType = request.getBookType();
//        // get books by book type from library service.
//        LibRequestByBookType libRequest = LibRequestByBookType
//                .newBuilder().setBookType(bookType).build();
//
//        LibResponse libResponse = libServiceBlockingStub.getBooksByBookType(libRequest);
//
//        responseObserver.onNext(libResponse);
//        responseObserver.onCompleted();

    }
}
