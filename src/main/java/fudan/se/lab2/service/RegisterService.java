package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.RegisterRequest;
import fudan.se.lab2.domain.Authority;
import fudan.se.lab2.domain.User;
import fudan.se.lab2.exception.UsernameHasBeenRegisteredException;
import fudan.se.lab2.repository.AuthorityRepository;
import fudan.se.lab2.repository.UserRepository;
import org.aspectj.apache.bcel.Repository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import sun.jvm.hotspot.asm.Register;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class RegisterService {

    private RegisterRequest request;
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private AtomicLong counterforau;

    public RegisterService(RegisterRequest req, UserRepository usrRepo,AuthorityRepository auRepo,AtomicLong counter) {
        this.request = req;
        this.userRepository = usrRepo;
        this.authorityRepository = auRepo;
        this.counterforau = counter;
    }

    public User Register() {
        String Usrname = request.getUsername();
        if(Usrname.equals("ASK1")){
            int x = 0;
        }

        if (userRepository.findByUsername(Usrname) != null) {//用户名已经被注册
            throw new UsernameHasBeenRegisteredException(Usrname);
        } else {
            Set<String> authorities = request.getAuthorities();
            Set<Authority> Autorities = new HashSet<>();
            for (String x : authorities
            ) {
                long id = counterforau.incrementAndGet();
                Authority authority = new Authority();
                authority.setAuthority(x);
                authority.setId(id);
                Autorities.add(authority);
                authorityRepository.save(authority);
            }//字符串集合对象转化为Authority集合对象
            User usr = new User(Usrname, BCrypt.hashpw(request.getPassword(),BCrypt.gensalt()), request.getEmail(),request.getInstitude(),request.getRegion(), Autorities);//该user构造完成
            usr.setId(counterforau.incrementAndGet());
            userRepository.save(usr);//添加用户
            return usr;//返回User对象
        }
    }
}
