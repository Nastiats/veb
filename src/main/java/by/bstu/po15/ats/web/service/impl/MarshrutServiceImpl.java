package by.bstu.po15.ats.web.service.impl;

import by.bstu.po15.ats.web.dto.MarshrutDto;
import by.bstu.po15.ats.web.entity.Marshrut;
import by.bstu.po15.ats.web.repository.MarshrutRepository;
import by.bstu.po15.ats.web.service.MarshrutService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarshrutServiceImpl implements MarshrutService
{   private MarshrutRepository marshrutRepository;

    MarshrutServiceImpl(MarshrutRepository marshrutRepository)
    {   this.marshrutRepository =marshrutRepository;
    }

    @Override
    public void save(MarshrutDto maRDto)
    {   Marshrut marshrut = new Marshrut();

        marshrut.setNumer(maRDto.getNumer());
        marshrut.setFrm(maRDto.getFrm());
        marshrut.setToto(maRDto.getToto());
        marshrutRepository.save(marshrut);
    }

    @Override
    public Marshrut findById(Long id)
    {   return marshrutRepository.getById(id);
    }


    public List<MarshrutDto> findAll() // Загружает объекты из базы
    {
        List<Marshrut> marshrutList = marshrutRepository.findAll();
        List<MarshrutDto> mar_dto = new ArrayList<>();
        for(Marshrut mar:marshrutList)
        {   MarshrutDto marshrutDto = new MarshrutDto();
            marshrutDto.setId(mar.getId());
            marshrutDto.setNumer(mar.getNumer());
            marshrutDto.setFrm(mar.getFrm());
            marshrutDto.setToto(mar.getToto());

            mar_dto.add(marshrutDto);
        }
        return mar_dto;
    }

}
