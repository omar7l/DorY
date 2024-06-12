package ch.noteshub.fhnw.business.service;

import ch.noteshub.fhnw.data.domain.Favorite;
import ch.noteshub.fhnw.data.domain.User;
import ch.noteshub.fhnw.data.domain.Notes;
import ch.noteshub.fhnw.data.repository.FavoriteRepository;
import ch.noteshub.fhnw.data.repository.UserRepository;
import ch.noteshub.fhnw.data.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotesRepository notesRepository;

    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    public Optional<Favorite> getFavoriteById(Long id) {
        return favoriteRepository.findById(id);
    }

    public Favorite createFavorite(Favorite favorite) {
        Optional<User> userOptional = userRepository.findById(favorite.getUser().getUserId());
        Optional<Notes> notesOptional = notesRepository.findById(favorite.getNotes().getNotesId());

        if (!userOptional.isPresent() || !notesOptional.isPresent()) {
            throw new IllegalArgumentException("Invalid user or notes ID");
        }

        favorite.setUser(userOptional.get());
        favorite.setNotes(notesOptional.get());

        return favoriteRepository.save(favorite);
    }

    public void deleteFavorite(Long id) {
        favoriteRepository.deleteById(id);
    }

    public List<Favorite> getFavoritesByUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        return favoriteRepository.findByUser(userOptional.get());
    }

    public List<Favorite> getFavoritesByNotes(Long notesId) {
        Optional<Notes> notesOptional = notesRepository.findById(notesId);
        if (!notesOptional.isPresent()) {
            throw new IllegalArgumentException("Invalid notes ID");
        }
        return favoriteRepository.findByNotes(notesOptional.get());
    }
}
