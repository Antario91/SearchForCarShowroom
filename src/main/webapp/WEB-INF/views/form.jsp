<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<h2>Hello World!</h2>
<form:form modelAttribute="searchObject" action="/result">
    <table>
       <tr>
           <td>Выберете модель автомобиля:</td>
           <td>
               <form:select path="model">
                   <form:option value="Rapid"/>
                   <form:option value="Octavia"/>
                   <form:option value="Fabia"/>
               </form:select>
           </td>
       </tr>
       <tr>
            <td>Укажите цену автомобиля: </td>
            <td>от <form:input path="minCost"/> грн. до <form:input path="maxCost"/> грн.</td>
       </tr>
       <tr>
            <td>Укажите требуемые <br>
            характеристики комплектации:</td>
            <td>Window tinting: <form:select path="windowTinting">
                                    <form:option value="null">Не учитывать</form:option>
                                    <form:option value="true">+</form:option>
                                    <form:option value="false">-</form:option>
                                </form:select> <br></td>
       </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="Save Changes"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
