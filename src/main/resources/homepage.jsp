<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/homepage.css">
</head>
<body style="background-color: white;">
<div class="container welcome-section">
    <form action="http://localhost:8080", method="post">
        <table class="table table-borderless">
            <tr>
                <td class="header" style="color: #527c70;">
                    GAME ĐOÁN SỐ!
                </td>
            </tr>
            <tr>
                <td style="color: #f1a661;">
                    TÊN NGƯỜI CHƠI
                </td>
            </tr>
            <tr>
                <td>
                    <input name="username" class="form-control" type="text">
                </td>
            </tr>
            <tr>
                <td>
                    <button class="btn btn-login" type="submit">Chơi</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>