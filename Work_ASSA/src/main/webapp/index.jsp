<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="../<%=basePath%>/js/jquery.min.js"></script>
    <script src="../<%=basePath%>/js/jquery-3.3.1.js"></script>
    <style type="text/css">
        form{
            margin:0px auto;

            width:500px;
            padding:20px;
        }git remote add origin https://github.com/heqiang1234/Employment_assistant.git
    </style>
    <title></title>
</head>

<body>
<h1>SSM整合</h1>
<form  >
    用户名：<input name="name1" id="nmae1"/> <br/>
    用户年龄<input name="age1"id="age1"/><br/>
    <input type="submit" value="save" onclick="test11()"/>
</form>
</body>
<script>
  function test11() {
      alert('dada')
      $.ajax({
          url: "/home/index/",
          type: "post",
          // data发送urlencoded格式就行，数据没那么深，没必要发json格式
          data: {
              name: $("#nmae1").val(),
              age: $("#age1").val(),
          },
          success: function (response) {
              // console.log(response);
             alert("hello")
          }
      })
  }


</script>
</html>