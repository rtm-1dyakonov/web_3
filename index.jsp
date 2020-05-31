<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Login Form Design</title>
        <style>
            <%@include file="styles/style.css" %>
        </style>
    </head>
    <body>
        <%
        Object o = session.getAttribute("error");
        String errMsg = o == null ? "" : (String) o;
        Boolean ec0=false, ec1=false;
        if(errMsg!=null && !errMsg.isEmpty()){
            ec0 = (Boolean)session.getAttribute("ec0");
            ec1 = (Boolean)session.getAttribute("ec1");
            }
        %>
        <section class>
            <div class="container">
                <div class="form_content">
                    <h2>Здравствуй, друг!</h2>
                    <p>Введи свой логин и пароль для доступа к системе.</p>
                </div>
                <div class="login_form">
                    <h1>Войти в систему</h1>
                    <form action="${pageContext.request.contextPath}/login" method="POST">
                        <input <%=!errMsg.isEmpty()&&ec0?"style=\"border:2px solid red\"":"style=\"border:2px solid #673ab7\""%> type="text" name="login" placeholder="Логин пользователя" required/>
                        <input <%=!errMsg.isEmpty()&&ec1?"style=\"border:2px solid red\"":"style=\"border:2px solid #673ab7\""%> type="password" name="password" placeholder="Пароль" required/>
                        <input type="submit" name="" value="LOGIN">
                    </form>
                    <div class="err"><%=errMsg%></div> 
                </div>
            </div>

        </section>


    </body>
</html>
