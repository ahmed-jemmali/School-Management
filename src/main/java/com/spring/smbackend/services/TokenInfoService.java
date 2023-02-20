package com.spring.smbackend.services;

import com.spring.smbackend.entities.TokenInfo;

public interface TokenInfoService {

    TokenInfo findById(Long id);

    TokenInfo findByRefreshToken(String refreshToken);

    TokenInfo save(TokenInfo tokenInfo);

}
