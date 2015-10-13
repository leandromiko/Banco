<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Saque</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="container">
            <div id="login-form">
                <h3>Saque</h3>
                <fieldset>
                    <form action="FrontController?command=realizarSaque" method="post">
                        <input name="valor" id="transfer" type="number" placeholder="Valor" autofocus required>
                        <input id="verde" type="submit" value="Confirm">
                    </form>
                    <a href="home.jsp"><input id="vermelho" type="submit" value="Cancel"></a>
                </fieldset>
            </div>
        </div>
    </body>
</html>