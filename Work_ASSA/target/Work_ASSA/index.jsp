<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src=".<%=basePath%>/js/jquery.min.js"></script>
    <script src=".<%=basePath%>/js/jquery-3.3.1.js"></script>
    <style type="text/css">
        form{
            margin:0px auto;

            width:500px;
            padding:20px;
        }
    </style>
    <title></title>
</head>

<body>
<h1>SSM整合</h1>
<form method="post">
    用户名：<input name="name" id="nmae1"/> <br/>
    用户年龄<input name="age"id="age1"/><br/>
    <input type="submit" value="save"/>
</form>
</body>
<script>
    $(document).ready(function () {
        $.ajax({
            url:"/home/index/",
            type:"post",
            // data发送urlencoded格式就行，数据没那么深，没必要发json格式
            data:{
                name:$("#nmae1").val(),
                age:$("#age1").val(),
            },
            success:function(response) {
                // console.log(response);
                if(response.userName1){
                    // 已登录
                    $("#userName").val(response.userName1)
                    $("#passWord").val(response.password1)
                    $("#sex").val(response.sex1)
                    $("#email_Name").val(response.email1)
                }
                else{
                    // 未登录
                    alert('failure')
                    $("#error").html(response.err_msg)
                }
            }
        })
    });


</script>
</html>