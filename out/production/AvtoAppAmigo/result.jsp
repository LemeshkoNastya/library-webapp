<%@page import="com.models.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.models.Library"%>
<%@page import="java.io.IOException"%>
<%@page import="com.models.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Internet Library</title>
        <link  rel="stylesheet"  href="styles/style.css"/>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
        %>
        <c:choose>
            <c:when test="<%=user == null%>">
                <script>
                    function goto_login() {
                        document.location.href = "${pageContext.request.contextPath}/";
                    }
                    setTimeout(goto_login, 5000);
                </script>
            <center>
                <p>Вы не авторизированы</p> <a style="color: gray" href="${pageContext.request.contextPath}/">перейти немедленно для входа</a>
            </center>

        </c:when>
        <c:otherwise>
            <section class="result_style">
                <h3>Интернет библиотека</h3>
                <h4>Добро пожаловать, <%=user.getName()%></h4>
                <div class="library">
                    <c:forEach items="<%=user.getList()%>" var="library">
                    <div class="author">
                        <div><p>${library.getAuthor()}</p></div>
                        <table>
                            <thead>
                                <th>название</th>
                                <th>год издательства</th>
                                <th>количество страниц</th>
                                <th>стоимость</th>
                            </thead>
                            <tbody>
                             <c:forEach items="${library.getList()}" var="book">
                                 <tr>
                                     <td>${book.getName()}</td>
                                     <td>${book.getYear()}</td>
                                     <td>${book.getPageCount()}</td>
                                     <td>${book.getPrice()}</td>
                                 </tr>
                                 </c:forEach>
                             </tbody>
                        </table>

                    </div>
                    </c:forEach>
                    <div class="exit_index"><a href="${pageContext.request.contextPath}/">Вернуться на исходную страницу</a></div>
                    <div class="result_xml"><a href="${pageContext.request.contextPath}/result.xml">Скачать файл</a></div>
                </div>
            </section>
        </c:otherwise>
    </c:choose>



</body>
</html>
