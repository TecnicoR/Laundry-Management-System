<%@ page import="com.laundry.model.User" %>
<%@ page import="com.laundry.model.Admin" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./styles/style.css">
    <title>Add Price - LMS</title>
</head>
<% String name = "";
    if ((Admin) session.getAttribute("user") == null) {
        response.sendRedirect("login-staff.jsp");
    } else {
        name = ((Admin) session.getAttribute("user")).getName();
    } %>

<body>

<div class="main-form">
    <h2>Add new price detail</h2>
    <form action="add-price" method="post">
        <div class="form-input">
            <label for="typeOfCloth">Type Of Cloth</label>
            <input type="text" name="typeOfCloth" id="typeOfCloth" placeholder="enter here" autofocus
                   required>
        </div>
        <div class="form-input">
            <label for="price">Price</label>
            <input type="text" name="price" id="price" placeholder="enter here" required>
        </div>
        <div class="form-input">
            <input type="submit" value="Add">
        </div>
    </form>
</div>

</body>

</html>