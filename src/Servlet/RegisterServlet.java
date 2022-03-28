package Servlet;


import Bean.UserBean;
import Dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private boolean isNull(String s) {
        return (s == null || "".equals(s));
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        request.setCharacterEncoding("utf-8");
        String userName = request.getParameter("registerName");
        String password = request.getParameter("registerPassword");
        String password_check = request.getParameter("registerRePassword");

        StringBuffer errMsg = new StringBuffer(200);
        //表单验证
        boolean error = false;
        if (isNull(userName)) {
            error = true;
            errMsg.append("请填写用户名称");
        }
        else if (isNull(password)) {
            error = true;
            errMsg.append("请填写用户密码");
        }
        else if (error == false && !password.equals(password_check)) {
            error = true;
            errMsg.append("两次输入的密码不一样");
        }

        if(error) {
            request.setAttribute("registerMsg", errMsg.toString());
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }else {
            UserDao userDao = new UserDao();
            //查询该用户名是否已经被注册了
            UserBean existUser = userDao.getUser(userName);
            if(existUser == null) {
                UserBean user = new UserBean();
                user.setUserName(userName);
                user.setPassword(password);
                boolean is_success = userDao.saveUser(user);
                if(is_success) {
                    //注册完直接登录
                    HttpSession session = request.getSession();
                    session.setAttribute("username", userName);
                    response.sendRedirect("index.jsp");
                }
                //数据库保存失败
                else {
                    request.setAttribute("registerMsg", "注册失败，请联系管理员！");
                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);
                }
            }else{
                request.setAttribute("registerMsg", "该用户名已被注册！");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
        }

    }

}
