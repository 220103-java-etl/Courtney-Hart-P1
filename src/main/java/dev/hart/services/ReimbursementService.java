package dev.hart.services;

import dev.hart.models.Reimbursement;
import dev.hart.models.Status;
import dev.hart.models.User;
import dev.hart.repositories.ReimbursementDAO;
import dev.hart.repositories.UserDAO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The ReimbursementService should handle the submission, processing,
 * and retrieval of Reimbursements for the ERS application.
 *
 * {@code process} and {@code getReimbursementsByStatus} are the minimum methods required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create Reimbursement</li>
 *     <li>Update Reimbursement</li>
 *     <li>Get Reimbursements by ID</li>
 *     <li>Get Reimbursements by Author</li>
 *     <li>Get Reimbursements by Resolver</li>
 *     <li>Get All Reimbursements</li>
 * </ul>
 */
public class ReimbursementService {
    private static ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
    private UserDAO userDAO = new UserDAO();
        /**
         * <ul>
         *     <li>Should ensure that the user is logged in as a Finance Manager</li>
         *     <li>Must throw exception if user is not logged in as a Finance Manager</li>
         *     <li>Should ensure that the reimbursement request exists</li>
         *     <li>Must throw exception if the reimbursement request is not found</li>
         *     <li>Should persist the updated reimbursement status with resolver information</li>
         *     <li>Must throw exception if persistence is unsuccessful</li>
         * </ul>
         *
         * Note: unprocessedReimbursement will have a status of PENDING, a non-zero ID and amount, and a non-null Author.
         * The Resolver should be null. Additional fields may be null.
         * After processing, the reimbursement will have its status changed to either APPROVED or DENIED.
         */

    public static Reimbursement process(Reimbursement unprocessedReimbursement, Status finalStatus, User resolver) {
        User u = new User();
        //u = unprocessedReimbursement.getAuthor(); // get the author and set that user object to user

        // or

        u = UserDAO.getByUsername(unprocessedReimbursement.getAuthorString()).get();
        UserService us = new UserService();
        //int id = u.getId();
        //List<Reimbursement> rd = ReimbursementDAO.getByStatus(finalStatus);
        //Optional<Reimbursement> rid = Optional.of(ReimbursementDAO.getById(id).get()

        /*if(u.getRole() != Role.FINANCE_MANAGER){
            System.out.println("not logged in as a finance manager");
            System.out.println(u.getRole());
            return null;
        }*/
        if (unprocessedReimbursement.getStatus() != Status.PENDING ){
            System.out.println("reimbursement request does not exist");
            return null;
        }
        // call reimbursement DAO update
        // update the unprocessed reimbursement's: role = ?, resolver = ?, where id = ?"
        // get role from user input
        // get resolver from user input
        if(finalStatus == Status.APPROVED){
            unprocessedReimbursement.setStatus(Status.APPROVED);
            unprocessedReimbursement.setResolver(resolver);
            //u.setTotalAwarded(unprocessedReimbursement.getAmount());
            //us.updateUserAmount(u);
            reimbursementDAO.update(unprocessedReimbursement);
        }
        if(finalStatus == Status.DENIED){
            unprocessedReimbursement.setStatus(Status.DENIED);
            unprocessedReimbursement.setResolver(resolver);
            reimbursementDAO.update(unprocessedReimbursement);
        }

        Reimbursement processedReimbursement;
        processedReimbursement = unprocessedReimbursement;
        return processedReimbursement;
    }

    /**
     * Should retrieve all reimbursements with the correct status.
     */
    public List<Reimbursement> getReimbursementsByStatus(Status status) {
        List<Reimbursement> rd = ReimbursementDAO.getByStatus(status);
        if (status == Status.PENDING) {
            return rd;
        }
        else if (status == Status.APPROVED) {
            return rd;
        }

        else if (status == Status.DENIED) {
            return rd;
        }

        return Collections.emptyList();
    }
    // create a method that updates users table after employee accepts or denies their reimbursement

    // create a method that calculates a users max reimbursement amount
    public double calculateMaxReimbursement(String author, int id){
        // get a reimbursement request by author
        Reimbursement r = reimbursementDAO.getById(id).get();
        User u = userDAO.getByUsername(author).get();
        double availabeReimbursement = 1000 - u.getTotalAwarded(); //
        //double totalPending = u.getAmountPending();
        int cost = r.getCost(); // cost of event
        //double amount = r.getAmount(); // requested amount
        String eventType = r.getEventType();
        //BigDecimal projectedBigMax = BigDecimal.valueOf(0); // (event type * cost) / 100
        double projectedMax = 0;
        //BigDecimal thousand = BigDecimal.valueOf(1000);

        int university = 80;
        int seminar = 60;
        int certPrep = 75;
        int techT = 90;
        int cert = 100;
        int other = 30;


        if (eventType == "University"){
            projectedMax = (cost * university)/100;
            if(projectedMax > 1000){
                projectedMax = 1000;
            }
        }
        if (eventType == "Certification Prep"){
            projectedMax = (cost * certPrep)/100;
            if(projectedMax > 1000){
                projectedMax = 1000;

            }
        }
        if (eventType == "Seminar"){
            projectedMax = (cost * seminar)/100;
            if(projectedMax > 1000){
                projectedMax = 1000;
            }
        }
        if (eventType == "Technical Training"){
            projectedMax = (cost * techT)/100;
            if(projectedMax > 1000){
                projectedMax = 1000;
            }
        }

        if(eventType == "Certification"){
            projectedMax = (cost * cert)/100;
            if(projectedMax > 1000){
                projectedMax = 1000;
            }
        }

        if(eventType == "Other"){
            projectedMax = (cost * other)/100;
            if(projectedMax > 1000){

                projectedMax = 1000;
            }
        }

        // compare the projectedMax to the reimbursementAvailable
        if(projectedMax > availabeReimbursement){
            projectedMax = availabeReimbursement;

        }
        return projectedMax;

    }
    // method to create new reimbursement
    public static Reimbursement newReimbursment(Reimbursement reimbursement){
        return ReimbursementDAO.create(reimbursement);
    }

    public Optional<Reimbursement> getById(int id){
        return ReimbursementDAO.getById(id);
    }
        // update available reimbursement

}
