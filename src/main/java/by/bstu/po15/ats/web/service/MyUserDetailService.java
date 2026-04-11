package by.bstu.po15.ats.web.service;

import by.bstu.po15.ats.web.entity.Users;
import by.bstu.po15.ats.web.repository.MyUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final MyUserRepository myUserRepository;

    // Constructor injection for MyUserRepository
    public MyUserDetailService(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    /**
     * Loads the user by username.
     *
     * @param username the username to search for
     * @return the UserDetails object
     * @throws UsernameNotFoundException if the username is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> myUserOptional = myUserRepository.findByUsername(username);
        if (myUserOptional.isPresent()) {
            Users myUser = myUserOptional.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(myUser.getUsername())
                    .password(myUser.getPassword())
                    .roles(myUser.getRoles().split(","))
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
