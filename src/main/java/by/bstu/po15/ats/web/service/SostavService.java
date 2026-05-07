package by.bstu.po15.ats.web.service;


import by.bstu.po15.ats.web.dto.SostavDto;
import by.bstu.po15.ats.web.entity.Sostav;

import java.util.List;

public interface SostavService
{
    void save(SostavDto sostav);

    Sostav findById(Long id);

    List<Sostav> findByMarshrut(Long marshrut_id);

    public List<SostavDto> findAll(); // Загружает объекты из базы
}
