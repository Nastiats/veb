package by.bstu.po15.ats.web.service;

import by.bstu.po15.ats.web.dto.Poezd_placesDto;
import by.bstu.po15.ats.web.entity.Poezd_places;

import java.util.List;

public interface Poezd_placesService
{
    void save(Poezd_placesDto poezd);

    public List<Poezd_places> findByPoezd(Long id);
}
