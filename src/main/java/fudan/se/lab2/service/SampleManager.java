package fudan.se.lab2.service;

import fudan.se.lab2.domain.User;
import fudan.se.lab2.exception.UserNamedidntExistException;
import fudan.se.lab2.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public class SampleManager implements AuthenticationManager {
    static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
    private UserRepository userRepository;
    static {
        AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        User target = userRepository.findByUsername(auth.getName());
        if (target==null){
            throw new UserNamedidntExistException();
        }
        if (BCrypt.checkpw((String)auth.getCredentials(),target.getPassword())) {
            return new UsernamePasswordAuthenticationToken(auth.getName(),
                    auth.getCredentials(), AUTHORITIES);
        }
        throw new BadCredentialsException("Bad Credentials");
    }

    public void setUserRepository(UserRepository Repository){
        userRepository = Repository;
    }
}