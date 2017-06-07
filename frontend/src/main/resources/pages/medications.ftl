<#-- @ftlvariable name="model" type="de.wolff.sample.model.Patient" -->
<!--suppress HtmlUnknownTarget -->
<html>
<head>
    <title>Medications for Patient ${model.id}</title>
</head>
<body>
<table>
    <caption><h1>Patient ${model.id}</h1></caption>
    <tr>
        <th>Birthday</th>
        <td>${model.year}</td>
    </tr>
    <tr>
        <th>Gender</th>
        <td>${model.gender}</td>
    </tr>
</table>
<form action="/frontend/${model.id}/medications.html" method="post">
    <input type="hidden" name="count" value="${model.medications?size}" />
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
        <#list model.medications as medication>
            <tr>
                <td><input type="date" name="since" value="${medication.sinceAsString}" required=""  title="The date since the patientId gets this medication"/> </td>
                <td><input type="date" name="until" value="${medication.untilAsString!}" title="The date until the patientId will or has get this medication" /></td>
                <td><input type="text" name="name" value="${medication.name}" title="The name of this medication" /></td>
                <td><input type="text" name="activeIngredient" value="${medication.activeIngredient!}" title="The active ingredient of this medication" /></td>
                <td><input type="number" name="dose" value="${medication.dose!}" title="The doses of the medication" /></td>
                <td><input type="text" name="unit" value="${medication.unit!}" title="The unit of the medications dose" /></td>
                <td><input type="text" name="pzn" value="${medication.pzn!}" title="The central pharmaceutical number" /></td>
                <td><input type="text" name="atc" value="${medication.atc!}" title="The chemical classification" /></td>
            </tr>
        </#list>
        </tbody>
    </table>
    <input type="submit" />
</form>
<form action="/frontend/${model.id}/medications/add" method="post">
    <table>
        <caption><h2>Add Medication</h2></caption>
        <tr>
            <th><label for="since">Since</label></th>
        <td><input id="since" type="date" name="since" required=""  title="The date since the patientId gets this medication"/> </td>
        </tr>
        <tr>
            <th><label for="until">Until</label></th>
        <td><input id="until" type="date" name="until" title="The date until the patientId will or has get this medication" /></td>
        </tr>
        <tr>
            <th><label for="name">Name</label></th>
        <td><input id="name" type="text" name="name" title="The name of this medication" /></td>
        </tr>
        <tr>
            <th><label for="ingredient">Active Ingredient</label></th>
            <td><input id="ingredient" type="text" name="activeIngredient" title="The active ingredient of this medication" /></td>
        </tr>
        <tr>
            <th><label for="dose">Dose</label></th>
            <td><input id="dose" type="number" name="dose" title="The doses of the medication" /></td>
        </tr>
        <tr>
            <th><label for="unit">Unit</label></th>
            <td><input id="unit" type="text" name="unit" title="The unit of the medications dose" /></td>
        </tr>
        <tr>
            <th><label for="pzn">PZN</label></th>
            <td><input id="pzn" type="text" name="pzn" title="The central pharmaceutical number" /></td>
        </tr>
        <tr>
            <th><label for="atc">ATC</label></th>
            <td><input id="atc" type="text" name="atc" title="The chemical classification" /></td>
        </tr>
    </table>
    <input type="submit" />
</form>
<a href="/frontend/patients.html">Back</a>
</body>
</html>
