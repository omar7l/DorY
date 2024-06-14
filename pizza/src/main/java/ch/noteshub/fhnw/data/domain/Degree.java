package ch.noteshub.fhnw.data.domain;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "degree")
public class Degree {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "degree_id", nullable = false)
    private Long degreeId;

    @Column(name = "degree_name", nullable = false)
    private String degreeName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    @JsonIgnoreProperties("degrees")
    private Location location;

    @OneToMany(mappedBy = "degree", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("degree")
    private Set<Module> modules = new HashSet<>();

    // Getters and Setters
    public Long getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Long degreeId) {
        this.degreeId = degreeId;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public void addModule(Module module) {
        modules.add(module);
        module.setDegree(this);
    }

    public void removeModule(Module module) {
        modules.remove(module);
        module.setDegree(null);
    }
}
