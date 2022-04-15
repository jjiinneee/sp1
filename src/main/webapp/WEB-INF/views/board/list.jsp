<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <c:forEach items="${dtoList}"  var="board">
        <li>
            <span>${board.bno}</span>
            <span>${board.title}</span>
        </li>
    </c:forEach>
</ul>
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
