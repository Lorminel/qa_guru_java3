package com.lorminel.data;

import com.lorminel.domain.Note;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface NoteRepository {

    List<Note> findAllNotesByUsername(String username);

    void save(Note note);

    class MockNoteRepository implements NoteRepository {

        private List<Note> store = new ArrayList<>(
                List.of(
                        new Note("dasha", "Погуляй с собачкой"),
                        new Note("dasha", "Сделай дз в qa guru!")
                )
        );

        @Override
        public List<Note> findAllNotesByUsername(String username) {
            if ("dasha".equals(username)) {
                return store;
            } else {
                return Collections.emptyList();
            }
        }

        @Override
        public void save(Note note) {
            if ("dasha".equals(note.username())) {
                store.add(note);
            }
        }
    }
}
