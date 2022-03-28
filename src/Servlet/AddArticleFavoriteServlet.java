package Servlet;

import Dao.ArticleFavoriteDao;
import Dao.InfoFavoriteDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddArticleFavoriteServlet")
public class AddArticleFavoriteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        //验证是否已经登录，防止直接通过url恶意修改用户收藏
        HttpSession session=request.getSession();
        String username = (String) session.getAttribute("username");
        if(username == null){
            request.setAttribute("loginMsg", "请先登录！");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }

        ArticleFavoriteDao articleFavoriteDao = new ArticleFavoriteDao();
        boolean success = articleFavoriteDao.addArticleFavorite(username,id);

        if(!success) {
            request.setAttribute("favoriteMsg", "增加收藏失败！");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }else{
            //servlet无法动态改变收藏按钮样式，只能通过刷新页面。动态改变需要使用ajax
            int src = Integer.parseInt(request.getParameter("src"));
            String url = "index.jsp";
            switch (src) {
                case 1 : {
                    url = "index.jsp";
                    break;
                }
                case 2 : {
                    url = "infos.jsp";
                    break;
                }
                case 3 : {
                    url = "articles.jsp";
                    break;
                }
                case 4 : {
                    url = "favorites.jsp";
                    break;
                }
            }
            response.sendRedirect(url);
        }
    }

    //请求统一交给doGet处理
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
