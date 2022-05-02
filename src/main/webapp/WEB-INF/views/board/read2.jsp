<%--
  Created by IntelliJ IDEA.
  User: eunjin
  Date: 2022/05/02
  Time: 9:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
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
<ul class="replyUL">

</ul>
<ul class="pageUL">

</ul>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
  //------------------------------------------------------

  let initState = {
    bno :${dto.bno},
    replyArr :[],
    replyCount :0,
    size :10,
    pageNum : 1,
  };


  document.querySelector(".addReplyBtn").addEventListener("click", (e) =>{
    const replyObj = {
      bno :${dto.bno},
      replyText : document.querySelector("input[name='replyText']").value,
      replier :document.querySelector("input[name='replier']").value
    }
    replyService.addServerReply(replyObj);
    
  },false);

  pageUL.addEventListener("click",(e) =>{
    if(e.target.tagName != "LI"){
      return;
    }

    const dataNum = parent(e.target.getAttribute("data-num"));
    replyService.setState({pageNum :dataNum});
    
  },false);


  const replyUL = document.querySelector(".replyUL");
  const pageUL = document.querySelector(".pageUL");
  
  
  function render(obj){
    console.dir("obje", obj);
    
    function printList(){
      const arr = obj.replyArr;
      replyUL.innerHTML  =arr.map(reply => `<li>\${reply.rno}</li>`).join(" ");
      
      // replyUL.innerHTML = "<li>Print List</li>";
    }
    
    function printPage(){
      const currentPage = obj.pageNum;
      const size = obj.size;
      
      let endPage = Math.ceil(currentPage / 10);
      const startPage = endPage -9;
      const prev = startPage != 1;
      endPage = obj.replyCount < endPage * obj.size ? Math.ceil(obj.replyCount / obj.size) : endPage;
      
      const next =  obj.replyCount > endPage * obj.size;
      
      let str = '';
      if(prev){
        str += `<li data-num=\${startPage - 1}>이전<li>`;
      }
      
      for (let i =startPage; i<= endPage; i++){
        str+= `<li data-num=\${i}>\${i}</li>`;
      }

      if(prev){
        str += `<li data-num=\${endPage + 1}>다음<li>`;
      }

      console.log(startPage)
      
      pageUL.innerHTML = str;
    }

    printList();
    printPage();
  }

  //------------------------------------------------------
  
  const replyService = (function(initState,callbackFn ){
    // state : initState,
    // setState:function(changedState, callback){
    //   console.log("chamgedState",changedState);
    //   state = {...state,changedState};   //전개 연산자
    //   // state = changedState;
    //
    // },
    // getServerList: function(){
    // }
    
    let state = initState;
    const callback = callbackFn;
    const setState = (newState) => {
      state =  {...state, ...newState};
      console.log(state);
      
      //newState 안에 replyCount 속성이 있다면 혹은 newState안에 pageNum이 있다면
      if(newState.replyCount || newState.pageNum){
          getServerList();
      }
      callback(state);
    }
    
    async function getServerList(){
      
     const pageNum = Math.ceil(state.replyCount/state.size);
      
      const paramObj = {page: pageNum, size: state.size};
      const res = await axios.get(`/replies/list/\${state.bno}`,{params:paramObj});
      
      console.log(res.data);
      
      state.pageNum = pageNum;
      setState({replyArr :res.data});
    }
    
    async function addServerReply(replyObj){
      
      const res = await axios.post(`/replies/` , replyObj);
      const data =  res.data;
      
      console.log("dat", data);
      
    }
    return {setState,addServerReply};
    
    
  })(initState,render);
  
  replyService.setState({replyCount: ${dto.replyCount}},render);
  // replyService.setState({pageNum:11},render);

</script>
</body>
</html>
