<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<form:form modelAttribute="searchObject" action="/result">
    <table>
       <tr>
           <td>
           <p>Выберете модель автомобиля: <form:select path="model">
                                               <form:option value="null">Не учитывать</form:option>
                                               <form:option value="Rapid">Rapid</form:option>
                                               <form:option value="Octavia">Octavia</form:option>
                                               <form:option value="Fabia">Fabia</form:option>
                                         </form:select>
           </td>
       </tr>
       <tr>
            <td>Укажите цену автомобиля: </td>
            <td>от <form:input path="minCost"/> грн. до <form:input path="maxCost"/> грн.</td>
       </tr>
       <tr>
            <td>
            <p>Укажите требуемые характеристики комплектации:</p>
            <p>Тонировка стекла: <form:select path="windowTinting">
                                    <form:option value="null">Не учитывать</form:option>
                                    <form:option value="true">+</form:option>
                                    <form:option value="false">-</form:option>
                              </form:select>
            </p>
            <p>Легкосплавные диски: <form:select path="alloyWheels">
                                        <form:option value="null">Не учитывать</form:option>
                                        <form:option value="true">+</form:option>
                                        <form:option value="false">-</form:option>
                                 </form:select>
            </p>
            <p>Immobiliser: <form:select path="immobiliser">
                                <form:option value="null">Не учитывать</form:option>
                                <form:option value="true">+</form:option>
                                <form:option value="false">-</form:option>
                         </form:select>
            </p>
            <p>Радиоподготовка: <form:select path="radioEquipment">
                                    <form:option value="null">Не учитывать</form:option>
                                    <form:option value="true">+</form:option>
                                    <form:option value="false">-</form:option>
                             </form:select>
            </p>
            <p>Круиз-контроль: <form:select path="cruiseControl">
                                    <form:option value="null">Не учитывать</form:option>
                                    <form:option value="true">+</form:option>
                                    <form:option value="false">-</form:option>
                            </form:select>
            <p>
            </td>
       </tr>
        <tr>
            <td>
                <input type="submit" value="Поиск"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
