package io.amit.library.service;

import io.amit.library.LibDto;
import io.amit.library.LibRequestByBookType;
import io.amit.library.LibResponse;
import io.amit.library.LibServiceGrpc;
import io.amit.library.repository.LibraryRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class LibraryService extends LibServiceGrpc.LibServiceImplBase {


    @Autowired
    private LibraryRepository libraryRepository;

    private List<LibDto> getBooksByBookType(String bookType) {
        return libraryRepository.getAllBooks().entrySet()
                .stream()
                .filter(entry -> entry.getValue().getBookType().equalsIgnoreCase(bookType))
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public void getBooksByBookType(LibRequestByBookType request, StreamObserver<LibResponse> responseObserver) {
        System.out.println("request received for book type "+request.getBookType());
        String bookType = request.getBookType();
        List<LibDto> booksByBookType = getBooksByBookType(bookType);
        LibResponse libResponse = LibResponse.newBuilder()
                .addAllLibs(booksByBookType)
                .build();
        responseObserver.onNext(libResponse);
        responseObserver.onCompleted();
    }
}
