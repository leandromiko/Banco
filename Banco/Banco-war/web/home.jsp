<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <%@include file="/verificaLogin.jspf" %>
        <title>Menu Principal</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="container">
            <div id="login-form">
                <h3>Bem vindo, ${user.getUsername()}!</h3>
                <fieldset>
                    <a href="FrontController?command=transferencia"><button>Transferencia</button></a><br/><br/>
                    <a href="FrontController?command=saque"><button>Saque</button></a><br/><br/>
                    <label id="verde">Saldo: ${saldo}</label>
                    <a href="FrontController?command=logout"><input id="vermelho" type="submit" value="Logout"></a> 
                </fieldset>
            </div>
        </div>
        <style>
            #verde{
                background: #658f25;
                float: left;
            }
            #verde:hover{background: #658f25;}
            button{
                font-size:14px;
                width:100%;
                height:30px;
            }
            #vermelho{float:right;}
        </style>
    </body>
</html>