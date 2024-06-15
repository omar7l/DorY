package ch.noteshub.fhnw.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.noteshub.fhnw.data.domain.CreateNoteRequest;
import ch.noteshub.fhnw.data.domain.Favorite;
import ch.noteshub.fhnw.data.domain.Notes;
import ch.noteshub.fhnw.data.domain.User;
import ch.noteshub.fhnw.data.domain.Module;
import ch.noteshub.fhnw.data.repository.FavoriteRepository;
import ch.noteshub.fhnw.data.repository.ModuleRepository;
import ch.noteshub.fhnw.data.repository.NotesRepository;
import ch.noteshub.fhnw.data.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Transactional
    public void addFavorite(Long userId, Long notesId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Notes notes = notesRepository.findById(notesId).orElseThrow(() -> new IllegalArgumentException("Invalid notes ID"));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setNotes(notes);
        favoriteRepository.save(favorite);
    }

    @Transactional
    public void removeFavorite(Long userId, Long notesId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Notes notes = notesRepository.findById(notesId).orElseThrow(() -> new IllegalArgumentException("Invalid notes ID"));

        Favorite favorite = favoriteRepository.findByUserAndNotes(user, notes);
        if (favorite != null) {
            favoriteRepository.delete(favorite);
        }
    }

    public Notes findById(Long notesId) {
        return notesRepository.findById(notesId).orElseThrow(() -> new IllegalArgumentException("Invalid notes ID"));
    }

    public List<Favorite> findFavoritesByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        return favoriteRepository.findByUser(user);
    }

    public List<Notes> findNotesByTitleContaining(String title) {
        return notesRepository.findByNotesTitleContaining(title);
    }

    @Transactional
    public void addNotes(Notes notes) {
        notesRepository.save(notes);
    }

    public Notes save(Notes note) {
        return notesRepository.save(note);
    }
        public Notes createNote(String title, String content, User user, LocalDate date, Module module) {
          
            Notes note = new Notes();
            note.setNotesTitle(title);
            note.setNotesContent(content);
            note.setNotesDate(date);
            note.setUser(user);
            note.setModule(module);


            return notesRepository.save(note);
    }
   
 
    public List<Notes> getAllNotes() {
        return notesRepository.findAll();
    }

    public Notes createNote(CreateNoteRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Module module = moduleRepository.findById(request.getModuleId()).orElseThrow(() -> new RuntimeException("Module not found"));
        Notes note = new Notes();
        note.setNotesTitle(request.getTitle());
        note.setNotesContent(request.getContent());
        note.setNotesDate(LocalDate.now());
        note.setUser(user);
        note.setModule(module);
        return notesRepository.save(note);
    }

    @Transactional
    public void deleteNoteById(Long notesId) {
        Notes note = notesRepository.findById(notesId).orElseThrow(() -> new RuntimeException("Note not found"));
        notesRepository.delete(note);
    }
    
    public List<Notes> getAllNotesByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return notesRepository.findByUser(user);
    }
}