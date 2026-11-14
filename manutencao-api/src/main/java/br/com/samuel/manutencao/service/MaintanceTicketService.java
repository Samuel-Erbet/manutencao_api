package br.com.samuel.manutencao.service;

import br.com.samuel.manutencao.domain.MaintenanceTicket;
import br.com.samuel.manutencao.domain.MaintenanceTicketDTO;
import br.com.samuel.manutencao.domain.mapper.MaintenanceTicketMapper;
import br.com.samuel.manutencao.repository.TicketsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaintanceTicketService {

    private final TicketsRepository repository;
    private final MaintenanceTicketMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(MaintanceTicketService.class);

    public MaintanceTicketService(TicketsRepository repository, MaintenanceTicketMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public MaintenanceTicketDTO create(MaintenanceTicket ticket){
        if(
            ticket.getDescription() == null ||
            ticket.getDescription().isBlank() ||
            ticket.getLocation() == null ||
            ticket.getLocation().isBlank() ||
            ticket.getTitle() == null ||
            ticket.getTitle().isBlank()

        ) throw new RuntimeException("informations are missing in your ticket.");

        logger.info("Creating a ticket.");
        MaintenanceTicket savedTicket = repository.save(ticket);
        return mapper.toDTO(savedTicket);
    }

}
