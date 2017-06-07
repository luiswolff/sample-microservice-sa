<#-- @ftlvariable name="model" type="de.wolff.sample.model.Patient" -->
<!--suppress HtmlUnknownTarget -->
<html>
<head>
    <title>Patient <#if model != null>${model.id}<#else>New</#if></title>
</head>
<body>
    <form action="/frontend/<#if model??>${model.id}/edit.html<#else>createPatient.html</#if>" method="post">
        <table>
            <caption>Patient <#if model != null>${model.id}<#else>New</#if></caption>
            <tr>
                <th><label for="birthday">Birthday</label></th>
                <fmt:formatDate value="${model.birthday}" pattern="yyyy-MM-dd" var="birthday" />
                <td><input id="birthday" type="date" name="birthday" <#if model??>value="${model.year}"</#if> required="" /></td>
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
        <input type="submit" name="delete" value="Delete" formaction="/frontend/${model.id}" >
    </form>
    <a href="/frontend/patients.html">Back</a>
</body>
</html>
