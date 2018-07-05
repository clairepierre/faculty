<%--
  Created by IntelliJ IDEA.
  User: computer
  Date: 20.05.2018
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Please press the button in order to start</h1>
<form action="/login" method="post">
    username: <input type="=text" name="loginname" width="30"/>
    password:<input type="text" name="password" width="10">
    <input type="submit" value="login">
</form>

<p style="color: darkred; font-family: 'Arial Black'; font-weight: bold"> ${errorMessage}</p>
</body>
</html>
