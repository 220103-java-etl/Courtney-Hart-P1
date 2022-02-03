package dev.hart.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hart.models.User;
import dev.hart.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserServlet extends HttpServlet {
    // everything that is ends in /users is mapped to this servlet
    /**
     * we're building an API
     * we want to create RESTful endpoints
     * get /users -> responds with all users (getAll()
     * get /users/{id} responds with a single user
     *
     * POST /users -> create a new user on the server
     * PUT /users/{id} -> delete author
     */
    // test endpoint
    UserService us = new UserService();

    // ObjectMapper
    ObjectMapper om = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //StringBuffer urlString = request.getRequestURL();
        StringBuilder uriString = new StringBuilder(request.getRequestURI());
        //System.out.println("urL" + urlString);
        System.out.println("uri" + uriString);

        uriString.replace(0, request.getContextPath().length() + 1, "");
        System.out.println(uriString);

        int userID = 0;
        if (uriString.indexOf("/") != -1){
            userID = Integer.parseInt(uriString.replace(0, uriString.indexOf("/") + 1, "").toString());
        }

        System.out.println("Path now is: " + uriString);
        if(userID == 0){
            //response.getWriter().write("Test Worked!");
            // send back the list of users that exist in the db
            // use userDAO to do this
            List<User> users = us.getAll();

            // write the list of users to the response
            // Doesn't Work: response.getWriter().write(users);
            // We need a way to map/marshal this list of user objects from its Java form to a string
            // of data, so we can send it to the front end to be interpreted by the browser
            // TO DO THIS: WE USE AN EXTERNAL LIBRARY CALLED JAXON
            // Jackson: packages java object into json to be sent on the internet

            // String responseBody = om.writeValueAsString(users);
            // response.getWriter().write(responseBody);


            response.getWriter().write(om.writeValueAsString(users));
        } else {
            System.out.println("User Id: " + userID);
            Optional<User> u = us.getById(userID);
            response.getWriter().write(om.writeValueAsString(u));
        }


    }


}
