package br.com.samuel.manutencao.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class MaintenanceTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "location", nullable = false, length = 50)
    private String location;

    @Column(name = "title",nullable = false, length = 50)
    private String title;

    @Column(name = "resolution")
    private String resolution;

    @Column(name = "solved",length = 8)
    private Boolean solved = false;

    public MaintenanceTicket() {
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
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        MaintenanceTicket that = (MaintenanceTicket) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getLocation(), that.getLocation()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getResolution(), that.getResolution()) && Objects.equals(getSolved(), that.getSolved());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getLocation(), getTitle(), getResolution(), getSolved());
    }
}
