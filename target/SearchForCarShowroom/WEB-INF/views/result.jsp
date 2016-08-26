<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<h2>Результаты поиска:</h2>
<c:forEach var="kit" items="${searchingResults}">
    <table border = "1">
        <tr>
            <td colspan = "4" align="center">Модель: ${kit.getAutomobile().model}</td>
        </tr>
        <tr>
            <td colspan = "2">Характеристики комплектации:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> <td colspan = "2">Характеристики модели:</td>
        </tr>
        <tr>
            <td>Тонировка стекла:</td> <td>${kit.windowTinting}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> <td>Максимальная мощность (л.с):</td> <td>${kit.getAutomobile().maxPower}</td>
        </tr>
        <tr>
            <td>Легкосплавные диски:</td> <td>${kit.alloyWheels}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> <td>Максимальный крутящий момент (Н*м):</td> <td>${kit.getAutomobile().maxTorque}</td>
        </tr>
        <tr>
            <td>Immobiliser:</td> <td>${kit.immobiliser}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> <td>Максимальная скорость (км/ч):</td> <td>${kit.getAutomobile().maxSpeed}</td>
        </tr>
        <tr>
            <td>Радиоподготовка:</td> <td>${kit.radioEquipment}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> <td>Ускорение 0-100 км/ч(с):</td> <td>${kit.getAutomobile().acceleration}</td>
        </tr>
        <tr>
            <td>Круиз-контроль:</td> <td>${kit.cruiseControl}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> <td>Расход топлива (л/100 км):</td> <td>${kit.getAutomobile().fuelConsumption}</td>
        </tr>
        <tr>
            <td colspan = "2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>                                <td>Вес (кг):</td> <td>${kit.getAutomobile().weight}</td>
        </tr>
        <tr>
             <td colspan = "4" align="center">Цена: ${kit.cost}</td>
        </tr>
    </table>
    <br>
    <br>
</c:forEach>
</body>
</html>
