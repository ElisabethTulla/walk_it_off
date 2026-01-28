public class UserService {

    UserRepository userRepo = new UserRepository();

    public void registerUser(String firstName, String lastName, String email, String password,
                             Integer birthYear, Integer birthMonth, Integer birthDay, String gender
                            /*todo get user Data input from GUI*/){

        //todo name profanity-check (also in AccountService!!)

        //todo securePassword (also in AccountService!!)

        //check if email already exists in the DB:
        boolean emailAlreadyExists = userRepo.checkEmail(email);

        if (emailAlreadyExists){
            //todo show message in GUI
            System.out.println("This e-mail is already registered.");
        } else {
            //create User:
            User newUser = new User("Oliver", "Tulla", "ofaderbauer@gmail.com", "postgres",
                    1992, 7, 21, "male");

            //register User in DB:
            userRepo.registerNewUser(newUser);
        }
    }

    public void login(String email, String password/*todo get user Data input from GUI*/){

        //fetch user from DB:
        User user1 = userRepo.getUser(email);
        //TODO QUESTION: beim login muss ich anhand der email in der DB nach dem user suchen -> index auf email für schnelle Suche)?

        boolean validPassword = userRepo.validatePassword(user1, password);
        //Integer invalidPasswordCounter = 0;

        if(!validPassword ){
            //todo show message in GUI
            System.out.println("Invalid password! Please enter your password.");
        } else {
            //todo grant session token !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        }

    }


}
