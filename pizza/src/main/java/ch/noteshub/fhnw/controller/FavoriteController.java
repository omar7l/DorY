package ch.noteshub.fhnw.controller;

import ch.noteshub.fhnw.data.domain.Favorite;
import ch.noteshub.fhnw.data.domain.User;
import ch.noteshub.fhnw.data.domain.Notes;
import ch.noteshub.fhnw.data.repository.FavoriteRepository;
import ch.noteshub.fhnw.data.repository.UserRepository;
import ch.noteshub.fhnw.data.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotesRepository notesRepository;

    @GetMapping
    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favorite> getFavoriteById(@PathVariable Long id) {
        Optional<Favorite> favorite = favoriteRepository.findById(id);
        return favorite.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Favorite> createFavorite(@RequestBody Favorite favorite) {
        Optional<User> userOptional = userRepository.findById(favorite.getUser().getUserId());
        Optional<Notes> notesOptional = notesRepository.findById(favorite.getNotes().getNotesId());

        if (!userOptional.isPresent() || !notesOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        favorite.setUser(userOptional.get());
        favorite.setNotes(notesOptional.get());

        Favorite savedFavorite = favoriteRepository.save(favorite);
        return ResponseEntity.ok(savedFavorite);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long id) {
        Optional<Favorite> favoriteOptional = favoriteRepository.findById(id);
        if (!favoriteOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        favoriteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
