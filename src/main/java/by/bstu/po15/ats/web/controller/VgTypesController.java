package by.bstu.po15.ats.web.controller;


import by.bstu.po15.ats.web.entity.VgTypes;


import by.bstu.po15.ats.web.entity.newPassData;
import by.bstu.po15.ats.web.repository.VgTypesRepository;
import by.bstu.po15.ats.web.service.VgTypesService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class VgTypesController
{   private final VgTypesService vgTypesService;
    private final VgTypesRepository vgTypesRepository;

    @GetMapping("/admin/vgtypes/index")
    public String showVgTypesList(HttpServletRequest request, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("vgtypes", vgTypesRepository.findAll());
        return "main";
    }

    @GetMapping("/admin/vgtypes/edit")
    public String showUpdateForm(HttpServletRequest request, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());

        Long id = Long.parseLong(request.getParameter("uid"));

        VgTypes vgt = vgTypesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Невенрый код типа вагона:" + id));

        model.addAttribute("vgtypes", vgt);
        return "main";
    }
    @PostMapping("/admin/vgtypes/edit")
    public String saveUpdateForm(HttpServletRequest request, VgTypes vgt, BindingResult result, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        if(vgt != null && vgt.getId()!=null)
        {   VgTypes bdvgt = vgTypesService.findById(vgt.getId());
            if (bdvgt != null) {
                bdvgt.setName(vgt.getName());
                bdvgt.setShort(vgt.getShort());
                bdvgt.setPlaces(vgt.getPlaces());
                bdvgt.setCost(vgt.getCost());
                vgTypesRepository.save(bdvgt);
                model.addAttribute("message", "Успешное изменение " + vgt.getName());
                return "redirect:/admin/vgtypes/index";
            }
        }
        model.addAttribute("vgtypes", vgt);
        model.addAttribute("error", "Ошибка сохранения " + vgt.getName());
        return "main";
    }


}
