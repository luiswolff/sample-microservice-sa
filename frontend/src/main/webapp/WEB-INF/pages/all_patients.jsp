<%--
  Created by IntelliJ IDEA.
  User: luis-
  Date: 05.06.2017
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Patients</title>
</head>
<body>
    <table>
        <caption><h1>Patients in Register</h1></caption>
        <thead>
            <tr>
                <th>Identification</th>
                <th>Gender</th>
                <th>Birthday</th>
                <th>Count Diagnoses</th>
                <th>Count Medications</th>
            </tr>
        </thead>
        <tbody>
            <%--@elvariable id="model" type="java.util.List"--%>
            <%--@elvariable id="patient" type="de.wolff.sample.model.Patient"--%>
            <c:forEach var="patient" items="${model}">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/${patient.id}/edit.html">${patient.id}</a></td>
                    <td>${patient.gender == 'M' ? 'Male' : 'Female'}</td>
                    <td><fmt:formatDate value="${patient.birthday}" pattern="yyyy" /></td>
                    <td><a href="${pageContext.request.contextPath}/${patient.id}/diagnoses.html"><fmt:formatNumber value="${patient.diagnoses.size()}" /></a></td>
                    <td><a href="${pageContext.request.contextPath}/${patient.id}/medications.html"><fmt:formatNumber value="${patient.medications.size()}" /></a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<a href="${pageContext.request.contextPath}/createPatient.html">New Patient</a>
</body>
</html>
