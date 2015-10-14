<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <c:if test="${isLogged}" >
            <c:redirect url="/home.jsp"></c:redirect>
        </c:if>
        <title>Login Form</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
        <div class="container">
            <div id="login-form">
                <h3>Login</h3>
                <fieldset>
                    <form action="FrontController?command=login" method="post">
                        <input name="login" id="username" type="text" placeholder="Usuário" autofocus required>
                        <input type="password" required name="senha" placeholder="Senha"><br/>
                        <input type="submit" value="Log in">
                        <footer class="clearfix">
                        </footer>
                    </form>
                </fieldset>
            </div>
        </div>
        <c:if test="${param.login=='false'}">
            <script>alert('LOGIN INVÁLIDO');</script>
        </c:if>
    </body>
</html>