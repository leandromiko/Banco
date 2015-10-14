<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <%@include file="/verificaLogin.jspf" %>
        <title>Transferencia</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="container">
            <div id="login-form">
                <h3>Transferencia</h3>
                <fieldset>
                    <form action="FrontController?command=realizarTransferencia" method="post">
                        <select name="user" id="transfer">
                            <option value=""> ------------------- Selecione ------------------- </option>
                            <c:forEach var="user2" items="${listaUsuarios}">
                                <c:if test="${user2.getIdUser()!=user.getIdUser()}">
                                    <option value="${user2.getIdUser()}" >${user2.getUsername()}</option>
                                </c:if>
                            </c:forEach>
                        </select><br><br>
                        <input name="valor" id="transfer" type="number" placeholder="Valor" autofocus value="" required>
                        <input id="verde" type="submit" value="Confirm">
                    </form>
                    <a href="home.jsp"><input id="vermelho" type="submit" value="Cancel"></a>
                </fieldset>
            </div>
        </div>
        <c:if test="${param.transfer=='false'}"><script>alert('VALOR INVÁLIDO');</script></c:if>
        <c:if test="${param.user=='false'}"><script>alert('USUÁRIO INVÁLIDO');</script></c:if>
    </body>
</html>