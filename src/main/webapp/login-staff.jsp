<%@ page import="com.laundry.model.Admin" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./styles/style.css">
    <title>Login - LMS</title>
</head>
<%
    if((Admin)session.getAttribute("user") != null){
        response.sendRedirect("admin-dashboard.jsp");
    }
%>

<body>
    <div class="main-form">
        <%
            if (session.getAttribute("error") != null) {
                out.print("<p style='margin-top:20px; font-size: large; color: red;'>"+session.getAttribute("error")+"</p>");
                session.removeAttribute("error");
            }
            if (session.getAttribute("success") != null) {
                out.print("<p style='margin-top:20px; font-size: large; color: green;'>"+session.getAttribute("success")+"</p>");
                session.removeAttribute("success");
            }
        %>
        <h2>Staff Login to LMS</h2>
        <form action="admin-login" method="post">
            <div class="form-input">
                <label for="email">Email</label>
                <input type="email" name="email" id="email" placeholder="enter here" autofocus required>
            </div>
            <div class="form-input">
                <label for="password">Password</label>
                <input type="password" name="password" id="password"  placeholder="enter here" required>
            </div>
            <div class="form-input">
                <input type="submit" value="login">
            </div>
        </form>
        <div class="extra-links">
            <a href="./login.jsp">login as user</a>
        </div>
    </div>
</body>
</html>