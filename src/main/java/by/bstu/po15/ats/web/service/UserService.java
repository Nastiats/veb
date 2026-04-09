package by.bstu.po15.ats.web.service;

import by.bstu.po15.ats.web.dto.UserRegisterDto;
import by.bstu.po15.ats.web.entity.UsersInfo;
import by.bstu.po15.ats.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService
{
    @Autowired
    UserRepository repository;

    public String userRegistration(UserRegisterDto userReigtserDto)
    {
        // convert dto instance  to entity object
        UsersInfo user = new UsersInfo();
        user.setContact(userReigtserDto.getContact());
        user.setEmailId(userReigtserDto.getEmailId());
        user.setName(userReigtserDto.getName());
        user.setPassword(userReigtserDto.getPassword());

        repository.save(user);

        return "User Registration Successfull.";
    }

    public String validateUser(String emailId, String password) {

        // Verify in data base
        List<UsersInfo> users = repository.findByEmailIdAndPassword(emailId, password);

        if (users.size() == 0) {
            return "Invalid Credentilas. Please Try again";
        } else {
            return "Welcome to FaceBook, " + emailId;
        }

    }

}