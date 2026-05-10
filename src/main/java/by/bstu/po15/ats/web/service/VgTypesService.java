package by.bstu.po15.ats.web.service;

import by.bstu.po15.ats.web.dto.VgTypesDto;
import by.bstu.po15.ats.web.entity.VgTypes;

public interface VgTypesService
{   void save(VgTypesDto vgTypesDto);

    VgTypes findById(Long id);
}
