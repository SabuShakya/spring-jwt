package com.sabu.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {

    private Long userId;

    private String userName;

    private String fullName;

    private String token;
}
