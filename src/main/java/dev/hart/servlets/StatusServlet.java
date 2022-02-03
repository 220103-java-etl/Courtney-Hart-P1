package dev.hart.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hart.models.Reimbursement;
import dev.hart.models.Status;
import dev.hart.models.User;
import dev.hart.repositories.ReimbursementDAO;
import dev.hart.services.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StatusServlet extends HttpServlet {
    ObjectMapper om = new ObjectMapper();

    /*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Reimbursement unprocessedReimbursement, Status finalStatus, User resolver

        String author = request.getParameter("author");
        String resolver = request.getParameter("resolver");
        Status status = Status.valueOf(request.getParameter("Status"));


        Reimbursement r = ReimbursementDAO.getByAuthor(author).get();
        //Reimbursement r = r.setAuthor();
        User user = new User();
        user.setUsername(resolver);
       // Reimbursement r = om.readValue(request.getReader(), Reimbursement.class);
        ReimbursementService.process(r, status, user);

    }
}
