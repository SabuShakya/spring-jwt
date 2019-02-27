package com.sabu.service;

import com.sabu.dtos.LoginRequestDTO;
import com.sabu.dtos.LoginResponseDTO;
import com.sabu.dtos.UserInfoDTO;
import com.sabu.entity.UserInfo;

import java.util.List;

public interface LoginService {

    LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO);

    List<UserInfoDTO> getListOfUsers();
}
