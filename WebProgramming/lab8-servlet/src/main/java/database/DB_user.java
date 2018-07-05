package database;


import java.sql.*;

public class DB_user {

    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost:3306/servlets";

    static final String USER="root";
    static final String PASS="";

    public boolean isValidUser(String userName,String pass){

        boolean isValid=false;

        Connection conn=null;
        Statement stmt=null;
        String sql="";

        try{
          Class.forName("com.mysql.jdbc.Driver");
          System.out.println("Connecting to database");
          conn= DriverManager.getConnection(DB_URL,USER,PASS);

          System.out.println("Creating statement");
          stmt=conn.createStatement();

          sql="SELECT * FROM users WHERE username = \"" +userName+"\" AND password =\"" + pass+"\"";

          System.out.println(sql);
          ResultSet rs= stmt.executeQuery(sql);

          if (rs.next()){
             isValid = true;
          }

          rs.close();
          stmt.close();
          conn.close();
        }catch (SQLException se){
            se.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Closing the connection!");

        return isValid;
    }
}
