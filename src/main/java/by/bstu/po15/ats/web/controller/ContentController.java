package by.bstu.po15.ats.web.controller;


import by.bstu.po15.ats.web.dto.UserDto;
import by.bstu.po15.ats.web.entity.User;
import by.bstu.po15.ats.web.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ContentController
{

    private final UserService userService;

    /**
     * Maps the home page.
     *
     * @return the view name for the home page
     */
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model)
    {   // return "home";
        model.addAttribute("currentUri", request.getRequestURI());
        return "main";
    }

    /**
     * Maps the home alias page.
     *
     * @return the view name for the home page
     */
    @GetMapping("/home")
    public String homeAlias(HttpServletRequest request, Model model)
    {   // return "home";
        model.addAttribute("currentUri", request.getRequestURI());
        return "main";
    }

    /**
     * Maps the login page.
     *
     * @return the view name for the login page
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        return "login";
    }

    /**
     * Maps the admin home page.
     *
     * @return the view name for the admin home page
     */
    @GetMapping("/admin/home")
    public String adminHome(HttpServletRequest request, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        // return "adminhome";
        return "main";
    }

    /**
     * Maps the user home page.
     *
     * @return the view name for the user home page
     */
    @GetMapping("/user/home")
    public String userHome(HttpServletRequest request, Model model)
    {   // return "userhome";
        model.addAttribute("currentUri", request.getRequestURI());
        return "main";
    }

    @GetMapping("/register")
    public String RegisterUser(HttpServletRequest request, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        UserDto userDto = new UserDto();
        model.addAttribute("user",userDto); //model object is used to store data that is entered from form.

        return "register";
    }


    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model){ //Model attribute is used to extract model object which is a form data.


        User existingUser = userService.findByEmail(userDto.getEmail()); //checking if entered email already exists or not.

        if(existingUser!=null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email",null,"there is already an account existed with this email");
        }

        if(result.hasErrors()){
            model.addAttribute("user",userDto);
            return "/register"; // if any form has errors it will be redirected to register page only.
        }

        userService.saveUser(userDto);
        return "redirect:/register?success"; // @Valid from jakarta.validation will enable the validation fields of dto objectsto be enabled.

    }

    //handler methods for getting list of users.
    @GetMapping("/admin/users")
    public String users(HttpServletRequest request, Model model){
        model.addAttribute("currentUri", request.getRequestURI());

        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users",users);
     //   return "users";
        return "main";
    }
}

