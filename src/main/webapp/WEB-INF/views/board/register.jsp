<%--
  Created by IntelliJ IDEA.
  User: eunjin
  Date: 2022/04/13
  Time: 12:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body onload="noBack();" onpageshow="if(event.persisted) noBack();"  onunload="">
<h1>Register page</h1>
<form action="/board/register" method="post">
    <input type="text" name="title">
    <button>Click</button>
</form>

<script>
    window.history.forward();
    function noBack(){
        window.history.forward();
    }
</script>
</body>
</html>
