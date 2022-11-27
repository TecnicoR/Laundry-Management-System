<%@ page import="com.laundry.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./styles/style.css">
    <title>Update Profile  - LMS</title>
</head>
<body>
<%
    User user = new User();
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login-staff.jsp");
    }
    else{
        user = (User) session.getAttribute("user");
    }
%>
    <div class="main-form">
        <h2>Update Your Profile</h2>
        <form action="update" method="post">
            <div class="form-input">
                <label for="name">Name</label>
                <input type="text" name="name" id="name" placeholder="enter here" value="<%=user.getName()%>" autofocus>
            </div>
            <div class="form-input">
                <label for="email">email</label>
                <input type="email" name="email" id="email" value="<%=user.getEmail()%>" placeholder="enter here" required>
            </div>
            <div class="form-input">
                <label for="phoneNumber">Phone number</label>
                <input type="text" name="phoneNumber" id="phoneNumber" value="<%=user.getPhoneNumber()%>" placeholder="enter here" pattern="[6789][0-9]{9}" required>
            </div>
            <div class="form-input">
                <label for="password">Password</label>
                <input type="password" name="password" id="password" placeholder="enter only to change or leave blank">
            </div>
            <input type="hidden" name="id" value="<%=user.getId()%>">
            <div class="form-input">
                <input type="submit" value="Update">
            </div>

        </form>
        <div class="extra-links">
            <a href="./user-dashboard.jsp.jsp">Go back to dashboard</a>
        </div>
    </div>
</body>
</html>
