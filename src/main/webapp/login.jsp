<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./styles/style.css">
    <title>Login - LMS</title>
</head>
<body>
    <div class="main-form">
        <h2>User Login to LMS</h2>
        <form action="#">
            <div class="form-input">
                <label for="username">Username</label>
                <input type="text" name="username" id="username" placeholder="enter here" autofocus required>
            </div>
            <div class="form-input">
                <label for="password">Password</label>
                <input type="text" name="password" id="password"  placeholder="enter here">
            </div>
            <div class="form-input">
                <input type="submit" value="login">
            </div>
        </form>
        <div class="extra-links">
            <a href="#">forgot password</a>
            <a href="/login-staff.html">login as staff</a>
        </div>
    </div>
</body>
</html>