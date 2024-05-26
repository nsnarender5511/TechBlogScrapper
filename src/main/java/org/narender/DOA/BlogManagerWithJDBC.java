package org.narender.DOA;

import org.narender.Objects.Blog;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BlogManagerWithJDBC {
    //private static String url = "jdbc:mysql://40.76.255.234:3306/TechBlogs";

    private static final Logger logger = Logger.getLogger(BlogManagerWithJDBC.class.getName());

    private static int blogid = 1;


    public static void insert(Blog blog) {
        try {
            // Get the database connection
            Connection con = DBConnectionSingleTon.getConnection();

            // Create a statement object
            //Statement stmt = con.createStatement();

            // Construct the SQL query with the blog details
            String sql = "INSERT INTO Blog (timeDate, title, url, company) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, blog.getTimeDate());
            pstmt.setString(2, blog.getTitle());
            pstmt.setString(3, blog.getUrl());
            pstmt.setString(4, blog.getCompany());

            // Execute the SQL query
            pstmt.executeUpdate();

            // Increment the blog ID
            blogid++;

            // Close the database connection
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Blog> fetchBlogsPaginated(int pageNo) {
        try{

            // Get the database connection
            Connection con = DBConnectionSingleTon.getConnection();
            // Create a statement object
            Statement stmt = con.createStatement();
            // Construct the SQL query with the blog details
            String sql = "SELECT * FROM Blog LIMIT 10 OFFSET " + (pageNo - 1) * 5;
            ResultSet rs = stmt.executeQuery(sql);
            List<Blog> blogList = new ArrayList<>();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setTimeDate(rs.getString("timeDate"));
                blog.setTitle(rs.getString("title"));
                blog.setUrl(rs.getString("url"));
                blog.setCompany(rs.getString("company"));
                blogList.add(blog);
            }
            // Close the database connection
            return blogList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int TotalBlogs() {
        try {
            // Get the database connection
            Connection con = DBConnectionSingleTon.getConnection();
            // Create a statement object
            Statement stmt = con.createStatement();
            // Construct the SQL query to count the number of blogs
            String sql = "SELECT count(*) as total FROM Blog";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("total"); // Directly retrieve the count
            }
            return 0; // Return 0 if no rows are found
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
