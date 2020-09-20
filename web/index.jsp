<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Internet Library</title>
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
            <div class="authorization">
                <h1>Интернет библиотека</h1>
                <h2>Список книг по авторам</h2>
                <form action="${pageContext.request.contextPath}/login" method="POST">
                    <div class="invitation">
                        Необходимо войти в систему, чтобы воспользоваться интернет-библиотекой
                    </div>
                    <div class="input">
                        <input type="text" name="login" placeholder="Логин" required>
                    </div>
                    <div class="input">
                        <input type="password" name="password" placeholder="Пароль" required>
                    </div>
                    <div class="input">
                        <button type="submit" class="btn send"> Войти </button>
                    </div>
                    <div class="error"><%=errMsg%></div>
                </form>

            </div>
         </section>
    </body>
</html>
