package by.bstu.po15.ats.web.service.impl;

import by.bstu.po15.ats.web.dto.VgTypesDto;
import by.bstu.po15.ats.web.entity.VgTypes;
import by.bstu.po15.ats.web.repository.VgTypesRepository;
import by.bstu.po15.ats.web.service.VgTypesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VgTypesServiceImpl implements VgTypesService
{   private VgTypesRepository vgTypesRepository;

    VgTypesServiceImpl(VgTypesRepository vgTypesRepository)
    {   this.vgTypesRepository=vgTypesRepository;
    }

    @Override
    public void save(VgTypesDto vgTDto)
    {   VgTypes vgt = new VgTypes();
        vgt.setName(vgTDto.getName());
        vgt.setCost(vgTDto.getCost());
        vgt.setShort(vgTDto.getShort());
        vgt.setPlaces(vgTDto.getPlaces());
        vgTypesRepository.save(vgt);
    }

    @Override
    public VgTypes findById(Long id)
    {   return vgTypesRepository.getById(id);
    }


    public List<VgTypesDto> findAll() // Загружает объекты из базы
    {
        List<VgTypes> vgt = vgTypesRepository.findAll();
        List<VgTypesDto> vgt_dto = new ArrayList<>();
        for(VgTypes vv:vgt)
        {   VgTypesDto vgTypesDto = new VgTypesDto();
            vgTypesDto.setId(vv.getId());
            vgTypesDto.setName(vv.getName());
            vgTypesDto.setPlaces(vv.getPlaces());
            vgTypesDto.setShort(vv.getShort());
            vgTypesDto.setCost(vv.getCost());
            vgt_dto.add(vgTypesDto);
        }
        return vgt_dto;
    }

}
