<%@page import="com.models.Car"%>
<%@page import="java.util.List"%>
<%@page import="com.models.Rental"%>
<%@page import="java.io.IOException"%>
<%@page import="com.models.Client"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Login Form Design</title>
        <link  rel="stylesheet"  href="./styles/style.css"/>
    </head>
    <body>
        <%
            Client client = (Client) session.getAttribute("user");
        %>
        <c:choose>
            <c:when test="<%=client == null%>">
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
            <section class="result-section">
                <div class="result-container">
                    <div class="logout"><a href="${pageContext.request.contextPath}/">logout</a></div>
                    <h2 class="greeting">Добро пожаловать, <%=client.getName()%>!</h2>
                    <div class="rental"> 
                        <c:forEach items="<%=client.getList()%>" var="rental">
                            <div class="point">
                                <div><p>${rental.getPoint()}</p></div>
                                <table>
                                    <thead>
                                    <th>модель</th>
                                    <th>производитель</th>
                                    <th>стоимость</th>
                                    <th>количество</th>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${rental.getList()}" var="car">
                                            <tr>
                                                <td>${car.getModel()}</td>
                                                <td>${car.getManufacturer()}</td>
                                                <td><fmt:setLocale value="ru_RU"/><fmt:formatNumber  type = "currency"  maxFractionDigits = "3" value = "${car.getPrice()}"/></td>
                                                <td>${car.getCount()}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:forEach>
                        <div class="result-xml"><a href="${pageContext.request.contextPath}/result.xml">загрузить результаты (xml)</a></div>
                    </div>

                </div>
            </section>
        </c:otherwise>
    </c:choose>



</body>
</html>
