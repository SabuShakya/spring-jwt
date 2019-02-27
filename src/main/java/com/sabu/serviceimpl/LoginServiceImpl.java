package com.sabu.serviceimpl;

import com.sabu.dtos.LoginRequestDTO;
import com.sabu.dtos.LoginResponseDTO;
import com.sabu.dtos.UserInfoDTO;
import com.sabu.entity.UserInfo;
import com.sabu.exceptionhandler.IncorrectUsernameException;
import com.sabu.service.LoginService;
import com.sabu.utility.JWTUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class LoginServiceImpl implements LoginService {


    public List<UserInfo> getUserInfos() {
        List<UserInfo> userInfos = new ArrayList<>();

        userInfos.add(new UserInfo(1L,
                "sabu",
                "Sabu Shakya",
                "sabushakya1@gmail.com",
                "sabu"));
        userInfos.add(new UserInfo(2L,
                "kusum",
                "Kusum Shakya",
                "kusumshakya1@gmail.com",
                "kusum"));
        return userInfos;
    }

    @Override
    public LoginResponseDTO loginUser(final LoginRequestDTO loginRequestDTO) {

        List<UserInfo> userInfos = getUserInfos();

        List<UserInfo> userInfoFound = userInfos.stream()
                .filter(userInfo -> loginRequestDTO.getUserName().equals(userInfo.getUserName()))
                .collect(Collectors.toList());

        checkIfUserFound.accept(userInfoFound);

        return convertToLoginResponseDTO(userInfoFound.get(0));
    }

    private LoginResponseDTO convertToLoginResponseDTO(UserInfo userInfoFound) {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

        loginResponseDTO.setUserName(userInfoFound.getUserName());
        loginResponseDTO.setFullName(userInfoFound.getFullName());
        loginResponseDTO.setUserId(userInfoFound.getId());
        loginResponseDTO.setToken(JWTUtils.createJWT(userInfoFound));

        return loginResponseDTO;
    }

    @Override
    public List<UserInfoDTO> getListOfUsers() {

        List<UserInfo> userInfos = getUserInfos();

        List<UserInfoDTO> userInfoDTOS =new ArrayList<>();

        userInfoDTOS = userInfos.stream()
                .map(convertUserInfoToUserInfoDTO)
                .collect(Collectors.toList());

        return userInfoDTOS;
    }

    private Consumer<List<UserInfo>> checkIfUserFound = (userInfoFound) -> {
        if (userInfoFound.size() < 0) {
            throw new IncorrectUsernameException("No user found.", "No such user");

        }
    };

    private Function<UserInfo, UserInfoDTO> convertUserInfoToUserInfoDTO = userInfo -> {
        UserInfoDTO userInfoDTO = new UserInfoDTO();

        userInfoDTO.setId(userInfo.getId());
        userInfoDTO.setUserName(userInfo.getUserName());
        userInfoDTO.setFullName(userInfo.getFullName());
        userInfoDTO.setEmail(userInfo.getEmailId());

        return userInfoDTO;
    };


}
