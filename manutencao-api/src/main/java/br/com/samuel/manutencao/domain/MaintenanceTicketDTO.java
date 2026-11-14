package br.com.samuel.manutencao.domain;

import java.util.Objects;

public class MaintenanceTicketDTO {

    private Long id;
    private String description;
    private String Location;
    private String title;
    private String resolution;
    private Boolean solved;

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

    public Boolean getSolved() {
        return solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MaintenanceTicketDTO that = (MaintenanceTicketDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(Location, that.Location) && Objects.equals(title, that.title) && Objects.equals(resolution, that.resolution) && Objects.equals(solved, that.solved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, Location, title, resolution, solved);
    }
}
