package by.bstu.po15.ats.web.service.impl;



import by.bstu.po15.ats.web.entity.Sostav;
import by.bstu.po15.ats.web.repository.Poezd_placesRepository;
import by.bstu.po15.ats.web.repository.SostavRepository;
import by.bstu.po15.ats.web.service.Poezd_placesService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import by.bstu.po15.ats.web.dto.Poezd_placesDto;
import by.bstu.po15.ats.web.entity.Poezd_places;

import java.math.BigDecimal;
import java.util.List;

@Service
public class Poezd_placesServiceImpl implements Poezd_placesService
{   private Poezd_placesService poezd_placesService;
    private Poezd_placesRepository poezd_placesRepository;

    private final SostavRepository sostavRepository;

    Poezd_placesServiceImpl(Poezd_placesRepository poezdRepository,
                            SostavRepository sostavRepository)
    {   this.poezd_placesRepository = poezdRepository;
        this.sostavRepository = sostavRepository;
    }

    @Override
    public List<Poezd_places> findByPoezd(Long id)
    {   Poezd_places probe = new Poezd_places();
        probe.setPoezd_id(id);

        Example<Poezd_places> example = Example.of(probe);
        return poezd_placesRepository.findAll(example, Sort.by("vgnum"));

    }

    @Override
    public void save(Poezd_placesDto sDto )
    {   Poezd_places pzd = new Poezd_places();

        pzd.setPoezd_id(sDto.getPoezd_id());
        pzd.setPoezd_id(sDto.getPoezd_id());
        pzd.setSost_id(sDto.getSost_id());
        pzd.setFree_places(sDto.getFree_places());
        pzd.setCost(sDto.getCost());
        pzd.setVgname(sDto.getVgname());
        pzd.setVgnum(sDto.getVgnum());

        poezd_placesRepository.save(pzd);
    }

}
