package com.lorminel.data;

import com.lorminel.domain.Note;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataBaseNotesRepository implements NoteRepository {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(
            DataSourceProvider.INSTANCE.getDataSource()
    );

    @Override
    public List<Note> findAllNotesByUsername(String username) {

        try {
            List<String> notes = jdbcTemplate.queryForList("select text from notes where username = ?",
                    new String[]{username}, String.class);

            List<Note> userNotes = new ArrayList<>();

            for (String note : notes) {
                userNotes.add(new Note(username, note));
            }
            return userNotes;
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public void save(Note note) {
        jdbcTemplate.update("insert into notes (id, username, text) values (gen_random_uuid(), ?, ?)",
                note.username(), note.text());
    }
}
