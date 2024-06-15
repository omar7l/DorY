package ch.noteshub.fhnw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ch.noteshub.fhnw.business.service.NotesService;
import ch.noteshub.fhnw.business.service.UserService;
import ch.noteshub.fhnw.business.service.ModuleService;
import ch.noteshub.fhnw.data.domain.Notes;
import ch.noteshub.fhnw.data.domain.User;
import ch.noteshub.fhnw.data.repository.UserRepository;
import ch.noteshub.fhnw.data.repository.ModuleRepository;
import ch.noteshub.fhnw.data.domain.CreateNoteRequest;
import ch.noteshub.fhnw.data.domain.Favorite;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    private NotesService notesService;
    private UserRepository userRepository;
    private ModuleRepository moduleRepository;

    @GetMapping
    public ResponseEntity<List<Notes>> getAllNotes() {
        List<Notes> notesList = notesService.getAllNotes();
        return ResponseEntity.ok(notesList);
    }

    @PostMapping("/{noteId}/favorite")
    public void favoriteNote(@PathVariable Long noteId, @RequestParam Long userId) {
        notesService.addFavorite(userId, noteId);
    }

    @DeleteMapping("/{noteId}/favorite")
    public void unfavoriteNote(@PathVariable Long noteId, @RequestParam Long userId) {
        notesService.removeFavorite(userId, noteId);
    }

    @GetMapping("/{noteId}/favorites/count")
    public int getFavoriteCount(@PathVariable Long noteId) {
        Notes notes = notesService.findById(noteId);
        return notes.getFavoriteCount();
    }

    @GetMapping("/favorites/{userId}")
    public List<Favorite> getUserFavorites(@PathVariable Long userId) {
        return notesService.findFavoritesByUser(userId);
    }

    // Add the missing method here
    @GetMapping("/search")
    public List<Notes> findNotesByTitle(@RequestParam String title) {
        return notesService.findNotesByTitleContaining(title);
    }


    @PostMapping
    public ResponseEntity<Notes> createNote(
            @RequestBody CreateNoteRequest request) {

        try {
            Notes note = notesService.createNote(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(note);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable String noteId) {
        try {
            noteId = noteId.split(":")[1].replace('}',' ').trim();
            notesService.deleteNoteById((Long.parseLong(noteId)));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notes>> getAllNotesByUserId(@PathVariable Long userId) {
        try {
            List<Notes> notesList = notesService.getAllNotesByUserId(userId);
            return ResponseEntity.ok(notesList);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
   