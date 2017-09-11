<#-- @ftlvariable name="model" type="de.wolff.sample.model.Patient[]" -->
<!DOCTYPE html>
<!--suppress HtmlUnknownTarget -->
<html>
<head>
    <title>Patients</title>
</head>
<body>
    <table id="patients">
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
            <#list model as patient>
                <tr id="patients:${patient.id}">
                    <td><a href="/frontend/${patient.id}/edit.html">${patient.id}</a></td>
                    <td><#if patient.gender == 'M'>Male<#else>Female</#if> </td>
                    <td>${patient.birthday?string('yyyy')}</td>
                    <td><a href="/frontend/${patient.id}/diagnoses.html">${patient.diagnoses?size}</a></td>
                    <td><a href="/frontend/${patient.id}/medications.html">${patient.medications?size}</a></td>
                </tr>
            <#else >
                <tr>
                    <td colspan="5">
                        <p align="center">Keine Patienten in der Datenbank</p>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>
<a href="/frontend/createPatient.html">New Patient</a>
</body>
</html>
