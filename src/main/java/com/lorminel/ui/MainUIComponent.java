package com.lorminel.ui;

import com.lorminel.data.DataBaseNotesRepository;
import com.lorminel.domain.Note;
import com.lorminel.domain.User;
import com.lorminel.service.Session;

import javax.swing.*;
import java.util.List;

public class MainUIComponent implements UIComponent {

    private final DataBaseNotesRepository noteRepository;

    public MainUIComponent(DataBaseNotesRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Session render(Session session) {
        final User user = session.unwrap();
        List<Note> currentNotes = noteRepository.findAllNotesByUsername(user.username());
        renderNotes(currentNotes);
        final String newNoteText = JOptionPane.showInputDialog("New note: ");
        checkPressedButton(newNoteText);
        if (!newNoteText.isBlank()) {
            noteRepository.save(
                    new Note(user.username(),
                            newNoteText)
            );
        }

        int flag = getConfirmation();
        if (flag == 0) {
            return render(session);
        } else {
            System.exit(0);
        }
        return session;
    }

    private int getConfirmation() {
        return JOptionPane.showConfirmDialog(
                null,
                "Continue?",
                "Confirmation",
                JOptionPane.OK_CANCEL_OPTION
        );
    }

    private void renderNotes(List<Note> notes) {
        JOptionPane.showMessageDialog(
                null,
                notes,
                "Current notes: ",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void checkPressedButton(String resultButton) {
        if (resultButton == null) {
            System.exit(0);
        }
    }
}
