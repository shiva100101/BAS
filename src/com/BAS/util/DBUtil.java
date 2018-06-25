package com.BAS.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
           return connection;
        else {
            try {
                Properties prop = new Properties();
                InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("/db.properties");
                prop.load(inputStream);
                
                
                
                String driver = prop.getProperty("driver");// "com.mysql.jdbc.Driver";
                String url = prop.getProperty("url");//jdbc:mysql://localhost:3306/FMS_DB
                String user = prop.getProperty("user");//root
                String password = prop.getProperty("password");//root
                System.out.println("DBUTil | getConnection| the values are :" + driver + " " +url+ " " +user + " " + password );
                Class.forName(driver);//com.mysql.jdbc.Driver  
                connection = DriverManager.getConnection(url, user, password);//true or false
                
                
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
}