package io.amit.library.repository;

import io.amit.library.LibDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Component
public class LibraryRepository {
    // lib_id, lib_dto
    private Map<Integer, LibDto> libDb = new HashMap<>();

    public void addBooks() {
        System.out.println("adding books...");
        IntStream.range(1, 51)
                .forEach(num -> {
                    LibDto book = getBook(num);
                    libDb.put(num, book);
                });
        System.out.println("books added successfully "+libDb.size());
    }

    public Map<Integer, LibDto> getAllBooks() {
        return libDb;
    }

    private LibDto getBook(int num) {
        String bookType = "";
        if(num % 2 ==0) {
            bookType = "history";
        } else {
            bookType = "present";
        }

        return LibDto.newBuilder()
                .setBookId(num)
                .setBookName("book : "+num)
                .setBookType(bookType).build();
    }

}
