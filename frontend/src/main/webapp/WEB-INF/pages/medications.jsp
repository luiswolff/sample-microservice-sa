<%--
  Created by IntelliJ IDEA.
  User: luis-
  Date: 06.06.2017
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--@elvariable id="model" type="de.wolff.sample.model.Patient"--%>
<html>
<head>
    <title>Medications for Patient ${model.id}</title>
</head>
<body>
<table>
    <caption><h1>Patient ${model.id}</h1></caption>
    <tr>
        <th>Birthday</th>
        <td><fmt:formatDate value="${model.birthday}" pattern="yyyy" /> </td>
    </tr>
    <tr>
        <th>Gender</th>
        <td>${model.gender}</td>
    </tr>
</table>
<form action="${pageContext.request.contextPath}/${model.id}/medications.html" method="post">
    <input type="hidden" name="count" value="${model.medications.size()}" />
    <table>
        <caption><h2>Medications</h2></caption>
        <thead>
        <tr>
            <th>Since</th>
            <th>Until</th>
            <th>Name</th>
            <th>Active Ingredient</th>
            <th>Dose</th>
            <th>Unit</th>
            <th>Central Pharmaceutical Number</th>
            <th>Classification</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="medication" items="${model.medications}">
            <tr>
                <fmt:formatDate value="${medication.since}" pattern="yyyy-MM-dd" var="since" />
                <td><input type="date" name="since" value="${since}" required=""  title="The date since the patientId gets this medication"/> </td>
                <fmt:formatDate value="${medication.until}" pattern="yyyy-MM-dd" var="until" />
                <td><input type="date" name="until" value="${until}" title="The date until the patientId will or has get this medication" /></td>
                <td><input type="text" name="name" value="${medication.name}" title="The name of this medication" /></td>
                <td><input type="text" name="activeIngredient" value="${medication.activeIngredient}" title="The active ingredient of this medication" /></td>
                <td><input type="number" name="dose" value="${medication.dose}" title="The doses of the medication" /></td>
                <td><input type="text" name="unit" value="${medication.unit}" title="The unit of the medications dose" /></td>
                <td><input type="text" name="pzn" value="${medication.pzn}" title="The central pharmaceutical number" /></td>
                <td><input type="text" name="atc" value="${medication.atc}" title="The chemical classification" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="submit" />
</form>
<form action="${pageContext.request.contextPath}/${model.id}/medications/add" method="post">
    <table>
        <caption><h2>Add Medication</h2></caption>
        <tr>
            <th><label for="since">Since</label></th>
        <fmt:formatDate value="${medication.since}" pattern="yyyy-MM-dd" var="since" />
        <td><input id="since" type="date" name="since" value="${since}" required=""  title="The date since the patientId gets this medication"/> </td>
        </tr>
        <tr>
            <th><label for="until">Until</label></th>
        <fmt:formatDate value="${medication.until}" pattern="yyyy-MM-dd" var="until" />
        <td><input id="until" type="date" name="until" value="${until}" title="The date until the patientId will or has get this medication" /></td>
        </tr>
        <tr>
            <th><label for="name">Name</label></th>
        <td><input id="name" type="text" name="name" value="${medication.name}" title="The name of this medication" /></td>
        </tr>
        <tr>
            <th><label for="ingredient">Active Ingredient</label></th>
            <td><input id="ingredient" type="text" name="activeIngredient" value="${medication.activeIngredient}" title="The active ingredient of this medication" /></td>
        </tr>
        <tr>
            <th><label for="dose">Dose</label></th>
            <td><input id="dose" type="number" name="dose" value="${medication.dose}" title="The doses of the medication" /></td>
        </tr>
        <tr>
            <th><label for="unit">Unit</label></th>
            <td><input id="unit" type="text" name="unit" value="${medication.unit}" title="The unit of the medications dose" /></td>
        </tr>
        <tr>
            <th><label for="pzn">PZN</label></th>
            <td><input id="pzn" type="text" name="pzn" value="${medication.pzn}" title="The central pharmaceutical number" /></td>
        </tr>
        <tr>
            <th><label for="atc">ATC</label></th>
            <td><input id="atc" type="text" name="atc" value="${medication.atc}" title="The chemical classification" /></td>
        </tr>
    </table>
    <input type="submit" />
</form>
<a href="${pageContext.request.contextPath}/patients.html">Back</a>
</body>
</html>
