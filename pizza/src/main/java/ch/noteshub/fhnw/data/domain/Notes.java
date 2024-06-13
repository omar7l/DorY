package ch.noteshub.fhnw.data.domain;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "notes")
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notes_id", nullable = false, updatable = false)
    private Long notesId;

    @Column(name = "notes_title", nullable = false)
    private String notesTitle;

    @OneToMany(mappedBy = "notes", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("notes")
    private Set<Favorite> favorites = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"notes", "favorites"})
    private User user;

    // Getters and Setters
    public Long getNotesId() {
        return notesId;
    }

    public void setNotesId(Long notesId) {
        this.notesId = notesId;
    }

    public String getNotesTitle() {
        return notesTitle;
    }

    public void setNotesTitle(String notesTitle) {
        this.notesTitle = notesTitle;
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }

    public int getFavoriteCount() {
        return favorites != null ? favorites.size() : 0;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
