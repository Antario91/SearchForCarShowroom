<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<body>
<h3>Добавление нового завода-изготовителя в базу данных</h3>
<form:form modelAttribute="factory" action="/updateDBWithNewManufacturingPlant" method="POST">
    <table>
        <tr>
            <td>Страна, в которой находится завод-изготовитель: <form:input path="country"/></td>
        </tr>

        <tr>
            <td>
                <input type="submit" value="Добавить в БД"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>