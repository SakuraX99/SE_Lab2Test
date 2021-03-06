package fudan.se.lab2.service;

import fudan.se.lab2.domain.User;
import fudan.se.lab2.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author LBW
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: Implement the function.
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User: '" + username + "' not found.");
        }
        else {
            return new JwtUser(user.getId(),user.getUsername(),user.getPassword(),user.getAuthorities(),user.isEnabled());
        }
    }
}
