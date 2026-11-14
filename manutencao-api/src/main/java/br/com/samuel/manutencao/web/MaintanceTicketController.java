package br.com.samuel.manutencao.web;

import br.com.samuel.manutencao.domain.MaintenanceTicket;
import br.com.samuel.manutencao.domain.MaintenanceTicketDTO;
import br.com.samuel.manutencao.service.MaintanceTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MaintanceTicketController {

    @Autowired
    MaintanceTicketService service;

    @PostMapping("/create")
    public MaintenanceTicketDTO createTicket (@RequestBody MaintenanceTicket ticket){

        return service.create(ticket);
    }
}
