package by.bstu.po15.ats.web.service;

import by.bstu.po15.ats.web.dto.UserDto;
import by.bstu.po15.ats.web.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto>findAllUsers();
}