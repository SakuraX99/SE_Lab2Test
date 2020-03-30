package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.HoldmeetingRequest;
import fudan.se.lab2.domain.Authority;
import fudan.se.lab2.domain.Meeting;
import fudan.se.lab2.exception.UsernameHasBeenRegisteredException;
import fudan.se.lab2.repository.MeetingRepository;
import fudan.se.lab2.security.jwt.JwtConfigProperties;
import fudan.se.lab2.security.jwt.JwtRequestFilter;
import fudan.se.lab2.security.jwt.JwtTokenUtil;
import fudan.se.lab2.domain.User;
import fudan.se.lab2.repository.AuthorityRepository;
import fudan.se.lab2.repository.UserRepository;
import fudan.se.lab2.controller.request.RegisterRequest;
import fudan.se.lab2.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

/**
 * @author LBW
 * 目前假设用户名，密码等的合法性认证已经被前端完成，service默认收到的是合法的相关字串
 */
@Service
public class AuthService {
    private JwtConfigProperties properties = new JwtConfigProperties();
    private JwtTokenUtil jwtutil = new JwtTokenUtil(properties);
    private final AtomicLong counterforau = new AtomicLong();
    private SampleManager am = new SampleManager();
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private MeetingRepository meetingRepository;

    @Autowired
    public AuthService(UserRepository userRepository, AuthorityRepository authorityRepository,MeetingRepository meetingRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.meetingRepository = meetingRepository;

        am.setUserRepository(userRepository);
    }

    public User register(RegisterRequest request) {
        RegisterService registerService  =new RegisterService(request,userRepository,authorityRepository,counterforau);
        return registerService.Register();
        // TODO: Implement the function.
    }


    public String login(String username, String password) {
        LoginService loginService = new LoginService(username,password,jwtutil,am,userRepository);
        return loginService.login();
             // TODO: Implement the function.
    }

    public Meeting holdmeeting(HoldmeetingRequest request){
        HoldmeetingService holdmeetingService = new HoldmeetingService(request,userRepository,authorityRepository,meetingRepository);
        return holdmeetingService.holdMeeting();
    }



}
