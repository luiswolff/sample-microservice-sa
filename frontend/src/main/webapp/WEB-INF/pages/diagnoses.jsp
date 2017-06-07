<%--
  Created by IntelliJ IDEA.
  User: luis-
  Date: 06.06.2017
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--@elvariable id="model" type="de.wolff.sample.model.Patient"--%>
<html>
<head>
    <title>Diagnoses for Patient ${model.id}</title>
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
    <form action="${pageContext.request.contextPath}/${model.id}/diagnoses.html" method="post">
        <input type="hidden" name="count" value="${model.diagnoses.size()}" />
        <table>
            <caption><h2>Diagnoses</h2></caption>
            <thead>
                <tr>
                    <th>Diagnosis</th>
                    <th>Date</th>
                    <th>ICD-10</th>
                    <th>ICD-Version</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="diagnosis" items="${model.diagnoses}" varStatus="count">
                    <tr>
                        <td>
                            <input type="text" name="diagnosis" value="${diagnosis.diagnosis}" required=""  title="The Name of the diagnosis"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${diagnosis.date}" pattern="yyyy-MM-dd" var="date" />
                            <input type="date" name="date" value="${date}" required="" title="The date when the diagnosis occurred" />
                        </td>
                        <td>
                            <input type="text" name="icd" value="${diagnosis.icd10}" title="The ICD-10 of the diagnosis if exists" />
                        </td>
                        <td>
                            <input type="text" name="icdVersion" value="${diagnosis.icdVersion}" title="The Vesion of the ICD-10 for the diagnosis" />
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <input type="submit" />
    </form>
    <form action="${pageContext.request.contextPath}/${model.id}/diagnoses/add" method="post">
        <table>
            <caption><h2>Add Diagnosis</h2></caption>
            <tr>
                <th><label for="diagnosis">Diagnosis</label></th>
                <td><input id="diagnosis" type="text" name="diagnosis" required=""/></td>
            </tr>
            <tr>
                <th><label for="date">Date</label></th>
                <td><input id="date" type="date" name="date" required=""/></td>
            </tr>
            <tr>
                <th><label for="icd10">ICD-10</label></th>
                <td><input id="icd10" type="text" name="icd"/></td>
            </tr>
            <tr>
                <th><label for="icdVersion">ICD-Version</label></th>
                <td><input id="icdVersion" type="text" name="icdVersion"/></td>
            </tr>
        </table>
        <input type="submit" />
    </form>
<a href="${pageContext.request.contextPath}/patients.html">Back</a>
</body>
</html>
