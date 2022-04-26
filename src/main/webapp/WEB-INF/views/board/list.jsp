<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<div class="searchDiv">
    <select class="type">
        <option value="">---</option>
        <option value="t" ${listDTO.type == "t"?"selected" :""}>제목</option>
        <option value="tc" ${listDTO.type == "tc"?"selected" :""}>제목내용</option>
        <option value="tcw" ${listDTO.type == "tcw"?"selected" :""}>제목내용작성자</option>
    </select>
    <input type="text" name="keyword" value="${listDTO.keyword}"/>
    <button class="searchBtn">search</button>
</div>

<ul class="dtoList">
  <c:forEach items="${dtoList}"  var="board">
    <li>
      <span>${board.bno}</span>
      <span><a class="dtoLink" href="/board/read/${board.bno}">${board.title}</a></span>
        
        <%--            <span><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}" /></span>--%>
        <%--            <fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd"/>--%>
    </li>
  </c:forEach>
</ul>
<style>
  .pagination {
    display: flex;
  }
  .pagination .page-item {
    margin: 0.3em;
    list-style: none;
  }
</style>
<ul class="pagination">
  <li class="page-item">
    <a class="page-link" href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
    </a>
  </li>
  <%--${pageMaker}--%>
  <c:forEach begin="${pageMaker.start}" end="${pageMaker.end}" var="num">
    <li class="page-item"><a class="page-link" href="${num}">${num}</a></li>
  </c:forEach>
  
  <li class="page-item">
    <a class="page-link" href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
    </a>
  </li>
</ul>
<form class="actionForm" action="/board/list" method="get">
    <input type="hidden" name="page" value="${listDTO.page}">
    <input type="hidden" name="size" value="${listDTO.size}">
    <input type="hidden" name="type" value="${listDTO.type == null ? '':listDTO.type}">
    <input type="hidden" name="keyword" value="${listDTO.keyword == null ? '':listDTO.keyword}">
</form>
<script>
    
    // const linkTags = document.querySelectorAll(".page-link");
    const linkDiv = document.querySelector(".pagination");
    console.log("ddd",linkTags);
    const actionForm = document.querySelector(".actionForm");
    
    document.querySelector(".dtoList").addEventListener("click", (e) =>{
        e.preventDefault();
        e.stopPropagation();
        const target = e.target;
        if(target.getAttribute("class").indexOf("dtoLink") < 0){
            return;
        }
        const url = target.getAttribute("href");
        // alert(url)
        actionForm.setAttribute("action", url);
        actionForm.submit();
    },false);
    
    //버블링만하고 캡쳐링은 false 다.
    linkDiv.addEventListener("click", (e) => {
        e.stopPropagation();
        e.preventDefault();
        
        const target = e.target;
        if(target.getAttribute("class") !== "page-link"){
          return;
        }
        
        const pageNum = target.getAttribute("href");
        actionForm.querySelector("input[name='page']").value = pageNum;
        actionForm.setAttribute("action" ,"/board/list");
        actionForm.submit();
        
    },false);
    
    
    // for (const tag of linkTags) {
    //     tag.addEventListener("click", (e) => {
    //         e.preventDefault();
    //         const pageNum = tag.getAttribute("href");
    //
    //
    //         actionForm.querySelector("input[name='page']").value = pageNum;
    //     },false);
    // };
    
    document.querySelector(".searchBtn").addEventListener("click" , (e) =>{
        
        const type = document.querySelector(".searchDiv .type").value;
        const keyword = document.querySelector(".searchDiv input[name='keyword']").value;
        
        actionForm.setAttribute("action", "/board/list");
        actionForm.querySelector("input[name='page']").value = 1;
        actionForm.querySelector("input[name='type']").value = type;
        actionForm.querySelector("input[name='keyword']").value = keyword;
        actionForm.submit();
        console.log(type,keyword);
    },false);
    
    
    const result = '${result}';
    
    if(result !== ''){
        alert("처리되었습니다.");
    }
    <%--const result = ${result == null ? '' : result};--%>
    <%--const result = '${result}';--%>
    <%--console.log("result" + result);--%>
    
    <%--if(result !== ''){--%>
    <%--    alert("처리되었습니다.")--%>
    <%--}--%>
</script>
</body>
</html>
