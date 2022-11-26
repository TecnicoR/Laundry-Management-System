<%@ page import="com.laundry.model.User" %>
<%@ page import="com.laundry.repo.PriceChartRepo" %>
<%@ page import="com.laundry.model.PriceChart" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./styles/style.css">
    <title>New Request - LMS</title>
</head>
<%

    String name = "";
    Integer id = 0;
    if (session.getAttribute("user") == null) {
        response.sendRedirect("user-dashboard.jsp");
    } else {
        User user = (User) session.getAttribute("user");
        name =user.getName();
        id = user.getId();
    }
%>

<body>
<div class="main-form">
    <h2>Raise New Laundry Request</h2>
    <form action="new-request" method="post">
        <input type="hidden" name="id" value="<%=id%>">
        <div class="form-input">
            <label for="name">Name</label>
            <input type="text" name="name" value="<%=name%>" id="name" placeholder="enter here" autofocus readonly required>
        </div>
        <div class="form-input">
            <label for="shirt">Shirt</label>
            <select name="shirt" id="shirt">
                <% for (int i=0;i<50;i++) out.print("<option value="+i+">"+i+"</option>");%>
            </select>
        </div>
        <div class="form-input">
            <label for="tShirt">T Shirt</label>
            <select name="tShirt" id="tShirt">
                <% for (int i=0;i<50;i++) out.print("<option value="+i+">"+i+"</option>");%>
            </select>
        </div>
        <div class="form-input">
            <label for="bottomWear">Bottom Wear</label>
            <select name="bottomWear" id="bottomWear">
                <% for (int i=0;i<50;i++) out.print("<option value="+i+">"+i+"</option>");%>
            </select>
        </div>
        <div class="form-input">
            <label for="woolenWear">Woolen Wear</label>
            <select name="woolenWear" id="woolenWear">
                <% for (int i=0;i<50;i++) out.print("<option value="+i+">"+i+"</option>");%>
            </select>
        </div>
        <div class="form-input">
            <label for="bedsheets">Blankets/Bed sheets</label>
            <select name="bedsheets" id="bedsheets">
                <% for (int i=0;i<50;i++) out.print("<option value="+i+">"+i+"</option>");%>
            </select>
        </div>
        <div class="form-input">
            <label for="others">Others</label>
            <select name="others" id="others">
                <% for (int i=0;i<50;i++) out.print("<option value="+i+">"+i+"</option>");%>
            </select>
        </div>
        <div class="form-input">
            <input type="submit" value="Submit">
        </div>
    </form>
</div>
</body>

</html>