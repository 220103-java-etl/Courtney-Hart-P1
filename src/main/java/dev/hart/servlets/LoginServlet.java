package dev.hart.servlets;

import dev.hart.models.Role;
import dev.hart.models.User;
import dev.hart.services.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

//@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // take user input
        //resp.setContentType("text/html");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String role = req.getParameter("Role");
        AuthService as = new AuthService();
        Optional<User> u = as.login(username, password); // set u to the object returned from logn
        if (u.isPresent()) {
            if (Objects.equals(role, String.valueOf(Role.EMPLOYEE))){
                // create a service method that checks to see if a user is a finance_manager
                //HttpSession session = req.getSession();
                resp.sendRedirect("EmployeeDash.html");
            }
            else if(Objects.equals(role, String.valueOf(Role.FINANCE_MANAGER)) && AuthService.isFinanceManager(username)){
                resp.sendRedirect("FinanceManagerDash.html");
            }

        }
        else {
            resp.sendRedirect("InvalidInput.html");

        }
    }

}
