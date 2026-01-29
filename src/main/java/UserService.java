public class UserService {

    UserRepository userRepo = new UserRepository();
    ValidationManager valid = new ValidationManager();

    public void registerUser(String firstName, String lastName, String email, String password,
                             Integer birthYear, Integer birthMonth, Integer birthDay, String gender
                            /*todo get user Data input from GUI*/){

        //check if email already exists in the DB:
        boolean emailAlreadyExists = valid.checkEmail(email);

        if (emailAlreadyExists){
            //todo show message in GUI
            System.out.println("This e-mail is already registered.");
            return;
        }

        //check if password is valid:
        boolean isValidPassword = valid.validatePassword(password);

        if (!isValidPassword){
            //todo show message in GUI
            System.out.println("The password must be between 8 - 20 characters long, \n" +
                    "must contain at least one digit, one lower case, \n" +
                    "one upper case character and one special character. \n" +
                    "No space between characters.");
            return;
        }

        //todo name profanity-check (Method in ValidatoinManager)

            //create User:
            User newUser = new User(firstName, lastName, email, password,
                    birthYear, birthMonth, birthDay, gender);

            //register User in DB:
            userRepo.registerNewUser(newUser);
            //todo show message in GUI
            System.out.println("Welcome " + newUser.getFirstName() + "!");

    }

    public User login(String email, String password/*todo get user Data input from GUI*/){

        //fetch user from DB:
        User user1 = userRepo.getUser(email);
        //TODO QUESTION: beim login muss ich anhand der email in der DB nach dem user suchen -> index auf email für schnelle Suche)?

        if (user1 == null){
            System.out.println("No user with this e-mail address was found.");
            return null;
        }

        boolean correctPassword = userRepo.checkPassword(user1, password);
        //Integer invalidPasswordCounter = 0;

        if(!correctPassword){
            //todo show message in GUI
            System.out.println("Invalid password!");
            return null;
        } else {
            //todo grant session token !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            System.out.println("Hello, " + user1.getFirstName() + "!");
            return user1;
        }

    }




}
