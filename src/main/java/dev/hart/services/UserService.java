package dev.hart.services;

import dev.hart.models.Reimbursement;
import dev.hart.models.User;
import dev.hart.repositories.ReimbursementDAO;
import dev.hart.repositories.UserDAO;

import java.util.List;
import java.util.Optional;

/**
 * The UserService should handle the processing and retrieval of Users for the ERS application.
 *
 * {@code getByUsername} is the only method required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create User</li>
 *     <li>Update User Information</li>
 *     <li>Get Users by ID</li>
 *     <li>Get Users by Email</li>
 *     <li>Get All Users</li>
 * </ul>
 */
public class UserService {
	private UserDAO userDAO = new UserDAO();

	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
	 * @return
	 */
	public Optional<User> getByUsername(String username) {
		Optional<User> u = UserDAO.getByUsername(username); // send the username to the database and obtain the user with the username
		//User user = new User();
		return u;

		/*if (u.isPresent()){
			//if (valueOf(u).equals(user.getUsername())) {
				return u;
			//}

		}*/

		//return Optional.empty();
	}

	public List<User> getAll(){
		return userDAO.getAll();
	}

	public Optional<User> getById(int id){
		 Optional<User> u = userDAO.getById(id);
		 return u;

	}

	public void updateUserAmount(User user){
		ReimbursementDAO rd = new ReimbursementDAO(); // need to match r with the name of the user passed in
		Reimbursement r = rd.getByAuthor(user.getUsername()).get();
		// math stuff goes here
		// add the total amount awarded in the users table to the amount in the reimbursements table
		int currentAmount = (int) user.getTotalAwarded() + r.getIntAmount();
		user.setTotalAwarded(currentAmount);
		userDAO.updateUserAmount(user);
		// figure out math stuff tomorrow
	}

}
