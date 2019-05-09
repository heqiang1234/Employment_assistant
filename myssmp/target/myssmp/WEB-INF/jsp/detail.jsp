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
    <title>详情页面</title>
    <link rel="stylesheet" href="${APP_PATH }/static/bootstrap.css" >
    <SCRIPT LANGUAGE=javascript>
        function p_del() {
            var msg = "您真的确定要更改吗？\n\n请确认！";
            if (confirm(msg) == true) {
                return true;
            } else {
                return false;
            }
        }
    </SCRIPT>
</head>
<body>

    <div class="container" style="width: 600px">
    <form action="updateuser" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label >编号<span style="color:red;">${user.id}</span><br></label>
        </div>
        <%--编号：<span style="color:red;">${user.id}</span><br>--%>
        <div class="form-group">
            <label >用户名</label>
            <input  class="form-control" name="username"  placeholder="Email" value="${user.username}">
        </div>

        <div class="form-group">
            <label >密码</label>
            <input  class="form-control"  name="password" placeholder="Email" value="${user.password}">
        </div>
        <%--用户名:&nbsp;&nbsp;<input name="username" maxlength="3" placeholder="" value="${user.username}"><br>--%>
        <%--密码:&nbsp;<input name="password" maxlength="11" placeholder="" value="${user.password}"><br>--%>
        <input type="hidden" name="id" value="${user.id }"/>


        <input type="submit" value="修改" class="btn btn-primary" onclick="javascript:return p_del()" style="cursor:hand">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" name=”Submit”  class="btn btn-default" onclick="javascript:history.back(-1);" value="返回">
    </div>

</form>
</body>
</html>
