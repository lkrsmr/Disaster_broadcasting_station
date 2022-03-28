package Servlet;

import Bean.UserBean;
import Dao.UserDao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("login:" + name + " " + password);
        UserDao userDao = new UserDao();
        UserBean user = new UserBean();
        user = userDao.getUser(name);
        //如果有查询结果并且密码一致
            if(user != null && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", name);
                response.sendRedirect("index.jsp");
            }else {
                request.setAttribute("loginMsg", "用户名或密码错误");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
    }

}
