<%@ page import="com.laundry.model.Admin" %>
<%@ page import="com.laundry.repo.PriceChartRepo" %>
<%@ page import="com.laundry.model.PriceChart" %>
<%@ page import="java.util.List" %>
<%@ page import="com.laundry.repo.LaundryOrderRepo" %>
<%@ page import="com.laundry.enums.LaundryOrderStatus" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./styles/admin-dashboard-style.css">
    <title>Admin - Dashboard</title>
</head>
<%
    String name = "";
    if(session.getAttribute("user") == null){
        response.sendRedirect("login-staff.jsp");
    }else{
        name = ((Admin)session.getAttribute("user")).getName();
    }

    final LaundryOrderRepo laundryOrderRepo = new LaundryOrderRepo();
    Integer newCount = laundryOrderRepo.countByStatus(LaundryOrderStatus.NEW);
    Integer acceptedCount = laundryOrderRepo.countByStatus(LaundryOrderStatus.ACCEPTED);
    Integer inProgressCount = laundryOrderRepo.countByStatus(LaundryOrderStatus.IN_PROGRESS);
    Integer finishedCount = laundryOrderRepo.countByStatus(LaundryOrderStatus.FINISHED);
%>
<body>
    <div class="navbar">
        <div class="title">
            <h1>LaundryMS</h1>
        </div>
        <div class="menu">
            <!-- <ul>
             <li><a href="#">Add Price Chart</a></li>
                 <li><a href="#">Pending Requests</a></li>
                <li><a href="#">Accepted Requests</a></li>
                <li><a href="#">Progress Requests</a></li>
                <li><a href="#">Completed Requests</a></li>
            </ul> -->
        </div>
        <div class="profile">
            <h3><a href="#"><%=name%></a> </h3>
            <a href="#"><img src="./images/user.png" height="30px" alt=""></a>
            <a id="logout-a" href="logout"><img src="./images/exit.png" height="20px" title="logout"></a>
        </div>
    </div>

    <div class="head">
        <div class="box green">
            <div class="up">
                <%=newCount%> &nbsp; New Requests
            </div>

            <div class="down">
                <div class="l-down">
                    View Details
                </div>
                <a href="#"><img src="./images/greater-than-symbol.png" height="20px" alt=""></a>
            </div>
        </div>
        <div class="box yellow">
            <div class="up">
                <%=acceptedCount%> &nbsp; Accepted
            </div>

            <div class="down">
                <div class="l-down">
                    View Details
                </div>
                <a href="#"><img src="./images/greater-than-symbol.png" height="20px" alt=""></a>
            </div>
        </div>
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
    <div class="main">
        
        <table>
            <caption>Price Chart</caption>
            <thead>
                <tr>
                    <th>Type of cloth</th>
                    <th>Price per unit</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<PriceChart> all = new PriceChartRepo().getAll();
                    for (PriceChart priceChart : all) {

                %>
                <tr>
                    <td><%=priceChart.getTypeOfCloth()%></td>
                    <td><%=priceChart.getPrice()%></td>
                    <td><a href="edit-price.jsp?id=<%=priceChart.getId()%>"><img src="./images/editing.png" alt="EDIT" height="30px"></a>
<%--                        <a href="delete-price?id=<%=priceChart.getId()%>"><img src="./images/delete.png" alt="DELETE" height="30px"></a>--%>
                    </td>
                </tr>
                <%}%>
<%--                <tr>--%>
<%--                    <td style="border: none"></td>--%>
<%--                    <td style="border: none"></td>--%>
<%--                    <td style="text-align: center"><a href="add-price.jsp"><img src="./images/add.png" alt="ADD" height="30px" ></a></td>--%>
<%--                </tr>--%>
            </tbody>
        </table>

        <!-- <img src="./images/laundry.png" height="500px" alt=""> -->
    </div>
    <br><br><br><br>
</body>

</html>