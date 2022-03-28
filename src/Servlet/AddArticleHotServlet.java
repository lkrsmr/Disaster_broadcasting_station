package Servlet;

import Dao.ArticleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddArticleHotServlet")
public class AddArticleHotServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String url = request.getParameter("url");
        System.out.println("add:" + id);
        ArticleDao articleDao = new ArticleDao();
        boolean success = articleDao.addHot(id);
        if(success){
            response.sendRedirect(url);
        }else{
            System.out.println("error:无法增加文章阅读量 id=" + id);
            response.sendRedirect(url);
        }
    }
}
