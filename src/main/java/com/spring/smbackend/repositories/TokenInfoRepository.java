package com.spring.smbackend.repositories;

import com.spring.smbackend.entities.TokenInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface TokenInfoRepository extends JpaRepository<TokenInfo, Long> {
    TokenInfo findByRefreshToken (String refreshToken);
}
