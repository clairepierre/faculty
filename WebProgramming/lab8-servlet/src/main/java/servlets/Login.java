package servlets;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User userObj=new User();
        request.setAttribute("username",request.getParameter("loginname"));


        if (userObj.isValidUserCredentials(request.getParameter("loginname"),request.getParameter("password"))){
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            httpRequest.getSession().setAttribute("user",userObj);
            request.getRequestDispatcher("/game.jsp").forward(request,response);
        }else{
         request.setAttribute("errorMessage","Invalid user or password. Try again.");
         request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("asassdasfasdas");
    }
}
