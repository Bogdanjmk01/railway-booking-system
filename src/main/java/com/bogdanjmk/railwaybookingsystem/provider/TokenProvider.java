package com.bogdanjmk.railwaybookingsystem.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.bogdanjmk.railwaybookingsystem.security.RailwayUserDetails;
import com.bogdanjmk.railwaybookingsystem.security.customers.CustomerDetails;
import com.bogdanjmk.railwaybookingsystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.currentTimeMillis;
import static java.util.Arrays.stream;

@Component
@RequiredArgsConstructor
public class TokenProvider {
    private final UserService userService;
    private static final String RAILWAY_SYSTEM = "Railway-Booking-System";
    private static final String RAILWAY_MANAGEMENT_SYSTEM = "RAILWAY_MANAGEMENT_SYSTEM";
    private static final String AUTHORITIES = "authorities";
    private static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";

    @Value("${jwt.access_token_expiration_time}")
    private Long accessTokenExpirationTime;

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String createAccessToken(RailwayUserDetails railwayUserDetails) {
        return JWT.create().withIssuer(RAILWAY_SYSTEM).withAudience(RAILWAY_MANAGEMENT_SYSTEM)
                .withIssuedAt(new Date())
                .withSubject(railwayUserDetails.getUsername()).withArrayClaim(AUTHORITIES, getClaimsFromUser(railwayUserDetails))
                .withExpiresAt(new Date(currentTimeMillis() + accessTokenExpirationTime))
                .sign(Algorithm.HMAC512(jwtSecret.getBytes()));
    }

    public String createAccessToken(CustomerDetails customerDetails) {
        return JWT.create().withIssuer(RAILWAY_SYSTEM).withAudience(RAILWAY_MANAGEMENT_SYSTEM)
                .withIssuedAt(new Date())
                .withSubject(customerDetails.getUsername()).withArrayClaim(AUTHORITIES, getClaimsFromUser(customerDetails))
                .withExpiresAt(new Date(currentTimeMillis() + accessTokenExpirationTime))
                .sign(Algorithm.HMAC512(jwtSecret.getBytes()));
    }

    public List<GrantedAuthority> getAuthorities(String token) {
        String[] claims = getClaimsFromToken(token);
        return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public Authentication getAuthentication(String email, List<GrantedAuthority> authorities, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userService.getUserByEmail(email), null, authorities);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        return usernamePasswordAuthenticationToken;
    }

    public boolean isTokenValid(String email, String token) {
        JWTVerifier verifier = getJWTVerifier();
        return StringUtils.isNotEmpty(email) && !isTokenExpired(verifier, token);
    }

    public String getSubject(String token, HttpServletRequest request) {
        try {
            return getJWTVerifier().verify(token).getSubject();
        } catch (TokenExpiredException exception) {
            request.setAttribute("expiredMessage", exception.getMessage());
            throw exception;
        } catch (InvalidClaimException exception) {
            request.setAttribute("invalidClaim", exception.getMessage());
            throw exception;
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public Date getExpirationDate(String token) {
        try {
            System.out.println(getJWTVerifier().verify(token).getExpiresAt());
            return getJWTVerifier().verify(token).getExpiresAt();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
        }
    }

    private boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expiration = verifier.verify(token).getExpiresAt();

        return expiration.before(new Date());
    }

    private String[] getClaimsFromUser(RailwayUserDetails gymUserDetailsService) {
        return gymUserDetailsService.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new);
    }

    private String[] getClaimsFromUser(CustomerDetails customerDetails) {
        return customerDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new);
    }

    private String[] getClaimsFromToken(String token) {
        JWTVerifier verifier = getJWTVerifier();

        return verifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
    }

    private JWTVerifier getJWTVerifier() {
        JWTVerifier verifier;

        try {
            Algorithm algorithm = Algorithm.HMAC512(jwtSecret);
            verifier = JWT.require(algorithm).withIssuer(RAILWAY_SYSTEM).build();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
        }

        return verifier;
    }
}
