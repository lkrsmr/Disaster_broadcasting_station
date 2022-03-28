package Dao;

import Bean.ArticleBean;
import Bean.InfoBean;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class InfoFavoriteDao {
    public Set<Integer> getInfoFavorite (String username){
        Set<Integer> favoriteSet = new HashSet<>();
        Connection conn =null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            String url = "jdbc:mysql://localhost:3306/website?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8";
            String dbuser = "root";
            String dbpass ="Mysql111";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,dbuser,dbpass);
            //String每次创建都会重新开辟一块内存空间，占用大量的内存。
            // 而stringBuffer是初始化创建一次，之后每次在后面追加内容，不需要重新开辟内存空间，提高代码执行效率。
            StringBuffer sql = new StringBuffer(200);
            sql.append("select info_id from infos_favorites ")
            .append("where user_name = ")
            .append("'").append(username).append("'");
            System.out.println(sql);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());

            while(rs.next()){
                favoriteSet.add(rs.getInt("info_id"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null) stmt.close();
            }catch (Exception e1) {

            }
            try {
                if (conn != null) conn.close();
            }catch (Exception e1) {
                e1.getMessage();
            }
            return favoriteSet;
        }
    }

    public boolean deleteInfoFavorite (int id) {
        Connection conn =null;
        Statement stmt = null;
        ResultSet rs = null;
        //删除操作后受影响的行数
        int success = 0;
        try {
            String url = "jdbc:mysql://localhost:3306/website?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8";
            String dbuser = "root";
            String dbpass ="Mysql111";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,dbuser,dbpass);
            //String每次创建都会重新开辟一块内存空间，占用大量的内存。
            // 而stringBuffer是初始化创建一次，之后每次在后面追加内容，不需要重新开辟内存空间，提高代码执行效率。
            StringBuffer sql = new StringBuffer(200);
            sql.append("delete from infos_favorites ")
                    .append("where info_id = ")
                    .append(id);
            System.out.println(sql);
            stmt = conn.createStatement();
            //获取删除操作后受影响的行数，不为0，则说明删除成功
            success = stmt.executeUpdate(String.valueOf(sql));


        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null) stmt.close();
            }catch (Exception e1) {

            }
            try {
                if (conn != null) conn.close();
            }catch (Exception e1) {
                e1.getMessage();
            }
            System.out.println("success:" + success) ;
            if(success != 0) {
                return true;
            }
            else {
                return false;
            }
        }

    }

    public boolean addInfoFavorite (String username ,int id) {
        Connection conn =null;
        Statement stmt = null;
        ResultSet rs = null;
        //插入操作后受影响的行数
        int success = 0;
        try {
            String url = "jdbc:mysql://localhost:3306/website?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8";
            String dbuser = "root";
            String dbpass ="Mysql111";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,dbuser,dbpass);
            //String每次创建都会重新开辟一块内存空间，占用大量的内存。
            // 而stringBuffer是初始化创建一次，之后每次在后面追加内容，不需要重新开辟内存空间，提高代码执行效率。
            StringBuffer sql = new StringBuffer(200);
            sql.append("insert into infos_favorites value (")
                    .append("'").append(username).append("'")
                    .append(",")
                    .append(id).append(")");
            System.out.println(sql);
            stmt = conn.createStatement();
            //获取删除操作后受影响的行数，不为0，则说明删除成功
            success = stmt.executeUpdate(String.valueOf(sql));


        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null) stmt.close();
            }catch (Exception e1) {

            }
            try {
                if (conn != null) conn.close();
            }catch (Exception e1) {
                e1.getMessage();
            }
            System.out.println("success:" + success) ;
            if(success != 0) {
                return true;
            }
            else {
                return false;
            }
        }
    }
}
