<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h3>Добавление комплектаций в автосалоны</h3>
    <form action="/updateCarKitsWithNewCarShowroom" method="POST">
        <table>
            <tr>
            <td>
                Комплектации:
                    <c:forEach var="kit" items="${kitsList}">
                        <p><input type="checkbox" name="kits" value="${kit.id}" />Модель: ${kit.auto.model}   ${kit.toString()}</p>
                    </c:forEach>
            <br>
            <br>
            </td>
            </tr>

            <tr>
            <td>
                Автосалоны:
                <c:forEach var="showroom" items="${carShowroomList}">
                    <p><input type="checkbox" name="showrooms" value="${showroom.id}" />${showroom.toString()}</p>
                </c:forEach>
            </td>
            </tr>

            <tr>
            <td>
            <br>
            <br>
                <input type="submit" value="Добавить">
            </td>
            </tr>
        </table>
    </form>
</form:form>
</body>
</html>