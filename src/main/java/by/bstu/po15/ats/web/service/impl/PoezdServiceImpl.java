package by.bstu.po15.ats.web.service.impl;


import by.bstu.po15.ats.web.dto.PoezdDto;
import by.bstu.po15.ats.web.entity.Marshrut;
import by.bstu.po15.ats.web.entity.Poezd;
import by.bstu.po15.ats.web.repository.PoezdRepository;
import by.bstu.po15.ats.web.repository.SostavRepository;
import by.bstu.po15.ats.web.service.MarshrutService;
import by.bstu.po15.ats.web.service.PoezdService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PoezdServiceImpl implements PoezdService
{   private PoezdRepository poezdRepository;

    PoezdServiceImpl(PoezdRepository poezdRepository)
    {   this.poezdRepository = poezdRepository;
    }

    @Override
    public Poezd findById(Long id)
    {   return poezdRepository.getById(id);
    }

    @Override
    public void save(PoezdDto sDto )
    {   Poezd pzd = new Poezd();

        pzd.setMarshrut_id(sDto.getMarshrut_id());
        pzd.setNomer(sDto.getNomer());
        pzd.setDatetime(sDto.getDatetime ());

        poezdRepository.save(pzd);
    }

}
