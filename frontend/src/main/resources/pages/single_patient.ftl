<#-- @ftlvariable name="model" type="de.wolff.sample.model.Patient" -->
<!--suppress HtmlUnknownTarget -->
<html>
<head>
    <title>Patient <#if model??>${model.id}<#else>New</#if></title>
</head>
<body>
    <form action="/frontend/<#if model??>${model.id}/edit.html<#else>createPatient.html</#if>" method="post">
        <table>
            <caption>Patient <#if model??>${model.id}<#else>New</#if></caption>
            <tr>
                <th><label for="birthday">Birthday</label></th>
                <td><input id="birthday" type="date" name="birthday" <#if model??>value="${model.birthday?string('yyyy-MM-dd')}"</#if> required="" /></td>
            </tr>
            <tr>
                <th><label for="gender">Gender</label></th>
                <td><select id="gender" name="gender" required>
                    <option value="M" <#if model?? && model.gender == 'M'>selected</#if>>Male</option>
                    <option value="F" <#if model?? && model.gender == 'F'>selected</#if>>Female</option>
                </select></td>
            </tr>
        </table>
        <input type="submit">
        <#if model??><input type="submit" name="delete" value="Delete" formaction="/frontend/${model.id}" /></#if>
    </form>
    <a href="/frontend/patients.html">Back</a>
</body>
</html>
