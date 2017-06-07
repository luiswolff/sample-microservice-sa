<%--
  Created by IntelliJ IDEA.
  User: luis-
  Date: 06.06.2017
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--@elvariable id="model" type="de.wolff.sample.model.Patient"--%>
<html>
<head>
    <title>Patient ${model != null ? model.id : 'New'}</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/${model != null ? model.id : ''}${model != null ? '/edit.html' : 'createPatient.html'}" method="post">
        <table>
            <caption>Patient ${model != null ? model.id : 'New'}</caption>
            <tr>
                <th><label for="birthday">Birthday</label></th>
                <fmt:formatDate value="${model.birthday}" pattern="yyyy-MM-dd" var="birthday" />
                <td><input id="birthday" type="date" name="birthday" value="${birthday}" required="" /></td>
            </tr>
            <tr>
                <th><label for="gender">Gender</label></th>
                <td><select id="gender" name="gender" required>
                    <option value="M" ${model != null && model.gender == 'M' ? 'selected' : ''}>Male</option>
                    <option value="F" ${model != null && model.gender == 'F' ? 'selected' : ''}>Female</option>
                </select></td>
            </tr>
        </table>
        <input type="submit">
        <input type="submit" name="delete" value="Delete" formaction="${pageContext.request.contextPath}/${model.id}" >
    </form>
    <a href="${pageContext.request.contextPath}/patients.html">Back</a>
</body>
</html>
