package com.example.Spring_HW12.controllers;

import com.example.Spring_HW12.services.NoteServiceImpl;
import com.example.Spring_HW12.models.Note;
import com.example.Spring_HW12.services.FileGateway;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Рест контролёр
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {

    private final NoteServiceImpl noteService;

    private final FileGateway fileGateway;

    /**
     * Добавить заметку
     * @param note заметка
     * @return заметка
     */
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        fileGateway.writeToFile(note.getTitle() + ".txt", note.toString());
        return new ResponseEntity<>(noteService.create(note), HttpStatus.CREATED);
    }

    /**
     * Получение всех заметок
     *
     * @return список заметок
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }
    /**
     * Получить заметку по идентификатору
     *
     * @param id уникальный идентификатор заметки
     * @return заметка
     */
    @GetMapping("{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") long id) {
        return new ResponseEntity<>(noteService.getById(id), HttpStatus.OK);
    }

    /**
     * Обновление заметки
     * @param id  уникальный идентификатор нужной заметки
     * @param note заметка из body
     * @return обновленная заметка
     */
    @PutMapping("/{id}")
    public ResponseEntity<Note> editNote(@PathVariable Long id, @RequestBody Note note) {
        Optional<Note> updatedNote = noteService.editNote(id, note);
        return updatedNote.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Удаление заметки
     *
     * @param id уникальный идентификатор нужной заметки
     * @return возвращаем статус выполнения
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") long id) {
        noteService.delete(id);
        return ResponseEntity.ok().build();
    }
}