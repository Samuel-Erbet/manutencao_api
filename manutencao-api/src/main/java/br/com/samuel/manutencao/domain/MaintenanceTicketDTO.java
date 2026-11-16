package br.com.samuel.manutencao.domain;

import java.util.Date;
import java.util.Objects;

import static br.com.samuel.manutencao.domain.StatusTicket.ABERTO;

public class MaintenanceTicketDTO {

    private Long id;
    private String description;
    private String Location;
    private String title;
    private String resolution;
    private String solved;
    private StatusTicket status = ABERTO;

    public MaintenanceTicketDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public StatusTicket getStatus() {
        return status;
    }

    public String getSolved() {
        return solved;
    }

    public void setSolved(String solved) {
        this.solved = solved;
    }

    public void setStatus(StatusTicket status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MaintenanceTicketDTO that = (MaintenanceTicketDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getLocation(), that.getLocation()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getResolution(), that.getResolution()) && Objects.equals(getSolved(), that.getSolved()) && getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getLocation(), getTitle(), getResolution(), getSolved(), getStatus());
    }
}
