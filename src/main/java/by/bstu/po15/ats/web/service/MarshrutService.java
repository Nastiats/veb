package by.bstu.po15.ats.web.service;


import by.bstu.po15.ats.web.dto.MarshrutDto;
import by.bstu.po15.ats.web.entity.Marshrut;
import lombok.RequiredArgsConstructor;

public interface MarshrutService
{   void save(MarshrutDto marshrut);

    Marshrut findById(Long id);

    public String FindNameMarshrut(Long Id);
}
