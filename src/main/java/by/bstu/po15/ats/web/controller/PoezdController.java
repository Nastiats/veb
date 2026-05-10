package by.bstu.po15.ats.web.controller;

import by.bstu.po15.ats.web.entity.Marshrut;
import by.bstu.po15.ats.web.entity.Poezd;
import by.bstu.po15.ats.web.entity.SelectList;

import by.bstu.po15.ats.web.entity.Sostav;
import by.bstu.po15.ats.web.repository.MarshrutRepository;
import by.bstu.po15.ats.web.repository.PoezdRepository;
import by.bstu.po15.ats.web.repository.VgTypesRepository;
import by.bstu.po15.ats.web.service.MarshrutService;
import by.bstu.po15.ats.web.service.PoezdService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PoezdController {
    private final PoezdService poezdService;
    private final PoezdRepository poezdRepository;
    private final MarshrutRepository marshrutRepository;
    private final MarshrutService marshrutService;

    private final VgTypesRepository vgTypesRepository;
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private String zonedDateTimeToTimeStamp(ZonedDateTime zdt, boolean flag )
    {   String tms = zdt.toLocalDateTime()
                .format(dateFormat);
        if(flag)
          tms= tms.replace(' ', 'T');
        return tms;
    }

    private String zonedDateTimeToTimeStamp(ZonedDateTime zdt )
    {   return zonedDateTimeToTimeStamp( zdt, true );
    }

    private ZonedDateTime timestampToZonedDateTime(String tms)
    {   return  LocalDateTime.parse(
                    tms.replace('T', ' '), dateFormat)
                    .atZone( ZoneId.systemDefault()
                );
    }

    public List<Poezd> BuildPoezdList() {
        List<Poezd> pzd = poezdRepository.findAll(
                Sort.by("datetime").ascending()
            );
        for (Poezd sts : pzd)
        {   sts.setMarshrut_name(marshrutService.FindNameMarshrut(sts.getMarshrut_id()));
            sts.setEdit_datetime( zonedDateTimeToTimeStamp(sts.getDatetime(), false ));
        }
        return pzd;
    }

    @GetMapping("/admin/poezd/index")
    public String showPoezdList(HttpServletRequest request, Model model)
    {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("pzd", BuildPoezdList());
        return "main";

    }

    @GetMapping("/admin/poezd/edit")
    public String EditPoezd(HttpServletRequest request, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        Long id = Long.parseLong(request.getParameter("uid"));      // Считали код поезда

        Poezd curPoezd = poezdService.findById(id);
        curPoezd.setEdit_datetime( zonedDateTimeToTimeStamp(curPoezd.getDatetime() ));
        curPoezd.setMarshrut_name(marshrutService.FindNameMarshrut(curPoezd.getMarshrut_id()));

        model.addAttribute("pzd", curPoezd);

        return "main";
    }
    @PostMapping("/admin/poezd/edit")
    public String StoreEditPoezd(HttpServletRequest request, Poezd pzd, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        Long id = Long.parseLong(request.getParameter("uid"));
        Long marshrut_id = Long.parseLong(request.getParameter("marshrut_id")); // Считали код поезда

        if(pzd!=null)
        {   Poezd bpoezd = poezdService.findById(id);

            bpoezd.setNomer(pzd.getNomer());
            // bpoezd.setDatetime(pzd.getDatetime());
            bpoezd.setDatetime( timestampToZonedDateTime(pzd.getEdit_datetime()) );
            bpoezd.setMarshrut_id(marshrut_id);

            poezdRepository.save(bpoezd);
            model.addAttribute("message", "Успешное изменение поезда" + bpoezd.getNomer());
            return "redirect:/admin/poezd/index";
        }
        model.addAttribute("pzd", pzd);

        model.addAttribute("error", "Ошибка сохранения поезда" + pzd.getNomer());
        return "main";
    }

    @GetMapping("/admin/poezd/new")
    public String NewPoezd(HttpServletRequest request, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());

        Poezd curPoezd = new Poezd();
        curPoezd.setMarshrut_id(0L);
        curPoezd.setDatetime(ZonedDateTime.now());
        curPoezd.setEdit_datetime(zonedDateTimeToTimeStamp(curPoezd.getDatetime() ));

        model.addAttribute("pzd", curPoezd);

        List<Marshrut> mrs = marshrutRepository.findAll();
        List<SelectList> lst = new ArrayList<>();
        lst.add(new SelectList(0L, "-- Выберите маршрут --",curPoezd.getMarshrut_id() == 0));
        for( Marshrut mar:mrs)
        {   SelectList ll = new SelectList(
                mar.getId(),
                marshrutService.FindNameMarshrut(mar.getId()),
                curPoezd.getMarshrut_id() == mar.getId()
        );
            lst.add(ll);
        }
        model.addAttribute("mar", lst);
        return "main";
    }
    @PostMapping("/admin/poezd/new")
    public String StoreNewPoezd(HttpServletRequest request, Poezd pzd, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());

        if(pzd!=null)
        {   Poezd bpoezd = new Poezd();

            bpoezd.setNomer(pzd.getNomer());
            // bpoezd.setDatetime(pzd.getDatetime());
            bpoezd.setDatetime( timestampToZonedDateTime(pzd.getEdit_datetime()) );
            bpoezd.setMarshrut_id(pzd.getMarshrut_id());

            poezdRepository.save(bpoezd);
            model.addAttribute("message", "Успешное создание поезда" + bpoezd.getNomer());
            return "redirect:/admin/poezd/index";
        }
        model.addAttribute("pzd", pzd);
        List<Marshrut> mrs = marshrutRepository.findAll();
        List<SelectList> lst = new ArrayList<>();
        lst.add(new SelectList(0L, "-- Выберите маршрут --",pzd.getMarshrut_id() == 0));
        for( Marshrut mar:mrs)
        {   SelectList ll = new SelectList(
                mar.getId(),
                marshrutService.FindNameMarshrut(mar.getId()),
                pzd.getMarshrut_id() == mar.getId()
        );
            lst.add(ll);
        }
        model.addAttribute("mar", lst);

        model.addAttribute("error", "Ошибка создания поезда" + pzd.getNomer());
        return "main";
    }

    @GetMapping("/admin/poezd/delete")
    public String showDeletePoezd(HttpServletRequest request, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        Long id = Long.parseLong(request.getParameter("uid"));      // Считали код поезда

        Poezd curPoezd = poezdService.findById(id);
        curPoezd.setEdit_datetime( zonedDateTimeToTimeStamp(curPoezd.getDatetime() ));
        curPoezd.setMarshrut_name(marshrutService.FindNameMarshrut(curPoezd.getMarshrut_id()));

        model.addAttribute("pzd", curPoezd);

        return "main";
    }
    @PostMapping("/admin/poezd/delete")
    public String saveDeleteForm(HttpServletRequest request, Poezd pzd, BindingResult result, Model model)
    {   model.addAttribute("currentUri", request.getRequestURI());
        Long id = Long.parseLong(request.getParameter("uid"));      // Считали код поезда

        if (pzd != null)
        {   Poezd bpoezd = poezdService.findById(id);
            if( bpoezd!=null)
            {   poezdRepository.delete(bpoezd);
                model.addAttribute("message", "Успешное удаление " + pzd.getNomer());
                return "redirect:/admin/poezd/index";
            }
        }
        model.addAttribute("pzd", pzd);
        model.addAttribute("error", "Ошибка удаления " + pzd.getNomer());
        return "main";
    }
}
