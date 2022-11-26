<%@ page import="com.laundry.model.Admin" %>
<%@ page import="com.laundry.repo.PriceChartRepo" %>
<%@ page import="com.laundry.model.PriceChart" %>
<%@ page import="java.util.List" %>
<%@ page import="com.laundry.repo.LaundryOrderRepo" %>
<%@ page import="com.laundry.enums.LaundryOrderStatus" %>
<%@ page import="com.laundry.model.LaundryOrder" %>
<!DOCTYPE html>
<html lang="en">
<%
    String orderStatus = "";
    LaundryOrderStatus status = null;
    if (request.getParameter("orderStatus") != null) {
        orderStatus = request.getParameter("orderStatus");
        status = Enum.valueOf(LaundryOrderStatus.class, orderStatus);
        if (orderStatus.contains("_"))
            orderStatus = orderStatus.replace("_", " ");
        orderStatus = orderStatus.substring(0, 1).toUpperCase() + orderStatus.substring(1).toLowerCase();
    }
%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./styles/admin-dashboard-style.css">
    <title>All <%=orderStatus%> orders</title>
</head>
<%
    String name = "";
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login-staff.jsp");
    } else {
        name = ((Admin) session.getAttribute("user")).getName();
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
        <h1><a href="./admin-dashboard.jsp">LaundryMS</a></h1>
    </div>
    <div class="menu">
        <%
            if (session.getAttribute("error") != null) {
                out.print("<p style='margin-left: 250px; font-size: large; font-weight: bold; color: red;'>" + session.getAttribute("error") + "</p>");
                session.removeAttribute("error");
            }
            if (session.getAttribute("success") != null) {
                out.print("<p style='margin-left: 250px; font-size: large; font-weight: bold; color: green;'>" + session.getAttribute("success") + "</p>");
                session.removeAttribute("success");
            }
        %>
    </div>
    <div class="profile">
        <h3><a href=""><%=name%>
        </a></h3>
        <a href=""><img src="./images/user.png" height="30px" alt=""></a>
        <a id="logout-a" href="logout"><img src="./images/exit.png" height="20px" title="logout" alt="logout"></a>
    </div>
</div>

<div class="head">
    <div class="box green">
        <div class="up">
            <%=newCount%> &nbsp; New Requests
        </div>

        <div class="down">
            <div class="l-down">
                <a href="show-orders-for-admin.jsp?orderStatus=NEW">View Details</a>
            </div>
            <a href="show-orders-for-admin.jsp?orderStatus=NEW"><img src="./images/greater-than-symbol.png"
                                                                     height="20px" alt=""></a>
        </div>
    </div>
    <div class="box yellow">
        <div class="up">
            <%=acceptedCount%> &nbsp; Accepted
        </div>

        <div class="down">
            <div class="l-down">
                <a href="show-orders-for-admin.jsp?orderStatus=ACCEPTED">View Details</a>
            </div>
            <a href="show-orders-for-admin.jsp?orderStatus=ACCEPTED"><img src="./images/greater-than-symbol.png"
                                                                          height="20px" alt=""></a>
        </div>
    </div>
    <div class="box orange">
        <div class="up">
            <%=inProgressCount%> &nbsp; In Progress
        </div>

        <div class="down">
            <div class="l-down">
                <a href="show-orders-for-admin.jsp?orderStatus=IN_PROGRESS">View Details</a>
            </div>
            <a href="show-orders-for-admin.jsp?orderStatus=IN_PROGRESS"><img src="./images/greater-than-symbol.png"
                                                                             height="20px" alt=""></a>
        </div>
    </div>
    <div class="box cyan">
        <div class="up">
            <%=finishedCount%> &nbsp; Finished
        </div>

        <div class="down">
            <div class="l-down">
                <a href="show-orders-for-admin.jsp?orderStatus=FINISHED">View Details</a>
            </div>
            <a href="show-orders-for-admin.jsp?orderStatus=FINISHED"><img src="./images/greater-than-symbol.png"
                                                                          height="20px" alt=""></a>
        </div>
    </div>
</div>
<div class="main">

    <table>
        <caption><%=orderStatus%> orders</caption>
        <thead>
        <th>Id</th>
        <th>User Name</th>
        <th>No of Cloths</th>
        <th>Cloth Items</th>
        <th>Status</th>
        <th>Order Date</th>
        <th>Finish Date</th>
        <th>Amount</th>
        <th>Action</th>
        </thead>
        <tbody>
        <%
            List<LaundryOrder> laundryOrders = laundryOrderRepo.getAllByStatus(status);
            for (LaundryOrder laundryOrder : laundryOrders) {
        %>
        <tr>
            <td><%=laundryOrder.getId()%>
            </td>
            <td><%=laundryOrder.getUserName()%>
            </td>
            <td><%=laundryOrder.getNumberOfCloths()%>
            </td>
            <td><%=laundryOrder.getClothItems().replace("[", "").replace("]", "")%>
            </td>
            <td><%=laundryOrder.getStatus()%>
            </td>
            <td><%=laundryOrder.getOrderDate()%>
            </td>
            <td>
                <%
                    if (laundryOrder.getFinishDate() != null) {
                        out.print(laundryOrder.getFinishDate());
                    } else {
                        out.print("Not Defined");
                    }
                %>
            </td>
            <td><%=laundryOrder.getAmount()%>
            </td>
            <%
                if (!laundryOrder.getStatus().equals(LaundryOrderStatus.FINISHED)) {
            %>
            <td>
                <a href="next-step-of-laundry-order?id=<%=laundryOrder.getId()%>"><img src="./images/arrow.png"
                                                                                       alt="Next Step" title="Next Step"
                                                                                       height="30px"></a>
            </td>
            <%
            } else {
            %>
            <td>Completed</td>
            <%}%>
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