package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.repository.AccountRepository;
import at.elisabeth_tulla.walk_it_off.util.ValidationManager;

public class AccountService {

    AccountRepository accountRepo = new AccountRepository();
    ValidationManager valid = new ValidationManager();


    public String changeLastName(User user1, String newName){

        user1.setLastName(newName);

        accountRepo.changeLastname(user1);

        return user1.getLastName();
    }

    public String changeFirstName(User user1, String newName){

        user1.setFirstName(newName);

        accountRepo.changeFirstname(user1);

        return user1.getFirstName();
    }

    public String changeEmail(User user1, String newEmail){

        boolean emailAlreadyExists = valid.checkEmail(newEmail);

        if (emailAlreadyExists){
            return null;
        } else {

            user1.setEmail(newEmail);

            accountRepo.changeEmail(user1);

            return user1.getEmail();
        }
    }

    public boolean changePassword(User user1, String newPassword){

        boolean isValidPassword = valid.validatePassword(newPassword);

        boolean passwordChanged;

        if (!isValidPassword){
            passwordChanged = false;
        } else {

            user1.setPassword(newPassword);

            accountRepo.changePassword(user1);
            passwordChanged = true;
        }
        return passwordChanged;
    }

    public boolean deleteUser(String email){

        boolean deleted = accountRepo.deleteUserAccount(email);

        boolean accountDeleted;

        if (deleted){
            accountDeleted = true;
        } else {
            accountDeleted = false;
        }
        return accountDeleted;
    }

}
