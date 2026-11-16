package br.com.samuel.manutencao.service;

import br.com.samuel.manutencao.ExceptionHandler.exceptions.TicketResourceNotFoundException;
import br.com.samuel.manutencao.ExceptionHandler.exceptions.TicketStatusException;
import br.com.samuel.manutencao.domain.MaintenanceTicket;
import br.com.samuel.manutencao.domain.MaintenanceTicketDTO;
import br.com.samuel.manutencao.domain.StatusTicket;
import br.com.samuel.manutencao.domain.mapper.MaintenanceTicketMapper;
import br.com.samuel.manutencao.ExceptionHandler.exceptions.TicketResourceNullException;
import br.com.samuel.manutencao.repository.TicketsRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.samuel.manutencao.domain.StatusTicket.ABERTO;
import static br.com.samuel.manutencao.domain.StatusTicket.EM_ANDAMENTO;

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

        ) throw new TicketResourceNullException("informations are missing in your ticket.");

        logger.info("Creating a ticket.");
        MaintenanceTicket savedTicket = repository.save(ticket);
        return mapper.toDTO(savedTicket);
    }

    public MaintenanceTicketDTO findById(Long id) {
        MaintenanceTicket wantedTicket = repository.findById(id).orElseThrow(
                () -> new TicketResourceNotFoundException("ticket not found.")
        );
        logger.info("Finding a ticket.");

        return mapper.toDTO(wantedTicket);
    }

    public List<MaintenanceTicketDTO> findAll() {
        logger.info("Finding all tickets.");
        return mapper.listToDTO(repository.findAll());
    }

    @Transactional
    public MaintenanceTicketDTO startCall(Long id) {
        logger.info("updating tickets.");
        MaintenanceTicket updatedTicket = repository.findById(id).orElseThrow(
                () -> new TicketResourceNotFoundException("ticket not found.")
        );

        if (!updatedTicket.getStatus().equals(ABERTO))
            throw new TicketStatusException("you can only open a ticket with status in ABERTO");

        updatedTicket.setStatus(EM_ANDAMENTO);
        repository.save(updatedTicket);
        return mapper.toDTO(updatedTicket);
    }
}
