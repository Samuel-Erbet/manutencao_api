package br.com.samuel.manutencao.repository;

import br.com.samuel.manutencao.domain.MaintenanceTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketsRepository extends JpaRepository<MaintenanceTicket, Long> {
}
