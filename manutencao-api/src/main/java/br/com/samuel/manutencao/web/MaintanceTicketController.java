package br.com.samuel.manutencao.web;

import br.com.samuel.manutencao.ExceptionHandler.exceptions.TicketResourceNotFoundException;
import br.com.samuel.manutencao.domain.MaintenanceTicket;
import br.com.samuel.manutencao.domain.MaintenanceTicketDTO;
import br.com.samuel.manutencao.repository.TicketsRepository;
import br.com.samuel.manutencao.service.MaintanceTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets/v1")
public class MaintanceTicketController {

    @Autowired
    MaintanceTicketService service;

    @Autowired
    TicketsRepository repository;

    @PostMapping("/create")
    public MaintenanceTicketDTO createTicket(@RequestBody MaintenanceTicket ticket){
        return service.create(ticket);
    }

    @GetMapping("/{id}")
    public MaintenanceTicketDTO findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @GetMapping("/findAll")
    public List<MaintenanceTicketDTO> findAll(){
        return service.findAll();
    }

    @PutMapping("/startCall/{id}")
    public MaintenanceTicketDTO startCall(@PathVariable("id")Long id){
        MaintenanceTicket ticket = repository.findById(id).orElseThrow(
                () -> new TicketResourceNotFoundException("ticket not found.")
        );
        return service.startCall(ticket);
    }
}
