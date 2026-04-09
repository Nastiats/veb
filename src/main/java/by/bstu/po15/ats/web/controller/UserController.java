package by.bstu.po15.ats.web.controller;

import by.bstu.po15.ats.web.dto.UserRegisterDto;
import by.bstu.po15.ats.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController
{

    @Autowired
    UserService userService;

    @GetMapping("register")
    public String sayHello()
    {
        return "register";
    }

    // User Register
    @PostMapping("user/register")
    public ModelAndView registerUser(HttpServletRequest request)
    {

        //Extracting Data From HttpServletRequest to DTO
        UserRegisterDto userReigtserDto = new UserRegisterDto();
        userReigtserDto.setName(request.getParameter("name"));
        userReigtserDto.setEmailId(request.getParameter("email"));
        userReigtserDto.setContact(request.getParameter("contact"));
        userReigtserDto.setPassword(request.getParameter("pwd"));

        String result = userService.userRegistration(userReigtserDto);

        ModelAndView modelAndView = new ModelAndView();
        //setting result jsp file name
        modelAndView.setViewName("result");
        modelAndView.addObject("message", result);

        return modelAndView;
    }
}