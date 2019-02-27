package com.sabu.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDTO {

    private Long id;

    private String userName;

    private String fullName;

    private String email;
}
