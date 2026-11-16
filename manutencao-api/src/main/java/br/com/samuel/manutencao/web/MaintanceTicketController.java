package br.com.samuel.manutencao.web;

import br.com.samuel.manutencao.domain.MaintenanceTicket;
import br.com.samuel.manutencao.domain.MaintenanceTicketDTO;
import br.com.samuel.manutencao.service.MaintanceTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets/v1")
public class MaintanceTicketController {

    @Autowired
    MaintanceTicketService service;

    @PostMapping("/create")
    public MaintenanceTicketDTO create (@RequestBody MaintenanceTicket ticket){
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

    @PutMapping("/update")
    public MaintenanceTicketDTO update(@RequestBody MaintenanceTicket ticket){
        return service.update(ticket);
    }
}
