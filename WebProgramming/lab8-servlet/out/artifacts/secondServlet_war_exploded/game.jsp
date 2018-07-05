<%--
  Created by IntelliJ IDEA.
  User: computer
  Date: 20.05.2018
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GameBoard</title>
</head>
<body>
<h1>Welcome ${username} !</h1>
<p>Your score is: </p>
<p>The opponent's score is</p>

<table border="1">
    <c:forEach var="line" items="${gameBean.gridLines}">
        <tr>
            <c:forEach var="cell" items="${gameBean.getGridStatus(line)}">
                <td>
                    <c:choose>
                        <c:when test="${cell.state == 'X'}">
                            <img src="img/state_x.png" alt="X"/>
                        </c:when>
                        <c:when test="${cell.state == 'O'}">
                            <img src="img/state_o.png" alt="O"/>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${winner == null}">
                                <a href="gameServlet?Line=${cell.line}&Col=${cell.col}">
                            </c:if>
                            <img src="img/state_null.png" alt="null"/>
                            <c:if test="${winner == null}">
                                </a>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
</body>
</html>
