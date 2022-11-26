<%@ page import="com.laundry.model.User" %>
<%@ page import="java.util.Objects" %>
<%@ page import="com.laundry.repo.LaundryOrderRepo" %>
<%@ page import="com.laundry.enums.LaundryOrderStatus" %>
<%@ page import="com.laundry.repo.PriceChartRepo" %>
<%@ page import="com.laundry.model.PriceChart" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./styles/admin-dashboard-style.css">
    <title>Dashboard</title>
</head>
<%
    String name = "";
    Integer userId = 0;
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
    } else {
        User user = (User) session.getAttribute("user");
        name = user.getName();
        userId = user.getId();
    }
    final LaundryOrderRepo laundryOrderRepo = new LaundryOrderRepo();
    Integer inProgressCount = laundryOrderRepo.countByStatusAndUserId(LaundryOrderStatus.IN_PROGRESS, userId);
    Integer finishedCount = laundryOrderRepo.countByStatusAndUserId(LaundryOrderStatus.FINISHED, userId);
%>
<body>
<div class="navbar">
    <div class="title">
        <h1>LaundryMS</h1>
    </div>
    <div class="menu">
        <ul>
            <li><a href="#">Request Service</a></li>
        </ul>
    </div>
    <div class="profile">
        <h3><a href="#"><%=name%>
        </a></h3>
        <a href="#"><img src="./images/user.png" height="30px" title="visit profile" alt=""></a>
        <a id="logout-a" href="logout"><img src="./images/exit.png" height="20px" title="logout"></a>
    </div>
</div>
<div class="main">
    <table>
        <caption>Price Chart</caption>
        <thead>
        <tr>
            <th>Type of cloth</th>
            <th>Price per unit</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<PriceChart> all = new PriceChartRepo().getAll();
            for (PriceChart priceChart : all) {
                System.out.println(priceChart);
        %>
        <tr>
            <td><%=priceChart.getTypeOfCloth()%></td>
            <td><%=priceChart.getPrice()%></td>
        </tr>
        <%}%>
        </tbody>
    </table>
    <div class="tiles">
        <div class="box orange">
            <div class="up">
                <%=inProgressCount%> &nbsp; In Progress
            </div>

            <div class="down">
                <div class="l-down">
                    View Details
                </div>
                <a href="#"><img src="./images/greater-than-symbol.png" height="20px" alt=""></a>
            </div>
        </div>
        <div class="box cyan">
            <div class="up">
                <%=finishedCount%> &nbsp; Finished
            </div>

            <div class="down">
                <div class="l-down">
                    View Details
                </div>
                <a href="#"><img src="./images/greater-than-symbol.png" height="20px" alt=""></a>
            </div>
        </div>
    </div>
</div>
</body>

</html>