<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/7/23 0023
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>

<html>
<head>
    <title>添加用户</title>
    <link rel="stylesheet" href="${APP_PATH }/static/bootstrap.css" >
</head>

<form action="save" method="post">
    <%--<table>--%>
        <%--用户名:&nbsp;&nbsp;&nbsp;&nbsp;<input name="username" maxlength="3"  value="" placeholder="用户名">--%>
        <%--<br><br>--%>
        <%--密码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="password" maxlength="6"  value="" placeholder="密码"><br>--%>

        <%--&lt;%&ndash;<h>$-----------------------------------------------------------------------------$</h>&ndash;%&gt;--%>
        <%--<br><br>--%>
        <%--<input type="submit" value="保存"  onclick="return check(this.form)" style="cursor:hand">--%>
        <%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
        <%--<input type=”button” name=”Submit” style="background: violet;width:35px;cursor:hand" onclick="javascript:history.back(-1);" value="返回">--%>
    <%--</table>--%>


        <div class="container" style="width: 600px">
            <form action="save" method="post" enctype="multipart/form-data">
                <h1>添加用户</h1>
                <div class="form-group">
                    <label >用户名</label>
                    <input  class="form-control" name="username"  placeholder="用户名" >
                </div>

                <div class="form-group">
                    <label >密码</label>
                    <input  class="form-control"  name="password" placeholder="密码">
                </div>



                <input type="submit" value="保存" class="btn btn-primary" onclick="javascript:return check(this.form)" style="cursor:hand">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" name=”Submit”  class="btn btn-default" onclick="javascript:history.back(-1);" value="返回">
            </form>'
        </div>

</form>
</body>
</html>
