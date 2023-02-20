package com.spring.smbackend.services.impl;

import com.spring.smbackend.entities.TokenInfo;
import com.spring.smbackend.exceptions.ResourceNotFoundException;
import com.spring.smbackend.repositories.TokenInfoRepository;
import com.spring.smbackend.services.TokenInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenInfoServiceImpl implements TokenInfoService {

    private final TokenInfoRepository tokenInfoRepository;

    @Override
    public TokenInfo findById(Long id) {
        return tokenInfoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Token info does not exist with id: " + id));
    }

    @Override
    public TokenInfo findByRefreshToken(String refreshToken) {
        return tokenInfoRepository.findByRefreshToken(refreshToken);
    }

    @Override
    public TokenInfo save(TokenInfo tokenInfo) {
        return tokenInfoRepository.save(tokenInfo);
    }
}
