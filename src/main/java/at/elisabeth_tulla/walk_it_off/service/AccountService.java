package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.repository.AccountRepository;
import at.elisabeth_tulla.walk_it_off.util.ValidationManager;

public class AccountService {

    AccountRepository accountRepo = new AccountRepository();
    ValidationManager valid = new ValidationManager();


    public void changeLastName(User user1, String newName){

        // todo profanity-check (Method in ValidationManager)

        //change at.elisabeth_tulla.walk_it_off.model.User-Object:
        user1.setLastName(newName);
        //change Name in DB:
        accountRepo.changeLastname(user1);
        System.out.println("Lastname changed to " + newName);
    }

    public void changeFirstName(User user1, String newName){

        // todo profanity-check (Method in ValidationManager)

        //change at.elisabeth_tulla.walk_it_off.model.User-Object:
        user1.setFirstName(newName);
        //change Name in DB:
        accountRepo.changeFirstname(user1);
        System.out.println("Firstname changed to " + newName);
    }

    public void changeEmail(User user1, String newEmail){

        //check, if email already exists in db:
        boolean emailAlreadyExists = valid.checkEmail(newEmail);

        if (emailAlreadyExists){
            System.out.println("This e-mail is already registered.");
        } else {
            //change at.elisabeth_tulla.walk_it_off.model.User-Object:
            user1.setEmail(newEmail);
            //change Name in DB:
            accountRepo.changeEmail(user1);
            System.out.println("E-mail changed.");
        }
    }

    public void changePassword(User user1, String newPassword){

        //check if password is valid:
        boolean isValidPassword = valid.validatePassword(newPassword);

        if (!isValidPassword){
            System.out.println("The password must be between 8 - 20 characters long, \n" +
                    "must contain at least one digit, one lower case, \n" +
                    "one upper case character and one special character. \n" +
                    "No spaces between characters.");
            return;}

        //change at.elisabeth_tulla.walk_it_off.model.User-Object:
        user1.setPassword(newPassword);
        //change Password in DB:
        accountRepo.changePassword(user1);
        System.out.println("Password changed");
    }

    public void deleteUser(String email){

        boolean deleted = accountRepo.deleteUserAccount(email);

        if (deleted){
            System.out.println("Account deleted");
        } else {
            System.out.println("Account not found");
        }

    }

}
