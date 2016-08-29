<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h3>Назначение комплектаций автомобилю</h3>
    <form action="/updateAutomobileWithNewCarKits" method="POST">
        <table>
            <tr>
            <td>
                Модель автомобиля: <select name="modelAuto">
                                        <c:forEach var="auto" items="${autosList}">
                                        <option value="${auto.model}">${auto.model}</option>
                                        </c:forEach>
                                    </select>
            <br>
            <br>
            </td>
            </tr>

            <tr>
            <td>
                <c:forEach var="kit" items="${kitsList}">
                <p><input type="checkbox" name="kits" value="${kit.id}" />${kit.toString()}</p>
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