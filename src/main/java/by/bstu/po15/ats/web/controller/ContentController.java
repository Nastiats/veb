package by.bstu.po15.ats.web.controller;


import by.bstu.po15.ats.web.dto.UserDto;
import by.bstu.po15.ats.web.entity.User;
import by.bstu.po15.ats.web.repository.UserRepository;
import by.bstu.po15.ats.web.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private final UserRepository userRepository;

    private BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();

    private String passEncode(String rawPassword)   // Возвращает крипто-хеш для заданной строки
    { return passEncoder.encode(rawPassword);
    }

    private boolean isPassMatch(String rawPass, String OldHash) // Проверяет соответствие хешей
    { return passEncoder.matches(rawPass, OldHash);

    }

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

    @GetMapping("/new")
    public String RegisterUser(HttpServletRequest request, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        UserDto userDto = new UserDto();
        model.addAttribute("user",userDto); //model object is used to store data that is entered from form.

        return "register";
    }


    @PostMapping("/new/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model)
    { //Model attribute is used to extract model object which is a form data.

        User existingUser = userService.findByEmail(userDto.getEmail()); //  Проверим, существует ли такой email

        if(existingUser!=null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty())
        {
            result.rejectValue("email",null,"Ваш e-mail уже зарегистрирован! Попробуйте восстановить пароль!");
        }

        if(result.hasErrors())
        {
            model.addAttribute("user",userDto);
            return "register"; // if any form has errors it will be redirected to register page only.
        }
        userDto.setUsanswer( passEncode(userDto.getUsanswer()) );
        userService.saveUser(userDto);
        return "redirect:/login"; // Переход на ввод пароля,после успешной регистрации
    }

    @GetMapping("/restore")
    public String restoreGetMail(HttpServletRequest request, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        UserDto userDto = new UserDto();
        model.addAttribute("user",userDto); // userDto - используется для обмена данными с формой

        return "restore";
    }

    @PostMapping("/restore/email")
    public String restorePostMail(HttpServletRequest request, @Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());

        User existingUser = userService.findByEmail(userDto.getEmail()); //  Проверим, существует ли такой email

        if(existingUser!=null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty()) // проверяем, что такой email зарегистрирован !
        {   model.addAttribute("email", userDto.getEmail() );
            UserDto user2Dto = new UserDto();
            user2Dto.setEmail(existingUser.getEmail());
            user2Dto.setUsquery(existingUser.getUsquery());
            model.addAttribute("user",user2Dto); // userDto - используется для обмена данными с формой
        }
        return "restore";
    }

    @PostMapping("/restore/save")
    public String restorePostSave(HttpServletRequest request, @Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());

        User existingUser = userService.findByEmail(userDto.getEmail()); //  Проверим, существует ли такой email

        if(existingUser!=null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty()) // проверяем, что такой email зарегистрирован !
        {   if( isPassMatch(userDto.getUsanswer(), existingUser.getUsanswer() ) )
            {   // если такой email существует, и ответ на вопрос совпадает - можем установить новый пароль
                existingUser.setPassword(passEncode(userDto.getPassword()));
                userRepository.save(existingUser);
            }
        }
        return "redirect:/main";
    }



    //handler methods for getting list of users.
    @GetMapping("/admin/auth")
    public String users(HttpServletRequest request, Model model){
        model.addAttribute("currentUri", request.getRequestURI());

        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users",users);

        return "main";
    }
}

