package by.bstu.po15.ats.web.controller;


import by.bstu.po15.ats.web.entity.Marshrut;
import by.bstu.po15.ats.web.repository.MarshrutRepository;
import by.bstu.po15.ats.web.service.MarshrutService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MarshrutController
{   private final MarshrutService marshrutService;
    private final MarshrutRepository marshrutRepository;

    @GetMapping("/admin/marshrut/index")
    public String showVgTypesList(HttpServletRequest request, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("mar", marshrutRepository.findAll());
        return "main";
    }

    @GetMapping("/admin/marshrut/edit")
    public String showUpdateForm(HttpServletRequest request, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());

        Long id = Long.parseLong(request.getParameter("uid"));

        Marshrut mrt = marshrutRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Неверный код маршрута:" + id));

        model.addAttribute("mar", mrt);
        return "main";
    }
    @PostMapping("/admin/marshrut/edit")
    public String saveUpdateForm(HttpServletRequest request, Marshrut mrt, BindingResult result, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        if(mrt != null && mrt.getId()!=null)
        {   Marshrut bdvgt = marshrutService.findById(mrt.getId());
            if (bdvgt != null) {
                bdvgt.setNumer(mrt.getNumer());
                bdvgt.setFrm(mrt.getFrm());
                bdvgt.setToto(mrt.getToto());

                marshrutRepository.save(bdvgt);
                model.addAttribute("message", "Успешное изменение " + mrt.getNumer());
                return "redirect:/admin/marshrut/index";
            }
        }
        model.addAttribute("mar", mrt);
        model.addAttribute("error", "Ошибка сохранения " + mrt.getNumer());
        return "main";
    }

    @GetMapping("/admin/marshrut/new")
    public String showNewForm(HttpServletRequest request, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());

       Marshrut mrt = new Marshrut();

        model.addAttribute("mar", mrt);
        return "main";
    }
    @PostMapping("/admin/marshrut/new")
    public String saveNewForm(HttpServletRequest request, Marshrut mrt, BindingResult result, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        if(mrt != null)
        {   Marshrut bdmrt = new Marshrut();
            if (bdmrt != null) {
                bdmrt.setNumer(mrt.getNumer());
                bdmrt.setFrm(mrt.getFrm());
                bdmrt.setToto(mrt.getToto());

                marshrutRepository.save(bdmrt);
                model.addAttribute("message", "Успешное создание " + mrt.getNumer());
                return "redirect:/admin/marshrut/index";
            }
        }
        model.addAttribute("mar", mrt);
        model.addAttribute("error", "Ошибка сохранения " + mrt.getNumer());
        return "main";
    }

    @GetMapping("/admin/marshrut/delete")
    public String showDeleteForm(HttpServletRequest request, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());

        Long id = Long.parseLong(request.getParameter("uid"));

        Marshrut mrt = marshrutRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Неверный код маршрута:" + id));

        model.addAttribute("mar", mrt);
        return "main";
    }
    @PostMapping("/admin/marshrut/delete")
    public String saveDeleteForm(HttpServletRequest request, Marshrut mrt, BindingResult result, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        if(mrt != null && mrt.getId()!=null)
        {   Marshrut bdmrt = marshrutService.findById(mrt.getId());
            if (bdmrt != null) {
                marshrutRepository.delete(bdmrt);
                model.addAttribute("message", "Успешное удаление " + mrt.getNumer());
                return "redirect:/admin/marshrut/index";
            }
        }
        model.addAttribute("mar", mrt);
        model.addAttribute("error", "Ошибка удаления " + mrt.getNumer());
        return "main";
    }
}
