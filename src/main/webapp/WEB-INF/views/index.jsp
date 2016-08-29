<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Welcome to the Skoda world</h2>
<p><a href="/form">Поиск</a></p>
  <table>
  <tr>
  <td>
    <form>
        <input type="submit" formaction="/new_automobile" value="Добавить новую модель авто в БД">
    </form>
  </td>
  <td>
    <form>
        <input type="submit" formaction="/new_CarKit" value="Добавить новую комплектацию в БД">
    </form>
  </td>
  <td>
    <form>
        <input type="submit" formaction="/new_manufacturingPlant" value="Добавить новый завод-изготовитель в БД">
    </form>
  </td>
  <td>
    <form>
        <input type="submit" formaction="/new_CarShowroom" value="Добавить новый автосалон в БД">
    </form>
  </td>
  </tr>

  <tr>
  <td>
    <form>
        <input type="submit" formaction="/addCarKitToAutomobile" value="Назначить автомобилю комплектации">
    </form>
  </td>
  <td>
    <form>
        <input type="submit" formaction="/addCarKitToCarShowroom" value="Добавить комплектации в автосалоны">
    </form>
  </td>
  <td>
    <form>
        <input type="submit" formaction="/addManufacturingPlantToAutomobile" value="Назначить автомобилю заводы-изготовители">
    </form>
  </td>
  </tr>
  </table>
</body>
</html>