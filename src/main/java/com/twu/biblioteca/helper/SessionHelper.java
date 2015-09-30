package com.twu.biblioteca.helper;


import com.twu.biblioteca.domain.model.User;
import com.twu.biblioteca.domain.repo.UserRepo;

public class SessionHelper {

    public static User login(InteractionHelper interactionHelper, UserRepo userRepo) {
        User currentUser = null;
        while (currentUser == null) {
            String userId = interactionHelper.readUserInputWithPrompt("Please input library number: ");
            String password = interactionHelper.readUserInputWithPrompt("Please input password: ");
            if (userRepo.verifyLoginUser(userId, password)) {
                currentUser = userRepo.findUser(userId);
            }
            if (currentUser == null) {
                interactionHelper.promptMessage("Sorry, maybe the library number or password error!\n");
            }
        }

        interactionHelper.promptMessage("Login Success!\n");
        interactionHelper.promptMessage("Profile:" + currentUser.toString());
        return currentUser;
    }
}
