<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h3>Изменение записи автомобиля в базе данных</h3>
<form action="/updateAutomobileInDB" method="POST">
    <table>
        <tr>
            <td>
                Модель: <input name="model" value="${auto.model}" type="text"/>
            </td>
        </tr>
        <tr>
            <td>
                Максимальная мощность (л.с): <input name="maxPower" value="${auto.maxPower}" type="text"/>
            </td>
        </tr>
        <tr>
            <td>
                Максимальный крутящий момент (Н*м): <input name="maxTorque" value="${auto.maxTorque}" type="text"/>
            </td>
        </tr>
        <tr>
            <td>
                Максимальный скорость (км/ч): <input name="maxSpeed" value="${auto.maxSpeed}" type="text"/>
            </td>
        </tr>
        <tr>
            <td>
                Ускорение 0-100 км/ч(с): <input name="acceleration" value="${auto.acceleration}" type="text"/>
            </td>
        </tr>
        <tr>
            <td>
                Расход топлива (л/100 км): <input name="fuelConsumption" value="${auto.fuelConsumption}" type="text"/>
            </td>
        </tr>
        <tr>
            <td>
                Вес автомобиля (кг): <input name="weight" value="${auto.weight}" type="text"/>
            </td>
        </tr>
        <tr>
            <td>
                Добавить новые комплектации автомобилю: <input type="checkbox" name="newCarKit" value="${auto.weight}" />
            </td>
        </tr>
        <tr>
            <td>
                Вес автомобиля (кг): <input name="weight" value="${auto.weight}" type="text"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>