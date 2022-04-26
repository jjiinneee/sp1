<%--
  Created by IntelliJ IDEA.
  User: eunjin
  Date: 2022/04/26
  Time: 11:45 AM
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
  <ul class="replyUL">
  
  </ul>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
    async function getReplyList(bno){
        <%--return axios.get(`/replies/list/${bno}`)--%>
        <%--    .then(res => res.data);--%>
       try{
           const res = await axios.get(`/replies/list/${bno}`);
           const data = res.data;
           return data;
       }catch (e){
           return e;
       }
       
        
    }
    const  bno = ${dto.bno};
    // getReplyList(bno)
    //     .then(data => console.log(data))
    //     .catch(err => console.log(err));

    getReplyList(bno)
        .then( arr => {
            const  liStr  = arr.map(replyDTO => `<li>\${replyDTO.rno}<li>-- \${replyDTO}`).join(" ");
            document.querySelector(".replyUL").innerHTML = liStr;
        })
        .catch(err => console.log(err));
</script>
</html>
