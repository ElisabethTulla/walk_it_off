public class AccountService {

    AccountRepository accountRepo = new AccountRepository();

    public void changeName(User user1, String newName /*todo get input from GUI*/){

        // todo profanity-check

        //change User-Object:
        user1.setLastName(newName);
        //change Name in DB:
        accountRepo.changeLastname(user1);
    }

    public void changeEmail(User user1, String newEmail /*todo get input from GUI*/){

        //todo checkEmail (Methode in UserRepository schon vorhanden!)

        //change User-Object:
        user1.setEmail(newEmail);
        //change Name in DB:
        accountRepo.changeEmail(user1);
    }

    public void changePassword(User user1, String newPassword/*todo get input from GUI*/){

        //todo securePassword

        //change User-Object:
        user1.setPassword(newPassword);
        //change Password in DB:
        accountRepo.changePassword(user1);
    }

    public void deleteUser(User user1/*todo get input from GUI*/){

        boolean deleted = accountRepo.deleteUserAccount(user1);

        if (deleted){
            // todo send message to GUI
            System.out.println("Account deleted");
            //todo end login session
        } else {
            //todo send message to GUI
            System.out.println("Account not found");
        }

    }

}
