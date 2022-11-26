<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./styles/style.css">
    <title>Register - LMS</title>
</head>
<body>
<div class="main-form">
    <%
        if (session.getAttribute("error") != null) {
            out.print("<p style='margin-top:20px; font-size: large; color: red;'>" + session.getAttribute("error") + "</p>");
            session.removeAttribute("error");
        }
        if (session.getAttribute("success") != null) {
            out.print("<p style='margin-top:20px; font-size: large; color: green;'>" + session.getAttribute("success") + "</p>");
            session.removeAttribute("success");
        }
    %>
    <h2>Register with LMS</h2>
    <form action="user-signup" method="post">
        <div class="form-input">
            <label for="name">Name</label>
            <input type="text" name="name" id="name" placeholder="enter here" required autofocus>
        </div>
        <div class="form-input">
            <label for="email">email</label>
            <input type="email" name="email" id="email" placeholder="enter here" required>
        </div>
        <div class="form-input">
            <label for="phoneNumber">Phone number</label>
            <input type="text" name="phoneNumber" id="phoneNumber" placeholder="enter here" required
                   pattern="[6789][0-9]{9}">
        </div>
        <div class="form-input">
            <label for="password">Password</label>
            <input type="password" name="password" id="password" placeholder="enter here" required>
        </div>
        <div class="form-input">
            <input type="submit" value="register">
        </div>
    </form>
    <div class="extra-links">
        <a href="./login.jsp">already registered? login</a>
    </div>
</div>
</body>
</html>