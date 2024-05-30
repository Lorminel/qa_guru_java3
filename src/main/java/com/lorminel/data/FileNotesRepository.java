package com.lorminel.data;

import com.lorminel.domain.Note;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileNotesRepository implements NoteRepository {

    private final Path path;

    public FileNotesRepository(Path path) {
        this.path = path;
    }

    @Override
    public List<Note> findAllNotesByUsername(String username) {
        try (InputStream is = Files.newInputStream(path);
             CSVReader reader = new CSVReader(
                     new InputStreamReader(is)
             )) {
            List<String[]> allLines = reader.readAll();
            List<Note> userNotes = new ArrayList<>();

            for (String[] array : allLines) {
                if (array[0].equals(username)) {
                    userNotes.add(new Note(array[0], array[1]));
                }
            }

            if (userNotes != null) {
                return userNotes;
            } else {
                return Collections.emptyList();
            }

        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Note note) {
        if ("dasha".equals(note.username())) {
            try (FileWriter mFileWriter = new FileWriter(String.valueOf(path), true);
                 CSVWriter writer = new CSVWriter(mFileWriter)) {

                String[] newLine = new String[]{note.username(), note.text()};
                writer.writeNext(newLine, false);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
