package com.example.demo.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FetchUsers {
    private final int id;
    private final String name;
    private final String email;
}
