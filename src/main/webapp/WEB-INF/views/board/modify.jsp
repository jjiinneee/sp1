<%--
  Created by IntelliJ IDEA.
  User: eunjin
  Date: 2022/04/26
  Time: 5:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<form class="modForm" action="/board/modify/${dto.bno}" method="post">
  <input type="hidden" name="page" value="${listDTO.page}">
  <input type="hidden" name="size" value="${listDTO.size}">
  <input type="hidden" name="type" value="${listDTO.type}">
  <input type="hidden" name="keyword" value="${listDTO.keyword}">
  <div>
    <div>
      <input type="text" name="bno" value="<c:out value="${dto.bno}"/>" readonly>
    </div>
    <div>
      <input type="text" name="title" value="<c:out value="${dto.title}"/>" >
    </div>
    <div>
      <textarea name="content" ><c:out value="${dto.content}"/></textarea>
    </div>
  </div>
</form>

<div>
  <button class="listBtn">리스트</button>
  <button class="modPostBtn">수정</button>
  <button class="delPostBtn">삭제</button>
</div>
<form class="actionForm" action="/board/remove/${bno}" method="post"></form>
</body>
<script>
  function sQuery(selector){
      return document.querySelector(selector);
  }
  
  const bno = ${dto.bno};
  
  const actionForm =  sQuery(".actionForm");
  
  document.querySelector(".listBtn").addEventListener("click", (e) =>{
      self.action = `/board/list${listDTO.link}`;
  },false);
  
  sQuery(".delPostBtn").addEventListener("click", (e) =>{
      actionForm.setAttribute("action", `/board/remobe/${bno}`);
      actionForm.submit();
  },false);
  
  sQuery(".modPostBtn").addEventListener("click", (e) =>{
      sQuery(".modForm").submit();
  },false);
</script>
</html>
