<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<h2>Выберете критерии поиска:</h2>
<form:form modelAttribute="SearchObject" action="/result">
    <table>
       <tr>
           <td>
           <p>Выберете модель автомобиля: <form:select path="model">
                                               <form:option value="null" label="Не учитывать"/>
                                               <form:options items="${modelList}"/>
                                         </form:select>
           <br>
           <br>
           </p>
           </td>
       </tr>
       <tr>
            <td>Укажите цену автомобиля: от <form:input path="minCost"/> грн. до <form:input path="maxCost"/> грн. <br><br></td>
       </tr>
       <tr>
            <td>
            <p>Укажите требуемые характеристики комплектации:</p>
            </td>
       </tr>
       <tr>
            <td>
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
            <td align="center">
                <input type="submit" value="Поиск"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
