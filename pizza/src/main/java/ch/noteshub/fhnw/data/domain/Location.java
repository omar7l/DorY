package ch.noteshub.fhnw.data.domain;

import jakarta.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id", nullable = false)
    private Long locationId;

    @Column(name = "location_name", nullable = false)
    private String locationName;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("location")
    private Set<Degree> degrees = new HashSet<>();

    // Getters and Setters
    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Set<Degree> getDegrees() {
        return degrees;
    }

    public void setDegrees(Set<Degree> degrees) {
        this.degrees = degrees;
    }

    public void addDegree(Degree degree) {
        degrees.add(degree);
        degree.setLocation(this);
    }

    public void removeDegree(Degree degree) {
        degrees.remove(degree);
        degree.setLocation(null);
    }
}
