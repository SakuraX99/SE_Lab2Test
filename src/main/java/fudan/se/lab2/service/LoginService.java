package fudan.se.lab2.service;

import fudan.se.lab2.repository.UserRepository;
import fudan.se.lab2.security.jwt.JwtConfigProperties;
import fudan.se.lab2.security.jwt.JwtTokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoginService {
    private String Username;
    private String Password;
    private JwtTokenUtil jwtutil;
    private SampleManager am;
    private UserRepository userRepository;

    public LoginService(String username, String password, JwtTokenUtil jwtutil,SampleManager sample,UserRepository usrRepo) {
        Username = username;
        Password = password;
        this.jwtutil = jwtutil;
        am = sample;
        userRepository = usrRepo;
    }

    public String login(){
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(Username, Password);
            Authentication result = am.authenticate(request);//校验
            SecurityContextHolder.getContext().setAuthentication(result);
        } catch(AuthenticationException e) {
            return ("Authentication failed: " + e.getMessage());
        }
        return "Successfully authenticated. Security context contains: " + SecurityContextHolder.getContext().getAuthentication() + "\n token : "+jwtutil.generateToken(userRepository.findByUsername(Username));//加入了token

    }
}
