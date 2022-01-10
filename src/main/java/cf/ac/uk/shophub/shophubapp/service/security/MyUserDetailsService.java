package cf.ac.uk.shophub.shophubapp.service.security;

import cf.ac.uk.shophub.shophubapp.data.interfaces.SHRepository;
import cf.ac.uk.shophub.shophubapp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// adapted from https://www.baeldung.com/spring-security-authentication-with-a-database
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private SHRepository shRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = shRepository.findByUsername(username);
        if (user == null) {
            System.out.println("no username found");
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
}