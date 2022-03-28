<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Dao.InfoDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Bean.InfoBean" %>
<%@ page import="Dao.InfoFavoriteDao" %>
<%@ page import="java.util.HashSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/style.css" rel="stylesheet">
    <link href="static/css/infos.css" rel="stylesheet">
    <script src="static/js/jquery-3.3.1.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar-default navbar-fixed-top navbar-shrink " style="height:110px;">
    <div class="navbar-header">
        <a class="navbar-brand" href="index.jsp" >地震灾害信息速报平台</a>
    </div>
    <div class="nav-items">
        <ul class="nav navbar-nav ">
            <% if(session.getAttribute("username") == null) {%>
            <li><a class="login" href="#">登录</a></li>
            <li><a class="register" href="#">注册</a></li>
            <% }
            else { String username = session.getAttribute("username").toString();%>
            <li><a> 欢迎！ <%= username %> </a></li>
            <li><a class="login" href="quitLoginServlet">退出登录</a></li>
            <li><a href="favorites.jsp">我的收藏</a></li>
            <% } %>
        </ul>
    </div>
    <div class="neck">
        <div class="neck-items">
            <ul>
                <li><a href="index.jsp">首页</a></li>
                <!-- 现在是灾害信息页，因此把active的css样式给灾害信息标签 -->
                <li class="active"><a href="infos.jsp">灾害信息</a></li>
                <li><a href="articles.jsp">科普文章</a></li>
                <% if(session.getAttribute("username") != null) {%>
                <li><a href="favorites.jsp">我的收藏</a></li>
                <% } %>

            </ul>
        </div>

    </div>
</nav>

<div class="main">

    <div class="container main-container" style="width: 600px">
        <div class="bt">灾害信息</div>
        <% InfoFavoriteDao infoFavoriteDao = new InfoFavoriteDao();
            String username = (String) session.getAttribute("username");
            boolean is_login = false;
            HashSet<Integer> infoFavoriteSet = null;
            //还未登录，则不获取收藏数据
            if(username != null){
                is_login = true;
                infoFavoriteSet = (HashSet<Integer>) infoFavoriteDao.getInfoFavorite(username);
                //存入request，通过jsp标签<c:if>使用
                request.setAttribute("infoFavoriteSet",infoFavoriteSet);
            }
        %>

        <% InfoDao infoDao = new InfoDao();
            ArrayList<InfoBean> infoList = (ArrayList<InfoBean>) infoDao.getInfos(); %>
        <c:forEach items="<%= infoList %>" var = "info">
            <c:set var="id" value="${info.id}" scope = "request"></c:set>
            <div class="newContentBox">
                <a hidefocus="true" href="${info.url}">
                    <div class="time">
                        <p class="level">${info.magnitude}<span class="suffix">级</span></p>
                        <p class="date">${info.date}</p>
                    </div>
                    <div class="newTitle">${info.position}</div>
                    <div class="border"></div>
                    <div class="newContent">
                        北京时间：${info.dateTime} 经度：${info.longitude}  纬度：${info.latitude} 深度：${info.depth}KM
                    </div>
                </a>

                <!-- 没登陆的情况，点击收藏按钮弹出登录框 -->
                <% if(is_login == false) {%>
                <a class="to-login" href="#">
                    <img class="favoriteIcon" src="static/images/favorite.png">
                </a>
                <% } else {
                    //如果保存收藏列表的set中存在此次输出的id，说明这条信息在用户收藏中
                    int id = (int) request.getAttribute("id");
                    if (infoFavoriteSet.contains(id)) { %>
                <a  href="DeleteInfoFavoriteServlet?id=${info.id}&src=2">
                    <img class="favoriteIcon" src="static/images/cancel-favorite.png">
                </a>
                <%} else { //不存在，该条信息未被收藏 %>
                <a  href="AddInfoFavoriteServlet?id=${info.id}&src=2">
                    <img class="favoriteIcon" src="static/images/favorite.png">
                </a>
                <%      }
                }
                %>
            </div>
        </c:forEach>
    </div>

</div>


<!-- 登录弹出框 -->
<div class="modal fade" id="login-dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <h4 class="modal-title" align="center">用户登录</h4>
                <br/>
                <form class="form-horizontal" role="form" action="LoginServlet" method="post">
                    <div class="form-group">
                        <label for="name" class="col-sm-offset-2 col-sm-2 control-label">账号</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="name" name="username" placeholder="请输您的入账号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-offset-2 col-sm-2 control-label">密码</label>
                        <div class="col-sm-5">
                            <input type="password" class="form-control" id="password" name="password" placeholder="请输入您的密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-5">
                            <button id="submitBtn" type="submit" class="btn btn-default btn-block btn-primary">登录</button>
                        </div>
                    </div>
                    <div class="illegal-input" id="loginMsg">${requestScope.loginMsg}</div>
                </form>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>

<!-- 注册框 -->
<div class="modal fade" id="register-dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <h4 class="modal-title" align="center">新用户注册</h4>
                <br/>
                <form class="form-horizontal" role="form" action="RegisterServlet" method="post">
                    <div class="form-group">
                        <label for="name" class="col-sm-offset-2 col-sm-2 control-label">账号</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="registerName" name="registerName"
                                   placeholder="请输入您的账号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-offset-2 col-sm-2 control-label">密码</label>
                        <div class="col-sm-5">
                            <input type="password" class="form-control" id="registerPassword" name="registerPassword"
                                   placeholder="请输入您的密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-offset-2 col-sm-2 control-label">确认密码</label>
                        <div class="col-sm-5">
                            <input type="password" class="form-control" id="registerRePassword"  name="registerRePassword"
                                   placeholder="请再次输入您的密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-5">
                            <button id="registerBtn" type="submit" class="btn btn-default btn-block btn-primary">注册</button>
                        </div>
                    </div>
                    <div class="illegal-input" id="registerMsg">${requestScope.registerMsg}</div>
                </form>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>

<!-- 收藏失败消息 -->
<div class="modal fade" id="favorite-dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h3 style="text-align: center">${requestScope.favoriteMsg}</h3>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>

            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    //如果发现request中有名为loginMsg或registerMsg的参数，就自动弹出登录/注册框
    window.οnlοad= $(function(){
        var s = ""
        s = "${requestScope.loginMsg}"
        if(s !== "") {
            $('#login-dialog').modal('show') //显示模态框
        }

        var s2 = ""
        s2 = "${requestScope.registerMsg}"
        if(s2 !== "") {
            $('#register-dialog').modal('show') //显示模态框
        }
        var s3 = ""
        s3 = "${requestScope.favoriteMsg}";
        if(s3 !== "") {
            $('#favorite-dialog').modal('show') //显示模态框
        }

    });
    //通过点击右上角的登录/注册来弹出

    $(function(){
        //使用jquery选择器
        $(".login").click(function(){
            $('#login-dialog').modal('show') //显示模态框
        })
    });

    $(function(){

        $(".register").click(function(){
            $('#register-dialog').modal('show') //显示模态框
        })
    });

    $(function(){
        //使用jquery选择器
        $(".to-login").click(function(){
            $('#login-dialog').modal('show') //显示模态框
        })
    });
</script>
</html>
