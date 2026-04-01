package at.elisabeth_tulla.walk_it_off.service;

import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.repository.ManageAccountRepository;
import at.elisabeth_tulla.walk_it_off.util.ValidationManager;

/***
 * This service hands over User changes to the ManageAccountRepository.
 */

public class ManageAccountService {

    ManageAccountRepository accountRepo = new ManageAccountRepository();
    ValidationManager valid = new ValidationManager();

    /***
     * This method changes the lastName attribute of the User-Object
     * and hands over the updated User-Object to the AccountManagementRepository.
     * @param user1 User Object
     * @param newName new String for lastName attribute of User Object
     * @return lastName of User-Object as String
     */
    public String changeLastName(User user1, String newName){

        user1.setLastName(newName);

        accountRepo.changeLastname(user1);

        return user1.getLastName();
    }

    /***
     * This method changes the firstName attribute of the User-Object
     * and hands over the updated User-Object to the AccountManagementRepository.
     * @param user1 User Object
     * @param newName new String for firstName attribute of User Object
     * @return firstName of User-Object as String
     */
    public String changeFirstName(User user1, String newName){

        user1.setFirstName(newName);

        accountRepo.changeFirstname(user1);

        return user1.getFirstName();
    }

    /***
     * This method changes the email attribute of the User-Object
     * and hands over the updated User-Object to the AccountManagementRepository.
     * @param user1 User Object
     * @param newEmail new String for email attribute of User Object
     * @return email of User-Object as String
     */
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

    /***
     * This method checks if newPassword is valid via the ValidationManager.
     * If this is true, it changes the password attribute of the User-Object
     * and hands over the updated User-Object to the AccountManagementRepository.
     * @param user1 User Object
     * @param newPassword new String for password attribute of User Object
     * @return boolean passwordChanged as true or false
     */
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

    /***
     * This method hands over the email to the AccountRepository and checks if the deletion was successful.
     * @param email String attribute of a User Object
     * @return boolean accountDeleted as true or false
     */
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
