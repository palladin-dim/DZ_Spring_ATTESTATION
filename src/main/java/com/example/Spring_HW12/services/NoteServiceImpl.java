package com.example.Spring_HW12.services;

import org.springframework.stereotype.Service;
import com.example.Spring_HW12.models.Note;
import com.example.Spring_HW12.repositories.NoteRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    /**
     * Интерфейс взаимодействия с Базой Данных
     */
    private final NoteRepository noteRepository;

    /**
     * Получение всех заметок через БД
     *
     * @return список заметок
     */
    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    /**
     * Получение заметки по идентификатору в БД
     *
     * @param id уникальный идентификатор
     * @return заметка
     */
    @Override
    public Note getById(long id) {
        return noteRepository.findById(id).orElse(null);
    }

    /**
     * Создание заметки в БД
     *
     * @param note заметка из запроса
     * @return новая заметка
     */
    @Override
    public Note create(Note note) {
        return noteRepository.save(note);
    }

    /**
     * Обнорвление заметки в БД
     *
     * @param note заметка из запроса
     * @return обновленная заметка
     */
    @Override
    public Optional<Note> editNote(Long id, Note note) {
        Optional<Note> existingNote = noteRepository.findById(id);
        if (existingNote.isPresent()) {
            note.setId(id);
            return Optional.of(noteRepository.save(note));
        } else {
            return Optional.empty();
        }
    }
    /**
     * Удаление заметки из БД
     *
     * @param id уникальный идентификатор заметки
     */
    @Override
    public void delete(long id) {
        noteRepository.deleteById(id);
    }
}