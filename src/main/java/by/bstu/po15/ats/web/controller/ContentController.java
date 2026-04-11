package by.bstu.po15.ats.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    /**
     * Maps the home page.
     *
     * @return the view name for the home page
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * Maps the home alias page.
     *
     * @return the view name for the home page
     */
    @GetMapping("/home")
    public String homeAlias() {
        return "home";
    }

    /**
     * Maps the login page.
     *
     * @return the view name for the login page
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Maps the admin home page.
     *
     * @return the view name for the admin home page
     */
    @GetMapping("/admin/home")
    public String adminHome() {
        return "adminhome";
    }

    /**
     * Maps the user home page.
     *
     * @return the view name for the user home page
     */
    @GetMapping("/user/home")
    public String userHome() {
        return "userhome";
    }
}
