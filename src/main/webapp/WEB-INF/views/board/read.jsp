<%--
  Created by IntelliJ IDEA.
  User: eunjin
  Date: 2022/04/28
  Time: 11:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<textarea><c:out value="${dto.content}"/> </textarea>
<div>
  <button class="listBtn">리스트</button>
  <button class="modBtn">수정/삭제</button>
</div>

<div>
  <div>
    <input type="text" name="replyText" value="샘플 댓글">
  </div>
  <div>
    <input type="text" name="replier" value="testUser">
  </div>
  <div>
    <button type="button" class="addReplyBtn">댓글추가</button>
  </div>
</div>
<div>
  <ul class="replyUL">
  
  </ul>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="/resources/js/reply.js"></script>
<script>
  const bno = ${dto.bno};
  const replyUL = qs(".replyUL");
  const replyCount = ${dto.replyCount};
  
  // replyService.getList(bno,printReplies);
  function getServerList(){
    replyService.getList({bno}, (replyArr) =>{
      const liArr = replyArr.map(reply => `<li>AAA</li>`);
      replyUL.innerHTML = liArr.join(" ");
    });
  };
  
   function addServerReply(){
     replyService.addReply(
         { bno:bno ,
           replyText: qs("input[name='replyText']").value,
           replier: qs("input[name='replier']").value},
         () => {
           alert("!")
           getServerList();
         }
     );
   };
   
   qsAddEvent(".addReplyBtn", "click" , addServerReply);
  
  // function printReplies(replyArr){
  //   const liArr = replyArr.map(reply => `<li>AAA</li>`);
  //   replyUL.innerHTML = liArr.join(" ");
  // }
</script>
</body>
</html>
