package dev.hart.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hart.models.Reimbursement;
import dev.hart.models.Status;
import dev.hart.repositories.ReimbursementDAO;
import dev.hart.services.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReimbursementServlet extends HttpServlet {
 /*
    this servlet will get all reimbursements
    return a reimbursements
    create a new reimbursement
    delete a reimbursement

    _________________________________
    employees:
        1. GET: see their reimbursements
        2. POST: request a reimbursement

    Finance Managers:
        1. GET: see all reimbursements by status
        2. GET: see a reimbursements by user/author
        3. POST: update reimbursement status, user total awarded, (user pending: do i really need a user pending)
        // users.pending = total reimbursements.amount
  */
 ObjectMapper om = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // 1. get which radio button is clicked
        // 2. set the id associated with the button to status
        // 3. call getByStatus
        response.setContentType("text/html");
        Status status = Status.valueOf(request.getParameter("Status"));
        //List<Reimbursement> reimbursements = ReimbursementDAO.getByStatus(status);
        //response.getWriter().write(om.writeValueAsString(reimbursements));
       if (status == Status.PENDING ) {
            // Invoke action 1.
           List<Reimbursement> reimbursements = ReimbursementDAO.getByStatus(status);
           response.getWriter().write(om.writeValueAsString(reimbursements));
        }
        else if (status == Status.APPROVED) {
            // Invoke action 2.
           List<Reimbursement> reimbursements = ReimbursementDAO.getByStatus(status);
           response.getWriter().write(om.writeValueAsString(reimbursements));
        }
        else if (status == Status.DENIED) {
            // Invoke action 3.
           List<Reimbursement> reimbursements = ReimbursementDAO.getByStatus(status);
           response.getWriter().write(om.writeValueAsString(reimbursements));
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // get data from form
        // send to DB
       // Reimbursement r =  om.readValue(request.getReader(), Reimbursement.class);
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String eventType = request.getParameter("Event Type");
        String gradingFormat = request.getParameter("Grading Format");
        String grade = request.getParameter("grade");
        String date = request.getParameter("date");
        String location = request.getParameter("location");
        String amount = request.getParameter("RequestedAmount");
        String description = request.getParameter("Description");
        String cost = request.getParameter("cost");

       /* request.setAttribute(name, "name");
        request.setAttribute(username, "username");
        request.setAttribute(eventType, "Event Type");
        request.setAttribute("Grading Format", gradingFormat);
        request.setAttribute("grade", grade);
        request.setAttribute("date", date);
        request.setAttribute("location", location);
        request.setAttribute("RequestedAmount", amount);
        request.setAttribute("Description", description);
        request.setAttribute("cost", cost);*/

        Reimbursement r = new Reimbursement();
        r.setAuthorString(username);
        r.setResolver(null);
       // r.setAmount(Double.parseDouble(amount));
        r.setAmount(0);
        r.setName(name);
        r.setEventType(eventType);
        r.setGrade(grade);
        r.setGradingFormat(null);
        r.setDate(date);
        r.setLocation(location);
        r.setDescription(description);
        //r.setCost(Integer.parseInt(cost));
        r.setCost(0);
        r.setStatus(Status.PENDING);

        ReimbursementService rs = new ReimbursementService();
        rs.newReimbursment(r);

        // event type if statements


        //r = ReimbursementService.newReimbursment(r);
      //response.getWriter().write(om.writeValueAsString(r));

    }

    // method that get a reimbursement from the database
    // method that



}
