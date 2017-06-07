<#-- @ftlvariable name="model" type="de.wolff.sample.model.Patient" -->
<!--suppress HtmlUnknownTarget -->
<html>
<head>
    <title>Diagnoses for Patient ${model.id}</title>
</head>
<body>
    <table>
        <caption><h1>Patient ${model.id}</h1></caption>
        <tr>
            <th>Birthday</th>
            <td>${model.year}"</td>
        </tr>
        <tr>
            <th>Gender</th>
            <td>${model.gender}</td>
        </tr>
    </table>
    <form action="/frontend/${model.id}/diagnoses.html" method="post">
        <input type="hidden" name="count" value="${model.diagnoses?size}" />
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
                <#list model.diagnoses as diagnosis >
                    <tr>
                        <td>
                            <input type="text" name="diagnosis" value="${diagnosis.diagnosis}" required=""  title="The Name of the diagnosis"/>
                        </td>
                        <td>
                            <input type="date" name="date" value="${diagnosis.dateAsString}" required="" title="The date when the diagnosis occurred" />
                        </td>
                        <td>
                            <input type="text" name="icd" value="${diagnosis.icd10}" title="The ICD-10 of the diagnosis if exists" />
                        </td>
                        <td>
                            <input type="text" name="icdVersion" value="${diagnosis.icdVersion}" title="The Vesion of the ICD-10 for the diagnosis" />
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
        <input type="submit" />
    </form>
    <form action="$/frontend/${model.id}/diagnoses/add" method="post">
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
<a href="frontend/patients.html">Back</a>
</body>
</html>
