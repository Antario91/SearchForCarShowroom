<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Carousel Template for Bootstrap</title>
  <style>
     .ttbody {
				text-shadow: 1px 1px 1px black;
                background-color: #9c9c9c;
                color: #f1f1f1;
             }
  </style>

  <!-- Bootstrap core CSS -->
  <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
</head>
<body class="ttbody">
<h2>Выберете критерии поиска:</h2>
<form:form modelAttribute="SearchObject" action="/result" role="form">
 <p>
   <label>
        Выберете модель автомобиля:
        <form:select path="model" class="form-control">
              <form:option value="null" label="Не учитывать"/>
              <form:options items="${modelList}"/>
            </form:select>
    </label>
  </p>

  <p class="text">
    <b>Укажите цену автомобиля:
    от <font color="#000000"><form:input path="minCost" class="form-group" /></font> грн. до <font color="#000000"><form:input path="maxCost" class="form-group"/></font> грн.
    </b>
  </p>


  <p class="text"><b>Укажите требуемые характеристики комплектации:</b></p>

  <label>
      Тонировка стекла:
      <form:select path="windowTinting" class="form-control">
                              <form:option value="null">Не учитывать</form:option>
                              <form:option value="true">+</form:option>
                              <form:option value="false">-</form:option>
                        </form:select>
  </label>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;



  <label>
    Легкосплавные диски:
    <form:select path="alloyWheels" class="form-control">
                                <form:option value="null">Не учитывать</form:option>
                                <form:option value="true">+</form:option>
                                <form:option value="false">-</form:option>
                         </form:select>
  </label>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


  <label>
    Immobiliser:
    <form:select path="immobiliser" class="form-control">
                        <form:option value="null">Не учитывать</form:option>
                        <form:option value="true">+</form:option>
                        <form:option value="false">-</form:option>
                 </form:select>

  </label>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


  <label>
    Радиоподготовка:
    <form:select path="radioEquipment" class="form-control">
                            <form:option value="null">Не учитывать</form:option>
                            <form:option value="true">+</form:option>
                            <form:option value="false">-</form:option>
                     </form:select>

  </label>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


  <label>
    Круиз-контроль:
    <form:select path="cruiseControl" class="form-control">
                            <form:option value="null">Не учитывать</form:option>
                            <form:option value="true">+</form:option>
                            <form:option value="false">-</form:option>
                    </form:select>
  </label>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<p>
<input type="submit" value="Поиск" class="btn btn-primary">
</p>

</form:form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
</body>
</html>