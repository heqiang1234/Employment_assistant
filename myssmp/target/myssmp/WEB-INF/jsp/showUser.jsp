<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>用户列表</title>
    <link rel="stylesheet" href="${APP_PATH }/static/bootstrap.css" >

    <SCRIPT LANGUAGE=javascript>
        function p_del() {
            var msg = "您真的确定要删除吗？\n\n请确认！";
            if (confirm(msg) == true) {
                return true;
            } else {
                return false;
            }
        }
    </SCRIPT>

    <SCRIPT LANGUAGE=javascript>
        function re() {
            var msa = "您真的确定要退出吗？\n\n请确认！";
            if (confirm(msa) == true) {
                return true;
            } else {
                return false;
            }
        }
    </SCRIPT>
</head>
<body>

    <div class="container" style="width: 900px;">
        <%--标题--%>
        <div class="row">
            <div class="col-md-12">
                <h1 style="text-align: center;">欢迎</h1>

            </div>
        </div>
            <%--查询行--%>
         <div class="row" style="text-align: center">
             <div class="col-md-12">
                 <form class="form-inline" action="/user/findUserName.do" method="post">
                     <label>姓名:</label>&nbsp;&nbsp;<input class="form-control" type="text" name="userName">&nbsp;&nbsp;
                     <label>手机号:</label>&nbsp;&nbsp;<input class="form-control" type="text" name="userPhone">&nbsp;&nbsp;
                     <input class="btn btn-default" type="submit" value="查询" style="cursor:hand">
                     <a class="btn btn-primary btn-sm" href="add">
                         <%--<span class="glyphicon glyphicon-zoom-in" ></span>--%>
                         添加用户
                     </a>
                 </form>
             </div>
         </div>

            <%--表格--%>
            <div class="row" style="text-align: center">
                <div class="col-md-12">

                    <table class="table table-hover">
                        <tr>
                            <th>用户名</th>
                            <th>密码</th>
                            <th>真实姓名</th>
                            <th>学校</th>
                            <th>专业</th>
                            <th>意向职位</th>
                            <th>邮箱</th>
                            <th>操作</th>
                        </tr>
                       <c:if test="${!empty userList}">
                           <c:forEach var="user" items="${userList}">
                            <tr>
                                <th>${user.user_Name }</th>
                                <th>${user.user_Password }</th>
                                <th>${user.user_RealName }</th>
                                <th>${user.user_School }</th>
                                <th>${user.user_Major }</th>
                                <th>${user.user_IntentionPost }</th>
                                <th>${user.user_Email }</th>
                                <th>
                                    <a class="btn btn-primary btn-sm" href="userinfo/${user.user_Id}">
                                        <%--<span class="glyphicon glyphicon-zoom-in" ></span>--%>
                                        编辑
                                    </a>
                                    <a class="btn btn-danger btn-sm" href="deleteuser/${user.user_Id}" onclick="javascript:return p_del()">
                                        <%--<span class="glyphicon glyphicon-trash" ></span>--%>
                                        删除
                                    </a>
                                </th>
                            </tr>
                        </c:forEach>
                       </c:if>
                    </table>


                </div>
            </div>


    </div>


    <%--</div>--%>

    <%--<a href="/user/loginOut.do" onclick="javascript:return re();">退出登录</a>--%>
    <%--<br>--%>
    <%--<h>$-----------------------------------------------------------------------------$</h>--%>
    <%--<br>--%>

    <%--<form action="/user/findUserName.do" method="post">--%>
        <%--姓名:<input type="text" name="userName">&nbsp;&nbsp;--%>
        <%--手机号:<input type="text" name="userPhone">--%>
        <%--<input type="submit" value="查询" style="cursor:hand">--%>
    <%--</form>--%>
    <%--<h>$-----------------------------------------------------------------------------$</h>--%>
    <%--<br>--%>
    <%--<c:if test="${!empty userList}">--%>
        <%--<c:forEach var="user" items="${userList}">--%>
            <%--&lt;%&ndash;头像：<img src="<%=basePath%>${user.imageUrl}" style="width: 80px" style="height:60px" alt=""/>&ndash;%&gt;--%>
            <%--姓名：${user.username}&nbsp;&nbsp;--%>
            <%--手机号：${user.password} &nbsp;&nbsp;--%>
            <%--&lt;%&ndash;邮箱：${user.userEmail} &nbsp;&nbsp;&ndash;%&gt;--%>
            <%--<a href="deleteuser/${user.id}" onclick="javascript:return p_del()">删除</a>--%>
            <%--<a href="userinfo/${user.id}">编辑</a><br>--%>
        <%--</c:forEach>--%>
    <%--</c:if>--%>
    <%--<h>$-----------------------------------------------------------------------------$</h>--%>
    <%--<br>--%>
    <%--<input class="btn btn-primary" type="button" value="新增一个人员"--%>
           <%--onclick="javascrtpt:window.location.href='add'" style="cursor:hand">--%>

    <%--<div style="position:absolute;font-size:20px;color:blue;font-weight:bold;width:360px;">--%>
        <%--<script>--%>
            <%--document.write((zjd=new Date()).getFullYear()+"年"+(zjd.getMonth()+1)+"月"+zjd.getDate() + "日 星期"+"日一二三四五六".charAt(zjd.getDay())+ " "+zjd.getHours() +":"+zjd.getMinutes()+":"+zjd.getSeconds());--%>
        <%--</script>--%>
    <%--</div>--%>

</body>
</html>

