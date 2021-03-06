<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="/css/employeestyle.css" title="style" />
</head>
<body>
<div class="edit">
  <form name="edit" class="form-horizontal" method="post" action="add_department.html">
    <div class="form-group">
      <label for="inputEmail3" class="col-sm-2 control-label">Name: </label>
      <div class="col-sm-10">
        <input type="text" name="name" class="form-control" id="inputEmail3" placeholder="Name"
               value="<c:out value="${requestScope.department.getName()}"></c:out>">
                <p style="color: brown"> <c:out value="${errorMessage}"></c:out></p>
      </div>
    </div>

    <div class="add">
      <input type="submit" class="btn btn-large btn-success" name="addSubmit" value="Add">
    </div>
  </form>
  <div class="cancel">
    <form action="index.html" method="post">
      <input type="submit" class="btn btn-large btn-info" name="addSubmit" value="Cancel">
    </form>
  </div>
</div>
</body>
</html>
