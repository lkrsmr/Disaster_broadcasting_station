package Dao;

import Bean.InfoBean;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class InfoDao {
    //输出表中所有数据
    public List<InfoBean> getInfos () {
        List<InfoBean> list = new ArrayList<>();
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
            sql.append("select * from earthquake_infos");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());

            while(rs.next()){
                InfoBean info = new InfoBean();
                info.setId(rs.getInt("id"));
                info.setPosition(rs.getString("position"));
                info.setMagnitude(rs.getFloat("magnitude"));
                //对数据库中的datetime类型转换成String类型
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Timestamp timestamp = rs.getTimestamp("date_time");
                String dateTime = sdf.format(timestamp);
                info.setDateTime(dateTime);
                String date = rs.getDate("date_time").toString();
                info.setDate(date);

                info.setLongitude(rs.getFloat("longitude"));
                info.setLatitude(rs.getFloat("latitude"));
                info.setDepth(rs.getInt("depth"));
                info.setUrl(rs.getString("url"));
                list.add(info);
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

    //根据id倒序输出limit个数据
    public List<InfoBean> getLeastInfos (int limit) {
        List<InfoBean> list = new ArrayList<>();
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
            //由于爬取数据时新插入的数据一定是最新的，所以id大的数据时间更近，因此根据id可以获得最新的数据
            sql.append("select * from earthquake_infos")
            .append(" ").append("order by id ").append("desc ")
            .append("limit ").append(limit);
            System.out.println(sql);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());

            while(rs.next()){
                InfoBean info = new InfoBean();
                info.setId(rs.getInt("id"));
                info.setPosition(rs.getString("position"));
                info.setMagnitude(rs.getFloat("magnitude"));
                //对数据库中的datetime类型转换成String类型
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Timestamp timestamp = rs.getTimestamp("date_time");
                String dateTime = sdf.format(timestamp);
                info.setDateTime(dateTime);
                String date = rs.getDate("date_time").toString();
                info.setDate(date);

                info.setLongitude(rs.getFloat("longitude"));
                info.setLatitude(rs.getFloat("latitude"));
                info.setDepth(rs.getInt("depth"));
                info.setUrl(rs.getString("url"));
                list.add(info);
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

    public InfoBean getOneInfo (int id) {
        InfoBean info = new InfoBean();
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
            sql.append("select * from earthquake_infos where id = ")
            .append(id);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());

            while(rs.next()){

                info.setId(rs.getInt("id"));
                info.setPosition(rs.getString("position"));
                info.setMagnitude(rs.getFloat("magnitude"));
                //对数据库中的datetime类型转换成String类型
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Timestamp timestamp = rs.getTimestamp("date_time");
                String dateTime = sdf.format(timestamp);
                info.setDateTime(dateTime);
                String date = rs.getDate("date_time").toString();
                info.setDate(date);

                info.setLongitude(rs.getFloat("longitude"));
                info.setLatitude(rs.getFloat("latitude"));
                info.setDepth(rs.getInt("depth"));
                info.setUrl(rs.getString("url"));

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
            return info;
        }
    }

}
