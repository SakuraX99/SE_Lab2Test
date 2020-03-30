package fudan.se.lab2.security.jwt;

import fudan.se.lab2.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * This is a util class to use JWT.
 * We give it to you for free. :)
 *
 * @author LBW
 */
@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3839549913040578986L;

    private JwtConfigProperties jwtConfigProperties;

    public JwtTokenUtil(JwtConfigProperties jwtConfigProperties) {
        this.jwtConfigProperties = jwtConfigProperties;
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().addClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfigProperties.getValidity()))
                .signWith(SignatureAlgorithm.HS512, jwtConfigProperties.getSecret()).compact();
    }

    public String getUsernameFromToken(String jwtToken) {
        return getClaimFromToken(jwtToken, Claims::getSubject);
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) {
        final String username = getUsernameFromToken(jwtToken);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }

    private Date getExpirationDateFromToken(String jwtToken) {
        return getClaimFromToken(jwtToken, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String jwtToken) {
        return Jwts.parser().setSigningKey(jwtConfigProperties.getSecret()).parseClaimsJws(jwtToken).getBody();
    }



    private boolean isTokenExpired(String jwtToken) {
        final Date expiration = getExpirationDateFromToken(jwtToken);
        return expiration.before(new Date());
    }
//
//    public static void main(String[] args) {
//        JwtConfigProperties d = new JwtConfigProperties();
//        d.setValidity(18000000);
//        String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBS0ciLCJpYXQiOjE1ODQ2MTc3NzMsImV4cCI6MTU4NDYxOTU3M30.FNE2w8pGBzqeC2HYEKPOOcZqrFVErYaJ8V1dW6D15eojCKym26Cah9K2iIe3AKuS3tSjCKDfihr9-eGZAQOY2g";
//        JwtTokenUtil f = new JwtTokenUtil(d);
//        Date x = f.getExpirationDateFromToken(jwt);
//        System.out.println(x);
//        Date y = new Date();
//        System.out.println(y);
//        System.out.println(x.before(y));
//        System.out.println(f.getUsernameFromToken(jwt));
//    }
//

}
