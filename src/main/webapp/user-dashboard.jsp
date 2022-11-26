<%@ page import="com.laundry.model.User" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./styles/admin-dashboard-style.css">
    <title>Dashboard</title>
</head>

<body>
    <div class="navbar">
        <div class="title">
            <h1>LaundryMS</h1>
        </div>
        <div class="menu">
            <ul>
             <li><a href="#">Request Service</a></li>
                 <!-- <li><a href="#">Pending Requests</a></li>
                <li><a href="#">Accepted Requests</a></li>
                <li><a href="#">Progress Requests</a></li>
                <li><a href="#">Completed Requests</a></li> -->
            </ul>
        </div>
        <div class="profile">
            <h3><a href="#"><%=((User)session.getAttribute("user")).getName()%></a> </h3>
            <a href="#"><img src="./images/user.png" height="30px" title="visit profile" alt=""></a>
            <a id="logout-a" href="#"><img src="./images/exit.png" height="20px" title="logout"></a>
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
                <tr>
                    <td>Top wear ( men )</td>
                    <td>20</td>
                </tr>
            </tbody>
        </table>
        <div class="tiles">
            <div class="box orange">
                <div class="up">
                    In Progress
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
                    Finished
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