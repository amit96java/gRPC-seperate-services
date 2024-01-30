package io.amit.user.repository;

import io.amit.user.UserDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Component
public class UserRepository {

    private Map<Integer, UserDto> userDB= new HashMap<>();

    public void addUsers() {
        System.out.println("adding user...");
        IntStream.range(1, 51)
                .forEach(num -> {
                    UserDto user = getUser(num);
                    userDB.put(num, user);
                });
        System.out.println("users added successfully "+userDB.size());
    }

    public UserDto getUser(int id) {
        String bookType = "history";
        if(id % 2 != 0) {
                bookType = "present";
        }
      return   UserDto.newBuilder()
                .setUserId(id)
                .setUserName("user : "+id)
                .setBookType(bookType).build();
    }

    public String getBookType(Integer id) {
      return   userDB.get(id).getBookType();
    }

}
