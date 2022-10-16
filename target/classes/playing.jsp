<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/homepage.css">
    <link rel="stylesheet" href="resources/css/play_info.css">

</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col col-lg-4 w-100 user-section middle">
            <form action="http://localhost:8080/playing", method="post">
                <table class="table table-borderless">
                    <tr>
                        <td class="header" style="color: #527c70; font-size:30px;">
                            Thành tích cao nhất
                        </td>
                    </tr>
                    <tr>
                        <td style="color: #f1a661; font-size:50px;">
                            <c:if test="${gameBestGuess != 0}">
                                <c:out value="${gameBestGuess}"></c:out>
                            </c:if>
                            <c:if test="${gameBestGuess == 0}">

                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td style="color: #f1a661;">
                            Người chơi dẫn đầu: <c:out value="${bestUser}"></c:out>
                        </td>
                    </tr>
                    <tr>
                        <td style="color: #f1a661;">
                            Người chơi hiện tại: <c:out value="${username}"></c:out>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button class="btn btn-success" type="submit" name="reset" value="true">Reset</button>
                        </td>
                    </tr>
                    <tr>

                    </tr>
                    <tr>

                    </tr>
                </table>
            </form>
        </div>
        <div class="col col-lg-8 w-100 play-section middle">
            <form action="http://localhost:8080/playing", method="post">
                <table class="table table-borderless">
                    <tr>
                        <td class="header" style="color: #f1a661; font-size:30px;">
                            <c:if test="${correct == false}">
                                <c:out value="${falseAnswer}"></c:out>
                            </c:if>
                            <c:if test="${correct == null}">
                                Nhập số ở đây
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input name="user_guess" class="form-control form-control-playing" type="number">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <c:if test="${correct == false}">
                                <button class="btn btn-playing" type="submit">Thử lại</button>
                            </c:if>
                            <c:if test="${correct == null}">
                                <button class="btn btn-playing" type="submit">Đoán</button>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td style="color: #f1a661; font-style: italic; font-weight:bold;">
                            (Gợi ý: <c:out value="${gameAnswer}"></c:out>)
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>
