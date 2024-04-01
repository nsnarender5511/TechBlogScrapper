package org.narender.DOA;

import org.narender.Objects.Blog;

import javax.swing.plaf.nimbus.State;
import java.sql.Statement;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BlogManagerWithJDBC {
    //private static String url = "jdbc:mysql://40.76.255.234:3306/TechBlogs";

    private static final Logger logger = Logger.getLogger(BlogManagerWithJDBC.class.getName());

    private static int blogid = 1;

    public static void insert(Blog blog){
        try {


           // Connection con = DriverManager.getConnection(url,"narender","pass");
            Connection con = DBConnectionSingleTon.getConnection();
            //Statement
            Statement smtt = con.createStatement();
            smtt.executeUpdate("insert into Blog values("+blogid+",'"+blog.getTimeDate()+"','"+blog.getTitle()+"','"+blog.getUrl()+"','"+blog.getCompany()+"')");
            blogid++;
            con.close();


        } catch (SQLException e) {
            logger.info("Error occurred while connecting to the database: " + e.getMessage());
        }


        //Statement


    }
}
