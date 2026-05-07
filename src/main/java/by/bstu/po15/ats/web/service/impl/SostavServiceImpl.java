package by.bstu.po15.ats.web.service.impl;

import by.bstu.po15.ats.web.dto.MarshrutDto;
import by.bstu.po15.ats.web.dto.SostavDto;
import by.bstu.po15.ats.web.entity.Marshrut;
import by.bstu.po15.ats.web.entity.Sostav;
import by.bstu.po15.ats.web.repository.MarshrutRepository;
import by.bstu.po15.ats.web.repository.SostavRepository;
import by.bstu.po15.ats.web.service.SostavService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SostavServiceImpl implements SostavService
{   private SostavRepository sostavRepository;

    private final MarshrutRepository marshrutRepository;

    SostavServiceImpl(SostavRepository sostavRepository,
                      MarshrutRepository marshrutRepository)

    {   this.sostavRepository = sostavRepository;
        this.marshrutRepository = marshrutRepository;
    }

    @Override
    public Sostav findById(Long id)
    {   return sostavRepository.getById(id);
    }

    @Override
    public void save(SostavDto sDto )
    {   Sostav sost = new Sostav();
        sost.setMarshrut_id(sDto.getMarshrut_id());
        sost.setCost(sDto.getCost());
        sost.setNumer(sDto.getNumer());
        sost.setVgid(sDto.getVgid());

        sostavRepository.save(sost);
    }

    public List<Sostav> findByMarshrut(Long marshrut_id)
    {
        Sostav probe = new Sostav();
        probe.setMarshrut_id(marshrut_id);

        Example<Sostav> example = Example.of(probe);

        return sostavRepository.findAll(example);
    }

    public List<SostavDto> findAll() // Загружает объекты из базы
    {
        List<Sostav> sostavList = sostavRepository.findAll();
        List<SostavDto> sts_dto = new ArrayList<>();
        for(Sostav sts:sostavList)
        {   SostavDto sostavDto = new SostavDto();

            sostavDto.setId(sts.getId());
            sostavDto.setNumer(sts.getNumer());
            sostavDto.setMarshrut_id(sts.getMarshrut_id());
            sostavDto.setVgid(sts.getVgid());
            sostavDto.setCost(sts.getCost());

            sts_dto.add(sostavDto);
        }
        return sts_dto;
    }
}
