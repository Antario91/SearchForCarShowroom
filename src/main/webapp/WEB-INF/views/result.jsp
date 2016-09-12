<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">

  </head>

  <body>
    <h2>Результаты поиска:</h2>
    <c:forEach var="kit" items="${searchingResults}">
        <table class="table table-hover">
            <tr>
                <td colspan = "5" align="center" class="active">Модель: ${kit.getAuto().model}</td>
            </tr>
            <tr>
                <td colspan = "2" class="active">Характеристики комплектации:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> <td colspan = "2" class="active">Характеристики модели:</td> <td rowspan = "3" class="active">Данная модель производится на заводах, расположенных в следующих странах:
                                                                                                                                                                <c:forEach var="factory" items="${factories.get(kit.getId())}">
                                                                                                                                                                    <p>${factory.country}</p>
                                                                                                                                                                </c:forEach>
                                                                                                                                                            </td>
            </tr>
            <tr>
                <td class="active">Тонировка стекла:</td> <td class="active">${kit.windowTinting}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> <td class="active">Максимальная мощность (л.с):</td> <td class="active">${kit.getAuto().maxPower}</td>
            </tr>
            <tr>
                <td class="active">Легкосплавные диски:</td> <td class="active">${kit.alloyWheels}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> <td class="active">Максимальный крутящий момент (Н*м):</td> <td class="active">${kit.getAuto().maxTorque}</td>
            </tr>
            <tr>
                <td class="active">Immobiliser:</td> <td class="active">${kit.immobiliser}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> <td class="active">Максимальная скорость (км/ч):</td> <td class="active">${kit.getAuto().maxSpeed}</td> <td class="active" rowspan = "4">Модель представлена в следующих автосалонах:
                                                                                                                                                                                            <c:forEach var="showroom" items="${showrooms.get(kit.getId())}">
                                                                                                                                                                                                <p>${showroom.toString()}</p>
                                                                                                                                                                                            </c:forEach>
                                                                                                                                                                                      </td>
            </tr>
            <tr>
                <td class="active">Радиоподготовка:</td> <td class="active">${kit.radioEquipment}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> <td class="active">Ускорение 0-100 км/ч(с):</td> <td class="active">${kit.getAuto().acceleration}</td>
            </tr>
            <tr>
                <td class="active">Круиз-контроль:</td> <td class="active">${kit.cruiseControl}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> <td class="active">Расход топлива (л/100 км):</td> <td class="active">${kit.getAuto().fuelConsumption}</td>
            </tr>
            <tr>
                <td  class="active" colspan = "2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>                                <td class="active">Вес (кг):</td> <td class="active">${kit.getAuto().weight}</td>
            </tr>
            <tr>
                 <td class="active" colspan = "5" align="center">Цена: ${kit.cost} грн.</td>
            </tr>
        </table>
        <table>
            <tr>
                <td class="active">
                    <form action="/edit/automobile/${kit.getAuto().id}"><input type="submit" value="Изменить"></form>
                </td>
            </tr>
        </table>
        <table>
            <tr>
                <td>
                    <img src="${images.get(kit.getAuto().model)}" />
                </td>
            </tr>
        </table>
        <br>
        <br>
    </c:forEach>







    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
  </body>
</html>