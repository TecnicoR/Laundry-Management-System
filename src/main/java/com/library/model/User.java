package com.library.model;


import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
}
