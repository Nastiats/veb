package by.bstu.po15.ats.web.security;

import by.bstu.po15.ats.web.entity.User;
import by.bstu.po15.ats.web.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.stream.Collectors;

@Service
public class CustomUserDetails implements UserDetailsService
{
    private UserRepository userRepository;
    public CustomUserDetails(UserRepository userRepository)
    {   this.userRepository=userRepository;
    }

    @Override   // Извлекает из базы объекты типа User и связывает их с аттрибутами безопасности
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {   User user = userRepository.findByEmail(email);
        if(user!=null)
        {   return new org.springframework.security.core.userdetails.User(
                user.getEmail(),    //  email пользователя  - как поле логина
                user.getPassword(), // pssword пользователя - как поле пароля
                // строит список ролей доступа, связанных с пользователем
                user.getRoles().stream().map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
            );
        }
        else
        {   throw new UsernameNotFoundException("Неверный e-mail или пароль!");
        }
    }
}