package Dao;

import Bean.UserBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDao {

    public UserBean getUser (String username) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            String url = "jdbc:mysql://localhost:3306/website?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8";
            String dbuser = "root";
            String dbpass = "Mysql111";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, dbuser, dbpass);
            //String每次创建都会重新开辟一块内存空间，占用大量的内存。
            // 而stringBuffer是初始化创建一次，之后每次在后面追加内容，不需要重新开辟内存空间，提高代码执行效率。
            StringBuffer sql = new StringBuffer(200);
            sql.append("select * from users where name='" + username + "'");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());
            //有查询结果就返回bean，没有就返回null，再用servlet判断是否返回了null
            if (rs.next()) {
                UserBean user = new UserBean();
                String userName = rs.getString("name");
                String password = rs.getString("password");
                user.setUserName(userName);
                user.setPassword(password);
                return user;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                if (stmt != null) stmt.close();
            } catch (Exception e1) {

            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e1) {
                e1.getMessage();
            }
        }
    }

    public boolean saveUser (UserBean user) {
        Connection conn =null;
        Statement stmt = null;
        try {
            String url = "jdbc:mysql://localhost:3306/website?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8";
            String dbuser = "root";
            String dbpass ="Mysql111";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,dbuser,dbpass);
            //String每次创建都会重新开辟一块内存空间，占用大量的内存。
            // 而stringBuffer是初始化创建一次，之后每次在后面追加内容，不需要重新开辟内存空间，提高代码执行效率。
            StringBuffer sql = new StringBuffer(200);
            sql.append(" INSERT INTO users VALUES")
                    .append("(")
                    .append("'").append(user.getUserName()).append("'")
                    .append(",")
                    .append("'").append(user.getPassword()).append("'")
                    .append(")");
            System.out.println(sql);
            stmt = conn.createStatement();
            System.out.println(stmt.execute(sql.toString()));
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
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
        }
    }
}

