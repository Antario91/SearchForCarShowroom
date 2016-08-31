<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h3>Изменение записи автомобиля в базе данных</h3>
<form action="/edit/${auto.model}" method="POST">
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
        </table>
        <table>
        <tr>
            <td rowspan = "2" valign = "top">Комплектации автомобиля:</td> <td valign = "top">Добавить новые:</td> <td><c:forEach var="kits" items="${new_carKits}">
                                                                                            <p><input type="checkbox" name="newKits" value="${kits.id}" />${kits.toString()}</p>
                                                                                          </c:forEach>
                                                                                          <br>
                                                                                      </td>
        </tr>
        <tr>
                                                             <td valign = "top">Удалить текущие:</td> <td><c:forEach var="kits" items="${current_carKits}">
                                                                                            <p><input type="checkbox" name="currentKits" value="${kits.id}" />${kits.toString()}</p>
                                                                                            </c:forEach>
                                                                                            <br>
                                                                                        </td>
        </tr>
        <tr>
            <td rowspan = "2" valign = "top">Заводы-изготовители:</td>  <td valign = "top">Добавить новые:</td> <td><c:forEach var="factories" items="${new_factories}">
                                                                                            <p><input type="checkbox" name="newFactories" value="${factories.country}" />${factories.getCountry()}</p>
                                                                                        </c:forEach>
                                                                                        <br>
                                                                                    </td>
        </tr>
        <tr>
                                                          <td valign = "top">Удалить текущие:</td> <td><c:forEach var="factories" items="${current_factories}">
                                                                                            <p><input type="checkbox" name="currentFactories" value="${factories.country}" />${factories.getCountry()}</p>
                                                                                         </c:forEach>
                                                                                         <br>
                                                                                    </td>
        </tr>
        <tr><td colspan = "2"><input type="checkbox" name="isDeleteAutomobile" value="true" />Удалить автомобиль из БД</td></tr>
        <tr><td><input type="submit" value="Изменить"></td></tr>
    </table>
</form>
</body>
</html>