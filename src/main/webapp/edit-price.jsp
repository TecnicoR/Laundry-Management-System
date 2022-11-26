<%@ page import="com.laundry.model.User" %>
<%@ page import="com.laundry.model.Admin" %>
<%@ page import="com.laundry.repo.PriceChartRepo" %>
<%@ page import="com.laundry.model.PriceChart" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./styles/style.css">
    <title>Edit Price - LMS</title>
</head>
<%
    if((Admin)session.getAttribute("user") == null){
        response.sendRedirect("login-staff.jsp");
    }
    Integer id = Integer.parseInt(request.getParameter("id"));
    PriceChart priceChart = new PriceChartRepo().getById(id);
%>

<body>
    <div class="main-form">
        <h2>Edit price detail</h2>
        <form action="edit-price" method="post">
            <div class="form-input">
                <label for="typeOfCloth">Type Of Cloth</label>
                <input type="text" name="typeOfCloth" value="<%=priceChart.getTypeOfCloth()%>" id="typeOfCloth" placeholder="enter here" required readonly>
            </div>
            <div class="form-input">
                <label for="price">Price</label>
                <input type="text" name="price" value="<%=priceChart.getPrice()%>" id="price" placeholder="enter here" autofocus required>
            </div>
            <input type="hidden" name="id" value="<%=priceChart.getId()%>">
            <div class="form-input">
                <input type="submit" value="Update">
            </div>
        </form>
    </div>
</body>

</html>