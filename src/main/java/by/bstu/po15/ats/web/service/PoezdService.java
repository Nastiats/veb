package by.bstu.po15.ats.web.service;

import by.bstu.po15.ats.web.dto.PoezdDto;
import by.bstu.po15.ats.web.entity.Poezd;

import java.util.List;

public interface PoezdService {
    void save(PoezdDto poezd);

    public Poezd findById(Long id);
}
