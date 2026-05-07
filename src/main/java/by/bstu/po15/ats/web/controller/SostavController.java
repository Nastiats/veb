package by.bstu.po15.ats.web.controller;


import by.bstu.po15.ats.web.dto.MarshrutDto;
import by.bstu.po15.ats.web.dto.SostavDto;
import by.bstu.po15.ats.web.entity.Marshrut;
import by.bstu.po15.ats.web.entity.SelectList;
import by.bstu.po15.ats.web.entity.Sostav;
import by.bstu.po15.ats.web.repository.MarshrutRepository;
import by.bstu.po15.ats.web.repository.SostavRepository;
import by.bstu.po15.ats.web.service.SostavService;
import by.bstu.po15.ats.web.service.VgTypesService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SostavController
{   private final SostavService sostavService;
    private final SostavRepository sostavRepository;
    private final MarshrutRepository marshrutRepository;

    @GetMapping("/admin/sostav/index")
    public String showSostavList(HttpServletRequest request, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());

        List<Marshrut> mrRep = marshrutRepository.findAll();
        List<SelectList> lst = new ArrayList<>();
        lst.add(new SelectList(0L, "-- Выберите маршрут --",true));
        for(Marshrut sts:mrRep)
        {   SelectList ll = new SelectList(sts.getId(), sts.getNumer() + ":" + sts.getFrm() + " - " + sts.getToto(), false);
            lst.add(ll);
        }
        model.addAttribute("mar", lst);
        model.addAttribute("mMarselectionar", null);

        // model.addAttribute("sost", sostavRepository.findAll());
        return "main";
    }

    @PostMapping("/admin/sostav/index")
    public String selectForm(HttpServletRequest request, SelectList mar, BindingResult result, Model model,
                             Reader reader)
    {   model.addAttribute("currentUri", request.getRequestURI());
        String SelId[]= request.getParameterValues("Marselection"); // SelId[0] - код выбранного маршрута
        Long selMar = Long.parseLong(SelId[0]);

        List<Marshrut> mrRep = marshrutRepository.findAll();
        List<SelectList> lst = new ArrayList<>();
        lst.add(new SelectList(0L, "-- Выберите маршрут --",selMar == 0));
        for(Marshrut sts:mrRep)
        {   SelectList ll = new SelectList(sts.getId(), sts.getNumer() + ":" + sts.getFrm() + " - " + sts.getToto(), sts.getId() == selMar);
            lst.add(ll);
        }
        model.addAttribute("mar", lst);
        model.addAttribute("mMarselectionar", (selMar==0)?null:selMar);

        if( selMar != 0 )
        {   model.addAttribute("sost", sostavService.findByMarshrut(selMar) );
        }
        return "main";
    }
}

