package com.lorminel;

import com.lorminel.data.DataBaseNotesRepository;
import com.lorminel.data.DataBaseUserRepository;
import com.lorminel.service.Base64PasswordEncoder;
import com.lorminel.service.Session;
import com.lorminel.ui.LoginUIComponent;
import com.lorminel.ui.MainUIComponent;
import com.lorminel.ui.UIContainer;

public class Main {
    public static void main(String[] args) {


        new UIContainer(
                new LoginUIComponent(
                        new DataBaseUserRepository(),
                        new Base64PasswordEncoder()
                ),
                new MainUIComponent(
                        new DataBaseNotesRepository())
        ).render(
                new Session.EmptySession()
        );

    }

    /* Code for File Repositories
        new UIContainer(
                new LoginUIComponent(
                        new FileUserRepository(
                                Path.of("users.csv")
                        ),
                        new Base64PasswordEncoder()
                ),
                new MainUIComponent(
                        new FileNotesRepository(
                                Path.of("notes.csv"))
                )
        ).render(
                new Session.EmptySession()
        );
         */
}