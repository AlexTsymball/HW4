<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <h1>Please login to continue</h1>
</head>
<body>
<p style="color: red">
    <b>${error}</b>
</p>
<form:form modelAttribute="user">
    <table>
        <tr>
            <td>Login:</td>
            <td><form:input type="text" path="login"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><form:input type="password" path="password"/></td>
        </tr>
        <td colspan="2">
            <input type="submit" value="Login" />
        </td>
    </table>
</form:form>
</body>
</html>


