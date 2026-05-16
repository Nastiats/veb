package by.bstu.po15.ats.web.service.impl;

import by.bstu.po15.ats.web.dto.TicketsDto;
import by.bstu.po15.ats.web.entity.Tickets;
import by.bstu.po15.ats.web.repository.TicketsRepository;
import by.bstu.po15.ats.web.service.TicketsService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

public class TicketsServiceImpl implements TicketsService
{   private TicketsRepository ticketRepository;

    @Override
    public void save(TicketsDto ticket)
    {
        Tickets tt = new Tickets();
        tt.setPoezd_places_id(ticket.getPoezd_places_id());
        tt.setUser_id(ticket.getUser_id());
        tt.setCount(ticket.getCount());
        tt.setCost(ticket.getCost());
        ticketRepository.save(tt);
    }
}
