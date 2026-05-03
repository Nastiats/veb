package by.bstu.po15.ats.web.service.impl;

import by.bstu.po15.ats.web.dto.UserDto;
import by.bstu.po15.ats.web.entity.Role;
import by.bstu.po15.ats.web.entity.User;
import by.bstu.po15.ats.web.repository.RoleRepository;
import by.bstu.po15.ats.web.repository.UserRepository;
import by.bstu.po15.ats.web.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder)
    {   this.userRepository=userRepository;
        this.roleRepository=roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void saveUser(UserDto userDto)
    {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setUsquery(userDto.getUsquery());
        user.setUsanswer(userDto.getUsanswer());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Получаемсписок ролей полььзователя
        Role role = roleRepository.findByName("ROLE_USER");

        if(role == null)    // Если нет ни одной назначенной роли
        {   role=defRoleList(); // Назначаем список ролей по умолчанию
        }
        user.setRoles(Arrays.asList(role));

        userRepository.save(user); // Сохраняем объект в базу данных

    }

    @Override
    public User findByEmail(String email)   // Находит и загружает из базы объет с заданным email
    {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() // Загружает объекты из базы
    {
        List<User> users = userRepository.findAll();
        List<UserDto> user_dto = new ArrayList<>();
        for(User user:users)
        {   UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setUsquery(user.getUsquery());
            userDto.setUsanswer(user.getUsanswer());
            user_dto.add(userDto);
        }
        return user_dto;
    }

    private Role defRoleList(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}