package com.spring.smbackend.security;

import com.spring.smbackend.entities.AppUser;
import com.spring.smbackend.entities.TokenInfo;
import com.spring.smbackend.services.TokenInfoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

@Service
@Log4j2
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final HttpServletRequest httpRequest;
    private final TokenInfoService tokenInfoService;

    public AuthService(AuthenticationManager authenticationManager, HttpServletRequest httpRequest, TokenInfoService tokenInfoService) {
        this.authenticationManager = authenticationManager;
        this.httpRequest = httpRequest;
        this.tokenInfoService = tokenInfoService;
    }

    public JwtResponseDto login(String login, String password) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password)
        );
        log.debug("Valid userDetails credentials");
        AppUserDetail userDetails = (AppUserDetail) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        /*UserDetails userDetails = userService.loadUserByUsername(login);*/
        TokenInfo tokenInfo = createLoginToken(login, userDetails.getId());
        return JwtResponseDto.builder()
                .accessToken(tokenInfo.getAccessToken())
                .refreshToken(tokenInfo.getRefreshToken())
                .build();
    }

    public TokenInfo createLoginToken(String username, Long userId) {
        String userAgent = httpRequest.getHeader(HttpHeaders.USER_AGENT);
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String accessTokenId = UUID.randomUUID().toString();
        String accessToken = JwtTokenUtils.generateToken(username, accessTokenId, false);
        log.info("Access token created. [tokenId={}]", accessTokenId);

        String refreshTokenId = UUID.randomUUID().toString();
        String refreshToken = JwtTokenUtils.generateToken(username, refreshTokenId, true);
        log.info("Refresh token created. [tokenId={}]", refreshTokenId);

        TokenInfo tokenInfo = new TokenInfo(accessToken, refreshToken);
        tokenInfo.setUser(new AppUser(userId));
        tokenInfo.setUserAgentText(userAgent);
        tokenInfo.setLocalIpAddress(ip.getHostAddress());
        tokenInfo.setRemoteIpAddress(httpRequest.getRemoteUser());
        return tokenInfoService.save(tokenInfo);
    }
}
