<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script>
    <%--const result = ${result == null ? '' : result};--%>
    const result = '${result}';
    console.log("result" + result);

    if(result !== ''){
        alert("처리되었습니다.")
    }
</script>
</body>
</html>
