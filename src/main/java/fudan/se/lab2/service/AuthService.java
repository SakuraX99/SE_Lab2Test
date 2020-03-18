package fudan.se.lab2.service;

import fudan.se.lab2.domain.Authority;
import fudan.se.lab2.exception.UsernameHasBeenRegisteredException;
import fudan.se.lab2.security.jwt.JwtConfigProperties;
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

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

/**
 * @author LBW
 */
@Service
public class AuthService {
    private JwtConfigProperties properties = new JwtConfigProperties();
    private JwtTokenUtil jwtutil = new JwtTokenUtil(properties);
    private final AtomicLong counterforau = new AtomicLong();
    private static SampleManager am = new SampleManager();
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private final String patternForUsrname = "^[a-zA-Z0-9]{6,20}$";//用户名暂定规则： 6-20个个字符，由大写字母，小写字母和数字构成
    private final String patternForPasw = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$";//密码暂定规则 8-20个字符，至少一个大写字母，一个小写字母和一个数字且只有这三种字符
    private final String patternForFullname = "^([A-Z][a-z]*( |$)){2,4}$";//英文fullName 2-4个单词

    @Autowired
    public AuthService(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    public User register(RegisterRequest request) {
        String Usrname = request.getUsername();
        String Pasw = request.getPassword();
        String Fullname = request.getFullname();//提取request中的信息

        if (Pattern.matches(patternForUsrname, Usrname)) {//用户名是否符合注册的基本要求
            //符合基本要求时检测用户名是否已被注册
            if (userRepository.findByUsername(Usrname) != null) {//用户名已经被注册
                throw new UsernameHasBeenRegisteredException(Usrname);
            } else {//用户名检测通过进入注册流程
                if (Pattern.matches(patternForPasw, Pasw)) {//密码是否符合安全标准
                    if (Pattern.matches(patternForFullname, Fullname)) {//密码是否是一个fullname
                        //三项匹配均通过进行注册
                        Set<String> authorities = request.getAuthorities();
                        Set<Authority> Autorities = new HashSet<>();
                        for (String x : authorities
                        ) {
                            long id = counterforau.incrementAndGet();
                            Autorities.add(new Authority(x,id));
                            authorityRepository.save(new Authority(x,id));
                        }//字符串集合对象转化为Authority集合对象
                        User usr = new User(Usrname, BCrypt.hashpw(Pasw,BCrypt.gensalt()), Fullname, Autorities);//该user构造完成
                        userRepository.save(usr);//添加用户
                        return usr;//返回User对象
                    } else {
                        throw new FullNameillegalException(Fullname);
                    }
                } else {
                    throw new PasswordLowSecurityAlertException();
                }
            }
        } else {
            throw new IllegalUserNameException();
        }
        // TODO: Implement the function.
    }


    public String login(String username, String password) {
        if (!Pattern.matches(patternForUsrname, username)) {//用户名是否符合基本要求
            throw new IllegalUserNameException();
        }
        am.setUserRepository(userRepository);
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(username, password);
            Authentication result = am.authenticate(request);//校验
            SecurityContextHolder.getContext().setAuthentication(result);
        } catch(AuthenticationException e) {
            return ("Authentication failed: " + e.getMessage());
        }

        return "Successfully authenticated. Security context contains: " + SecurityContextHolder.getContext().getAuthentication() + "\n token : "+jwtutil.generateToken(userRepository.findByUsername(username));//加入了token
        // TODO: Implement the function.
    }

    public static void main(String[] args) {
        String pas = "snaks231CSMZK";
//        this.passwordEncoder.encode();
    }


}
