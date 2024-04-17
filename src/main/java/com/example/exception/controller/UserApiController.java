package com.example.exception.controller;

import com.example.exception.model.Api;
import com.example.exception.model.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UserApiController {

    private static List<UserResponse> list = List.of(
            UserResponse.builder()
                    .id("1")
                    .age(10)
                    .name("Hong")
                    .build(),
            UserResponse.builder()
                    .id("2")
                    .age(20)
                    .name("Kim")
                    .build()
    );

    @GetMapping(path = "/id/{userId}")
    public Api<UserResponse> getUser(@PathVariable(name = "userId") String id) {

//        if (true) {
//            throw new RuntimeException("message");
//        }

        var user = list.stream()
                .filter(
                        it -> it.getId().equals(id)
                )
                .findFirst()
                .get();

        Api<UserResponse> userResponse = Api.<UserResponse>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.name())
                .data(user)
                .build();

        return userResponse;
    }
}
