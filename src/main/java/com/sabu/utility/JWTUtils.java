package com.sabu.utility;

import com.sabu.dtos.LoginResponseDTO;
import com.sabu.entity.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JWTUtils {

    final static String SECRET_KEY = "SecretKey";


    public static String createJWT(UserInfo userInfo) {
        return Jwts.builder()
                .setSubject(userInfo.getUserName())
                .claim("roles", userInfo.getId())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

    }

    public static boolean validateJWTToken(HttpServletRequest httpServletRequest, String header) {

        final String jwt = header.substring(7);

        final Claims claims = Jwts
                .parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwt).getBody();
        httpServletRequest.setAttribute("claims", claims);

        return true;
    }

}

