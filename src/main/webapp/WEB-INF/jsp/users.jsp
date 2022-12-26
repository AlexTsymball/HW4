<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All users</title>
    <h1>All users</h1>
</head>
<body>
<table border="1" cellpadding="5">
    <thead>
    <th>Login</th>
    <th>Name</th>
    </thead>
    <c:forEach var="user" items="${usersList}">
        <tr>
            <td>${user.login}</td>
            <td>${user.name}</td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/app/welcome">
    <input type=button value='Menu'>
</a>
</body>
</html>