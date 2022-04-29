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
  <ul class="pageUL">
  
  </ul>
</div>
</body>
<style>
  .pageUL {
    display: flex;
  }
  
  .pageUL li {
    list-style: none;
    margin: 0.1em;
    border: 1px solid blue;
  }
  
  .pageUL .current {
    background-color: blue;
  }
  
</style>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="/resources/js/reply.js"></script>
<script>
  const bno = ${dto.bno};
  const replyUL = qs(".replyUL");
  const pageUL = qs(".pageUL");
  <%--const replyCount = ${dto.replyCount};--%>
  let replyCount = ${dto.replyCount};
  const replySize = 10;
  const replyNum = 1;
  
  function printPage(targetPage){
    const lastPageNum = Math.ceil(replyCount/replySize);
    
    // let endPageNum = Math.ceil(lastPageNum/10) *10;
    let endPageNum = Math.ceil(targetPage/10) *10;
    const startPageNum = endPageNum -9;

    endPageNum = lastPageNum < endPageNum ? lastPageNum: endPageNum;
    console.log("pageParam", pageParam);
    
    if(startPageNum >1 ) {
      str = `<li data-num=\${startPageNum - 1}>\${startPageNum - 1} 이전</li>`;
    }
    // else {
    //   str = `<li>\${endPageNum} 다음</li>`;
    // }
    
    let str = '';
    for(let i = startPageNum; i <= endPageNum;i++){
       str += `<li data-num=\${i} class="\${i === pageParam? 'current' : ''}">\${i}</li>`;
       
    }
    
    if(lastPageNum > endPageNum){
      str += `<li data-num=\${startPageNum + 1}>\${startPageNum + 1} 다음</li>`;
    }
    pageUL.innerHTML = str;
  }
  
  // replyService.setReplyCount =  function setReplyCount(num){
  //   console.log("num", num);
  //   replyCount = num;
  // }
  console.log("==============");
  
  // replyService.getList(bno,printReplies);
  function getServerList(param){
    replyService.getList(param, (replyArr) =>{
      const liArr = replyArr.map(reply => `<li>AAA</li>`);
      replyUL.innerHTML = liArr.join(" ");
      printPage(param.page);
    });
  };
  
   function addServerReply(){
     replyService.addReply(
         { bno:bno ,
           replyText: qs("input[name='replyText']").value,
           replier: qs("input[name='replier']").value},
            replySize,
         (param) => {
           getServerList(param);
         }
     );
   };
   
   qsAddEvent(".addReplyBtn", "click" , addServerReply);
   qsAddEvent(".pageUL", "click", (e,realTarget) => {
     // alert("AAAA");
     const num = realTarget.getAttribute("data-num");
     console.log("num"+num);
     getServerList({bno :bno,page : num, size: replySize});
   },"li");
   
   //after loading ,즉시실행(무조권 한번실행)
  const pageParam = Math.ceil(replyCount/replySize);
  console.log(pageParam);
  getServerList({bno :bno,page : pageParam, size: replySize});
  
  
  // function printReplies(replyArr){
  //   const liArr = replyArr.map(reply => `<li>AAA</li>`);
  //   replyUL.innerHTML = liArr.join(" ");
  // }
</script>
</body>
</html>
