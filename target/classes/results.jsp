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
                        <td>
                            <button class="btn btn-success" type="submit" name="new_player" value="true">New Player</button>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button class="btn btn-success" type="submit" name="ranking" value="true">Ranking</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="col col-lg-8 w-100 play-section middle">
            <form action="http://localhost:8080/playing", method="post">
                <table class="table table-borderless">
                    <tr>
                        <td class="header" style="color: #90b77d; font-size:30px;">
                            Chính xác!
                        </td>
                    </tr>
                    <tr>
                        <td class="display-result">
                            <p>
                                Đáp án chương trình:
                                <c:out value="${correctAnswer}"></c:out>
                            </p>

                        </td>
                    </tr>
                    <tr>
                        <td class="display-result">
                            <p>
                                Số lần thử của bạn:
                                <c:out value="${userGuess}"></c:out>
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <td class="display-result">
                            <p>
                                Số lần thử tốt nhất của bạn:
                                <c:out value="${userBestGuess}"></c:out>
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button class="btn btn-playing" type="submit" name="play_again" value="true">Chơi lại</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>
