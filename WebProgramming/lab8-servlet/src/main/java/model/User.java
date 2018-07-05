package model;


import database.DB_user;

public class User {
    public boolean isValidUserCredentials(String sUserName, String sPass){

        DB_user db_user_obj= new DB_user();


        return db_user_obj.isValidUser(sUserName,sPass);

    }
}
