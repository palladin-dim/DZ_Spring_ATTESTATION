package com.example.Spring_HW12.services;

import com.example.Spring_HW12.models.Note;

import java.util.List;
import java.util.Optional;

/**
 * Для улучшения архитектуры и расширяемости кода, применен паттерн проектирования
 * **Service Layer** (Слой сервисов). Этот паттерн разделяет бизнес-логику
 * и операции с данными от контроллера, что сделает код более чистым и модульным.
 * Созданы интерфейс NoteService и  класс NoteServiceImpl
 */

public interface NoteService {
    List<Note> getAllNotes();
    Note getById(long id);
    Note create(Note note);
    Optional<Note> editNote(Long id, Note note);
    void delete(long id);
}