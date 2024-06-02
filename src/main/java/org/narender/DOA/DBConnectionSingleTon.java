package org.narender.DOA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBConnectionSingleTon {

    private static final Logger logger = Logger.getLogger(DBConnectionSingleTon.class.getName());
    private static Connection INSTANCE;

    private static final String url = "jdbc:mysql://127.0.0.1:3306/TechBlogs";

    private DBConnectionSingleTon(){
        try {
            INSTANCE = DriverManager.getConnection(url,"root","pass");
        } catch (SQLException e) {
            logger.info("Error occurred while connecting to the database: " + e.getMessage());

        }
    }

    public static Connection getConnection(){
        try {
            if(INSTANCE == null || INSTANCE.isClosed()){
                new DBConnectionSingleTon();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return INSTANCE;
    }
}
