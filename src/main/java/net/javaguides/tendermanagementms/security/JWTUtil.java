package net.javaguides.tendermanagementms.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.javaguides.tendermanagementms.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtil implements Serializable {

    private static final long serialVersionUID = 654352132132L;

    public static final long JWT_TOKEN_VALIDITY = 500*60*60;

    private final String secretKey= "randomkey123";

    //    Gets the Username (email) of the user from token

    public String getUsernameFromToken(String token) {
        return getClaimFromToken (token, Claims::getSubject);
    }

    //    Retrieves the expiry of the token


    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken (token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function <Claims, T> claimsResolver) {
       final Claims claims =  getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //    Secret key will be required for retrieving data from token

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    //  Check if the token has expired

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    UserService userService;

    //generate token for user

    public String generateToken(UserDetails userDetails) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("roles",userDetails.getAuthorities());
        return doGenerateToken(claims,userDetails.getUsername());
    }

    //        Generate the token from the claims and required details

        private String doGenerateToken (Map<String, Object> claims, String subject) {

           return Jwts.builder()
                   .setClaims(claims)
                   .setSubject(subject)
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis()+ JWT_TOKEN_VALIDITY))
                   .signWith(SignatureAlgorithm.HS256,secretKey)
                   .compact();
        }

    //            Check if the provided JWT token is valid or not

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);


    }


}
