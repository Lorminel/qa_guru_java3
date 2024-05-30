package com.lorminel.ui;

import com.lorminel.data.DataBaseUserRepository;
import com.lorminel.domain.User;
import com.lorminel.service.PasswordEncoder;
import com.lorminel.service.Session;
import com.lorminel.service.UserSession;

import javax.swing.*;
import java.util.Optional;

public class LoginUIComponent implements UIComponent {
    private final DataBaseUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginUIComponent(DataBaseUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Session render(Session session) {
        final String username = JOptionPane.showInputDialog(
                "username: "
        );
        checkPressedButton(username);
        final String password = JOptionPane.showInputDialog(
                "password: "
        );
        checkPressedButton(password);

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            showError();
            return render(session);
        }
        User fromRepository = optionalUser.get();
        if (!fromRepository.isPasswordEqual(passwordEncoder.encode(password))) {
            showError();
            return render(session);
        }
        return new UserSession(fromRepository);
    }

    public void checkPressedButton(String resultButton) {
        if (resultButton == null) {
            System.exit(0);
        }
    }

    private void showError() {
        JOptionPane.showMessageDialog(
                null,
                "Bad credentials!",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
