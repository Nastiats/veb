package by.bstu.po15.ats.web.controller;


import by.bstu.po15.ats.web.dto.MarshrutDto;
import by.bstu.po15.ats.web.dto.SostavDto;
import by.bstu.po15.ats.web.entity.Marshrut;
import by.bstu.po15.ats.web.entity.SelectList;
import by.bstu.po15.ats.web.entity.Sostav;
import by.bstu.po15.ats.web.entity.VgTypes;
import by.bstu.po15.ats.web.repository.MarshrutRepository;
import by.bstu.po15.ats.web.repository.SostavRepository;
import by.bstu.po15.ats.web.repository.VgTypesRepository;
import by.bstu.po15.ats.web.service.MarshrutService;
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
    private final VgTypesService vgTypesService;
    private final VgTypesRepository vgTypesRepository;

    private final MarshrutService marshrutService;

    @GetMapping("/admin/sostav/index")
    public String selectForm(HttpServletRequest request, Model model)
    {   Long selMar = 0L;
        model.addAttribute("currentUri", request.getRequestURI());

        String SelId[]= request.getParameterValues("mrs"); // SelId[0] - код выбранного маршрута

        if( (SelId !=  null ) && (  ! SelId[0].isEmpty()) )
        {   selMar = Long.parseLong(SelId[0]);
        }

        List<Marshrut> mrRep = marshrutRepository.findAll();
        List<SelectList> lst = new ArrayList<>();
        lst.add(new SelectList(0L, "-- Выберите маршрут --", (selMar == 0) ) );
        for(Marshrut sts:mrRep)
        {   SelectList ll = new SelectList(sts.getId(),
                sts.getNumer() + ":" + sts.getFrm() + " - " + sts.getToto(),
                (sts.getId() == selMar) );
            lst.add(ll);
        }
        model.addAttribute("mar", lst);
        model.addAttribute("mrs", selMar);

        if( selMar != 0 )
        {   List<Sostav> listView= sostavService.findByMarshrut(selMar) ;
            for( Sostav item:listView)
            { item.setVtype(vgTypesService.findById(item.getVgid()).getShort());
            }
            model.addAttribute("sost", listView);
        }
        return "main";
    }

    @GetMapping("/admin/sostav/edit")
    public String EditSostav(HttpServletRequest request, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        Long marsId = Long.parseLong(request.getParameter("mrs"));  // Считали код маршрута
        Long id = Long.parseLong(request.getParameter("uid"));      // Считали код вагона

        model.addAttribute("mrs", marshrutService.findById(marsId));

        Sostav sts = sostavService.findById(id);
        sts.setVtype(vgTypesService.findById(sts.getVgid()).getShort());
        model.addAttribute("sost", sts);

        List<VgTypes> vgs  = vgTypesRepository.findAll();
        List<SelectList> lst = new ArrayList<>();
        lst.add(new SelectList(0L, "-- Выберите тип вагона --",sts.getVgid() == 0));
        for(VgTypes tt:vgs)
        {   SelectList ll = new SelectList( tt.getId(),
                tt.getName() + " (" + tt.getShort() + ")",
              tt.getId() == sts.getVgid());
            lst.add(ll);
        }
        model.addAttribute("vgs", lst);
        return "main";
    }
    @PostMapping("/admin/sostav/edit")
    public String StoreEditSostav(HttpServletRequest request, Sostav sost, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        Long marsId = Long.parseLong(request.getParameter("mrs"));  // Считали код маршрута
        Long id = Long.parseLong(request.getParameter("uid"));      // Считали код вагона

        if(sost != null && sost.getVgid()!=null)
        {   Sostav bds = sostavService.findById(id);
            bds.setMarshrut_id(marsId);
            bds.setVgid(sost.getVgid());
            bds.setNumer(sost.getNumer());
            bds.setCost(sost.getCost());

            sostavRepository.save(bds);
            model.addAttribute("message", "Успешное изменение " + sost.getNumer());
            return "redirect:/admin/sostav/index?mrs=" + marsId;
        }
        model.addAttribute("mrs", marsId);
        model.addAttribute("sost", sost);
        List<VgTypes> vgs  = vgTypesRepository.findAll();
        List<SelectList> lst = new ArrayList<>();
        lst.add(new SelectList(0L, "-- Выберите тип вагона --",sost.getVgid() == 0));
        for(VgTypes tt:vgs)
        {   SelectList ll = new SelectList( tt.getId(),
                tt.getName() + " (" + tt.getShort() + ")",
                tt.getId() == sost.getVgid());
            lst.add(ll);
        }
        model.addAttribute("vgs", lst);
        model.addAttribute("error", "Ошибка сохранения " + sost.getNumer());
        return "main";
    }

    @GetMapping("/admin/sostav/new")
    public String showNewForm(HttpServletRequest request, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        Long marsId = Long.parseLong(request.getParameter("mrs"));  // Считали код маршрута

        model.addAttribute("mrs", marshrutService.findById(marsId));

        Sostav sost = new Sostav();
        sost.setVgid(0L);

        model.addAttribute("sost", sost);

        List<VgTypes> vgs  = vgTypesRepository.findAll();
        List<SelectList> lst = new ArrayList<>();
        lst.add(new SelectList(0L, "-- Выберите тип вагона --",sost.getVgid() == 0));
        for(VgTypes tt:vgs)
        {   SelectList ll = new SelectList( tt.getId(),
                tt.getName() + " (" + tt.getShort() + ")",
                tt.getId() == sost.getVgid());
            lst.add(ll);
        }
        model.addAttribute("vgs", lst);

        return "main";
    }
    @PostMapping("/admin/sostav/new")
    public String StoreNewSostav(HttpServletRequest request, Sostav sost, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        Long marsId = Long.parseLong(request.getParameter("mrs"));  // Считали код маршрута

        if(sost != null && sost.getVgid()!=null)
        {   Sostav bds = new Sostav();
            bds.setMarshrut_id(marsId);
            bds.setVgid(sost.getVgid());
            bds.setNumer(sost.getNumer());
            bds.setCost(sost.getCost());

            sostavRepository.save(bds);
            model.addAttribute("message", "Успешное создание " + sost.getNumer());
            return "redirect:/admin/sostav/index?mrs=" + marsId;
        }
        model.addAttribute("mrs", marsId);
        model.addAttribute("sost", sost);
        List<VgTypes> vgs  = vgTypesRepository.findAll();
        List<SelectList> lst = new ArrayList<>();
        lst.add(new SelectList(0L, "-- Выберите тип вагона --",sost.getVgid() == 0));
        for(VgTypes tt:vgs)
        {   SelectList ll = new SelectList( tt.getId(),
                tt.getName() + " (" + tt.getShort() + ")",
                tt.getId() == sost.getVgid());
            lst.add(ll);
        }
        model.addAttribute("vgs", lst);
        model.addAttribute("error", "Ошибка сохранения " + sost.getNumer());
        return "main";
    }

    @GetMapping("/admin/sostav/delete")
    public String showDeleteForm(HttpServletRequest request, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());

        Long marsId = Long.parseLong(request.getParameter("mrs"));  // Считали код маршрута
        Long id = Long.parseLong(request.getParameter("uid"));      // Считали код вагона

        model.addAttribute("mrs", marshrutService.findById(marsId));

        Sostav sts = sostavService.findById(id);
        sts.setVtype(vgTypesService.findById(sts.getVgid()).getShort());
        model.addAttribute("sost", sts);
        return "main";
    }
    @PostMapping("/admin/sostav/delete")
    public String saveDeleteForm(HttpServletRequest request, Sostav sost, BindingResult result, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        Long marsId = Long.parseLong(request.getParameter("mrs"));  // Считали код маршрута
        Long id = Long.parseLong(request.getParameter("uid"));      // Считали код вагона

        if(sost != null )
        {   Sostav bdmrt = sostavService.findById(id);
            if (bdmrt != null) {
                sostavRepository.delete(bdmrt);
                model.addAttribute("message", "Успешное удаление " + sost.getNumer());
                return "redirect:/admin/sostav/index?mrs=" + marsId;
            }
        }
        model.addAttribute("mrs", marshrutService.findById(marsId));
        Sostav sts = sostavService.findById(id);
        sts.setVtype(vgTypesService.findById(sts.getVgid()).getShort());
        model.addAttribute("sost", sts);
        model.addAttribute("error", "Ошибка удаления " + sost.getNumer());
        return "main";
    }
}

