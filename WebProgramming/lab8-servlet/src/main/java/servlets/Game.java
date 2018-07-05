package servlets;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Game extends HttpServlet {
    private int[][] array=new int[3][3];
    private ArrayList<User> userList=new ArrayList<>();
    private boolean done=false;
    private Algorithm alg=new Algorithm(array);



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int us=0;
        User user= (User)request.getSession().getAttribute("user");

        if(request.getSession().getAttribute("user")== null){
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        if (!userList.contains(user) && userList.size() < 3) {
            userList.add(user);
        }

        if (userList.size()>=3){
            request.getRequestDispatcher("/wait.jsp").forward(request,response);
        }
        if (userList.get(0).equals(user)) {
            us = 1;
        } else if (userList.get(1).equals(user)) {
            us = 2;
        }
        System.out.println("entered into post");
        int line = Integer.parseInt(request.getParameter("line"));
        int column = Integer.parseInt(request.getParameter("column"));
        array[line][column]=us;

        if(!alg.win()&& done){

            request.getRequestDispatcher("/win.jsp").forward(request,response);
        }
        System.out.println(line+" "+column+" "+us);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user= (User)request.getSession().getAttribute("user");
        if(request.getSession().getAttribute("user")== null){
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        if (userList.size()>=3){
            request.getRequestDispatcher("/wait.jsp").forward(request,response);
        }
        if(alg.win()){
            request.getRequestDispatcher("/win.jsp").forward(request,response);
            done=true;
        }
        System.out.println("entered into get");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        StringBuilder str= new StringBuilder("<table id=\"myTable\" style=\"width: 25%;\">\n" + "<tbody>\n");
        for (int i=0; i<3; i++){
            str.append("<tr>\n");
            for (int j=0; j<3; j++){
                str.append("<td onclick=\"takeCell(").append(i).append(",").append(j).append(")\"><img src=\"");
                if (array[i][j] == 0) {
                    str.append("img/state_null.png");
                }else if (array[i][j] == 1){
                    str.append("img/state_x.png");
                }else if (array[i][j] == 2){
                    str.append("img/state_o.png");
                }
                str.append("\" ></td>\n");
            }

            str.append("</tr>\n");
        }
        str.append("</tbody>\n" +"</table>");
        out.print(str);

    }
}
