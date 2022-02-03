package dev.hart.services;

import dev.hart.exceptions.NewUserHasNonZeroIdException;
import dev.hart.exceptions.RegistrationUnsuccessfulException;
import dev.hart.exceptions.UsernameNotUniqueException;
import dev.hart.models.Role;
import dev.hart.models.User;
import dev.hart.repositories.UserDAO;

import java.util.Optional;

/**
 * The AuthService should handle login and registration for the ERS application.
 *
 * {@code login} and {@code register} are the minimum methods required; however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Retrieve Currently Logged-in User</li>
 *     <li>Change Password</li>
 *     <li>Logout</li>
 * </ul>
 */
public class AuthService {
    private static UserDAO userDAO = new UserDAO();
    //private User user = new User();

    public static Optional<User> login(String username, String password) {
        /** Optional<User> login(String username, String password)
         * <ul>
         *     <li>Needs to check for existing users with username/email provided.</li>
         *     <li>Must throw exception if user does not exist.</li>
         *     <li>Must compare password provided and stored password for that user.</li>
         *     <li>Should throw exception if the passwords do not match.</li>
         *     <li>Must return user object if the user logs in successfully.</li>
         * </ul>
         */
        // first we need the help of our UserDAO to retrieve user information by their username
        //Optional<User> u = userDAO.getByUsername(username);
        //Optional<User> p = userDAO.getByPassword(password);
        //String dbPass = (User) u.getPassword();
        User u = userDAO.getByUsername(username).get(); // the parameter passed into the method goes to DOA which goes to DB
        Optional<User> user1 = userDAO.getByUsername(username); // temporary variable to see if an optional is present
        User user = new User();
        //User u = userDAO.getByUsername(username);
        //Optional<User> ou = Optional.ofNullable(userDAO.getByUsername(username));
        try{
        // check if user exists
        if (user1.isPresent()){ // user1 is present needs to be true to continue
            //user.setUsername(username);
            if(username.equals(u.getUsername()) && password.equals(u.getPassword())) {
                user.setPassword(password);
                return Optional.of(user);
            }
        }}catch(Exception e){
            e.printStackTrace();
            System.out.println("No record of this username exist");
            }
            // check to make sure credentials match
           // if (Objects.equals(getByUsername(username), user.getUsername()) && password.equals(user.getPassword())) {
        return Optional.empty();
    }



    public static User register(User userToBeRegistered) {
        User user = new User();
        String username = userToBeRegistered.getUsername();
        Integer id = userToBeRegistered.getId();
        //UserDAO ud = new UserDAO();
        Optional<User> u = userDAO.getByUsername(username);
        Optional<User> uid = userDAO.getById(id);

        // check database to see if username exists
        try {
            if (u.isPresent() == false); // user needs to be false to continue
        } catch (UsernameNotUniqueException message){
            System.out.println(message); // if it does, throw an error.
        }
        try{
            //ud.create(userToBeRegistered); // userDao.add user to data base
            userToBeRegistered = userDAO.create(userToBeRegistered);
            System.out.println(userToBeRegistered);
            return userToBeRegistered;
        }catch(RegistrationUnsuccessfulException message1){
            message1.getLocalizedMessage();
            System.out.println("Registration unsuccessful");// throw exception if unsuccessful
        }try{
            uid = userDAO.getById(id);
        }catch(NewUserHasNonZeroIdException n){
            n.printStackTrace();
            n.getLocalizedMessage();
        }
        return null;
    }

    public static boolean isFinanceManager(String username){
        User u = userDAO.getByUsername(username).get();
        if (u.getRole() == Role.FINANCE_MANAGER){
            return true;
        }
        return false;
    }

}
/**
 * This is an example method signature for retrieving the currently logged-in user.
 * It leverages the Optional type which is a useful interface to handle the
 * possibility of a user being unavailable.
 *     public Optional<User> exampleRetrieveCurrentUser() {
 *         return Optional.empty();
 *     }
 */

/** public static User register(User userToBeRegistered)
 * <ul>
 *     <li>Should ensure that the username/email provided is unique.</li>
 *     <li>Must throw exception if the username/email is not unique.</li>
 *     <li>Should persist the user object upon successful registration.</li>
 *     <li>Must throw exception if registration is unsuccessful.</li>
 *     <li>Must return user object if the user registers successfully.</li>
 *     <li>Must throw exception if provided user has a non-zero ID</li>
 * </ul>
 *
 * Note: userToBeRegistered will have an id=0, additional fields may be null.
 * After registration, the id will be a positive integer.
 */