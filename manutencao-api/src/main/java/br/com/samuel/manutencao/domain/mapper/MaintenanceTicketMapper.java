package br.com.samuel.manutencao.domain.mapper;

import br.com.samuel.manutencao.domain.MaintenanceTicket;
import br.com.samuel.manutencao.domain.MaintenanceTicketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MaintenanceTicketMapper {

    MaintenanceTicket toEntity(MaintenanceTicketDTO ticketDTO);
    MaintenanceTicketDTO toDTO(MaintenanceTicket ticket);
    List<MaintenanceTicketDTO> listToDTO(List<MaintenanceTicket> tickets);

}
