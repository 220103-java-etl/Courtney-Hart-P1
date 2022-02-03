package dev.hart.models;

/**
 * This concrete User class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>First Name</li>
 *     <li>Last Name</li>
 *     <li>Email</li>
 *     <li>Phone Number</li>
 *     <li>Address</li>
 * </ul>
 *
 */
public class User extends AbstractUser{
    private int id;
    private String username;
    private String password;
    private Role role;
    private double amountPending;
    private double totalAwarded;

    public User() {
        super();
    }

    public User(int id, String username, String password, Role role){
        super(id, username, password, role);

    }


    /*public User(int id, String username, String pass, Role role, BigDecimal amount_pending, BigDecimal total_amount_awarded) {
    }*/

   /* public User(int id, String username, String pass, String role, BigDecimal amount_pending, BigDecimal total_amount_awarded) {
    }*/

   /* public User(String username, String password, int role) {
    }*/



    /**
     * This includes the minimum parameters needed for the {@link /com.revature.models.AbstractUser} class.
     * If other fields are needed, please create additional constructors.
     */

    public User(int id, String username, String password, Role role, double amountPending, double totalAwarded) {
        super(id, username, password, role);
        //this.setRole(role);
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.amountPending = amountPending;
        this.totalAwarded = totalAwarded;
    }

   /* public User(String username, String password, int role) {
    }*/

    public User(int id, String username, String pass, String role, double amount_pending, double total_amount_awarded) {
    }

    public User(int id, String username, String pass, double amount_pending, double total_amount_awarded) {
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword(){
        return this.password;}

    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(Role role){
        //super.setRole(this.role);
        this.role = role;
    }

    public Role getRole(){
        return this.role;
        //return super.getRole();
    }

   /* public String getStringRole(){
        return super.getRole().toString();
    }
    public String setStringRole(){
        return super.setRole(Role.valueOf(role.toString()));
    }*/

  /* public String setStringRole(Role role){
        this.getRole().toString() = role.toString();
    }*/

    public double getTotalAwarded(){
        return this.totalAwarded;
    }

    public void setTotalAwarded(double totalAwarded){
         this.totalAwarded = totalAwarded;
    }


    public double getAmountPending() {
        return this.amountPending;
    }

    public void setAmountPending(double amountPending) {
        this.amountPending = amountPending;
    }


    /*public Role getUserRole(){
        return this.getRole();
    }*/

    public String toString() {
        return "User{" +
                "id = " + getId() + '\'' +
                "Username = " + getUsername() + '\'' +
                "Password = " + getPassword() + '\'' +
                "Role = " + getRole() + '\'' +
                "Amount Pending = " + getAmountPending() + '\'' +
                "Amount Awarded = " + getTotalAwarded() + '\'' +

                '}';

    }

    /*public String toString2() {
        return "User{" +
                "id = " + getId() + '\'' +
                "Username = " + getUsername() + '\'' +
                "Password = " + getPassword() + '\'' +
                "Role = " + getStringRole() + '\'' +
                "Amount Pending = " + getPending() + '\'' +
                "Amount Awarded = " + getTotalAwarded() + '\'' +

                '}';

    }*/

}
   /* public User(int id, String username, String password, String role) {
        super(id, username, password, Role.valueOf(role)); // convert role to a string

    public User(String username, String password) {

    }

    public User(int id, String username, String password, String role) {
    }
    }*/



    /*@Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }*/


   /* @Override
    public String toString() {
        return "User{" +
                //"id=" + id + '\''
                ", username='" + AbstractUser.getUsername() + '\'' +
                ", password='" + User.getPassword() + '\'' +
                ", role=" + role +
                '}';*/



    //@Override
   /* public Role /String/ getRole(){ // can you change the return type in method overriding?
        if (Role.EMPLOYEE.equals(this)) {
            return "Employee";
        } else if (Role.FINANCE_MANAGER.equals(this)) {
            return "Finance Manager";
        }

    }*/

