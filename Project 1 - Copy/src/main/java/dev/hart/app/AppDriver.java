package dev.hart.app;

import dev.hart.models.User;
import dev.hart.repositories.UserDAO;

public class AppDriver {
    public static void main(String[] args) {

      /* List<Reimbursement> reimbursements = ReimbursementDAO.getAllReimbursements();
       for(Reimbursement u : reimbursements){
           System.out.println(u.toString());
       }*/


        User u = UserDAO.getByUsername("usertroy").get();
        System.out.println(u);



      /*  Scanner sc = new Scanner(System.in);
       // new reimbursement test
        Reimbursement r = new Reimbursement();
       System.out.println("Amount");
       int amount = sc.nextInt();
       sc.nextLine();
       r.setAmount(amount);
       // username
        System.out.println("username");
        String username = sc.nextLine();
        r.setAuthorString(username);

        // cost
        System.out.println("cost");
        int cost = sc.nextInt();
        r.setCost(cost);
        // name
        sc.nextLine();
        System.out.println("name");
        String name = sc.nextLine();
        r.setName(name);
        System.out.println("grade");
        String grade = sc.nextLine();
        // grade
        r.setGrade(grade);
        System.out.println("date");
        String date = sc.nextLine();
        // date
        r.setDate(date);
        System.out.println("location");
        String location = sc.nextLine();
        // location
        r.setLocation(location);
        System.out.println("description");
        String description = sc.nextLine();
        r.setDescription(description);
        // description
        System.out.println("grading format");
        String gradingFormat = sc.nextLine();
        r.setGradingFormat(gradingFormat);
        // grading format
        System.out.println("Event Type");
        String eventType = sc.nextLine();
        r.setEventType(eventType);
        // event type
        ReimbursementService rs = new ReimbursementService();
        System.out.println(rs.newReimbursment(r));


        // test getByStatus and Reimburcement services
       /*System.out.println("enter 1: Pending, 2: Approved, 3: denied");
        ReimbursementService rs = new ReimbursementService();
       int input = sc.nextInt();
        if(input == 1){
            List<Reimbursement> r = rs.getReimbursementsByStatus(Status.PENDING);
            //r = ReimbursementDAO.getByStatus(Status.PENDING);
            System.out.println(r);
        }

        if(input == 2){
           List<Reimbursement> r = rs.getReimbursementsByStatus(Status.APPROVED);
           //r = ReimbursementDAO.getByStatus(Status.PENDING);
           System.out.println(r);
       }
        if(input == 3){
            List<Reimbursement> r = rs.getReimbursementsByStatus(Status.DENIED);
            //r = ReimbursementDAO.getByStatus(Status.PENDING);
            System.out.println(r);
        }*/


       /* Scanner sc = new Scanner(System.in);
        // create user test
        System.out.println("New User username?");
        String username = sc.nextLine();
        System.out.println("hit enter");
        sc.nextLine();
        System.out.println("New User password?");
        String password = sc.nextLine();
        System.out.println("hit enter");
        sc.nextLine();
        System.out.println("select 1. for employee or 2 for Finance manager");
        int role = sc.nextInt();
        User newUser = new User(username, password, role);
        newUser.setUsername(username);
        newUser.setPassword(password);
        if (role == 1) {
            newUser.setRole(Role.EMPLOYEE); // Enum.valueOf()
        }
        else if (role == 2) {
            newUser.setRole(Role.FINANCE_MANAGER);
        } else {
            System.out.println("Invalid input");
        }
        System.out.println(newUser);*/
        //newUser = AuthService.register(newUser);*/

        // System.out.println(newUser);

       /* UserService us = new UserService();

        //User service test
        String username;
        //User u = new User();
        System.out.println("input username");
        username = sc.nextLine();
        //u.setUsername(username);
        System.out.println(us.getByUsername(username));*/

        //login service test
       /* String password;
        String username;
        User u = new User();
        System.out.println("input the username");
        username = sc.nextLine();
        //u.setUsername(username);
        System.out.println("Press enter");
        sc.nextLine();
        System.out.println("input the password");
        password = sc.nextLine();
        //u.setPassword(password);
        //System.out.println(u.getUsername() + u.getPassword());
        //AuthService.login(u.getUsername(), u.getPassword());
        AuthService.login(username, password);*/



        /*
        String password = null;
        String username = null;
        System.out.print("\n1. Employee login \n2. Finance Manager login" +
                "\n3. Quit");
        int input = sc.nextInt();

        switch (input) {
            case 1:
                System.out.println("You chose to login as an employee. \nUsername: ");
                username = sc.nextLine();
                System.out.println("Password:");
                password = sc.nextLine();
                if(!AuthService.validateUser(username, password)) {
                    System.out.println("invalid username or password");
                    return;
                }
                System.out.println(password + "\nYou're in!");
                break;
            case 2:
                System.out.println("You chose to login as a manager. \nUsername: ");
                username = sc.nextLine();
                System.out.println(username + "\nPassword: ");
                password = sc.nextLine();
                System.out.println(password + "\nYou're in!");
                break;
            case 3:
                System.out.println("You chose to quit :( GOODBYE!!!");
                break;
            default:
                System.out.println("Invalid input >:^( integers 1-3 only!!!");
            }
   */
    }
}
