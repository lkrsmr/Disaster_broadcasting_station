package Dao;

import Bean.ArticleBean;
import Bean.InfoBean;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao {

    public List<ArticleBean> getArticles () {
        //输出表中所有数据
        List<ArticleBean> list = new ArrayList<>();
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
            sql.append("select * from articles");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());

            while(rs.next()){
                ArticleBean article = new ArticleBean();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setHot(rs.getInt("hot"));
                article.setUrl(rs.getString("url"));
                list.add(article);
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
            return list;
        }

    }

    public List<ArticleBean> getLeastArticles (int limit) {
        List<ArticleBean> list = new ArrayList<>();
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
            sql.append("select * from articles")
                    .append(" ").append("order by id ").append("desc ")
                    .append("limit ").append(limit);
            System.out.println(sql);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());

            while(rs.next()){
                ArticleBean article = new ArticleBean();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setHot(rs.getInt("hot"));
                article.setUrl(rs.getString("url"));
                list.add(article);
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
            return list;
        }

    }

    public ArticleBean getOneArticle (int id) {
        ArticleBean article = new ArticleBean();
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
            sql.append("select * from articles where id = ")
                    .append(id);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());

            while(rs.next()){
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setHot(rs.getInt("hot"));
                article.setUrl(rs.getString("url"));
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
            return article;
        }
    }

    public List<ArticleBean> getArticleRank (int limit) {
        List<ArticleBean> list = new ArrayList<>();
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
            sql.append("select * from articles")
                    .append(" ").append("order by hot ").append("desc ")
                    .append("limit ").append(limit);
            System.out.println(sql);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());

            while(rs.next()){
                ArticleBean article = new ArticleBean();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setHot(rs.getInt("hot"));
                article.setUrl(rs.getString("url"));
                list.add(article);
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
            return list;
        }
    }

    public boolean addHot (int id) {
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
            sql.append("update articles set hot = hot+1 where id = ")
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


}
