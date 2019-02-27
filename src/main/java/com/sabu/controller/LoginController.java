package com.sabu.controller;


import com.sabu.dtos.LoginRequestDTO;
import com.sabu.dtos.LoginResponseDTO;
import com.sabu.dtos.UserInfoDTO;
import com.sabu.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(
            @RequestBody LoginRequestDTO loginRequestDTO) {
        return new ResponseEntity<LoginResponseDTO>(loginService.loginUser(loginRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserInfoDTO>> getAllUsers() {
        return new ResponseEntity<List<UserInfoDTO>>(loginService.getListOfUsers(), HttpStatus.OK);
    }
}
