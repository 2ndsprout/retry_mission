package com.example.textBoard.note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public List<Note> getList () {
        return this.noteRepository.findAllByOrderByCreateDateDesc();
    }
    public Note create () {
        Note note = new Note();
        note.setTitle("newTitle...");
        note.setContent("");
        note.setCreateDate(LocalDateTime.now());

        return this.noteRepository.save(note);
    }
    public Note getNote (Long id) {
        Note note = this.noteRepository.findById(id).get();

        return note;

    }
}
