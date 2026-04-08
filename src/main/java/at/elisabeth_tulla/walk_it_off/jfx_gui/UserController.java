package at.elisabeth_tulla.walk_it_off.jfx_gui;

import at.elisabeth_tulla.walk_it_off.model.User;

/**
 * UserController acts as the parent to AccountController, ActivityController, ChallengeController
 * and ManageAccountController. It provides the User currentUser for its extending Controllers.
 */

public class UserController {

    protected User currentUser;

    public void initData(User currentUser) {
        this.currentUser = currentUser;
    }
}
