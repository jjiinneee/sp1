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
<h2>fileupload test</h2>
<form action="/upload1"  method="post"  enctype="multipart/form-data">
    <input type="file"  name="files"  multiple/>
    <button>upload</button>
</form>

<div>
    <h2>Ajax Upload</h2>
    <input type="file" name="upload" class="uploadFile"  multiple/>
    <button class="uploadBtn">upload Ajax</button>
</div>

<style>
    .uploadResult{
      display: flex;
    }
    .uploadResult > div {
      margin: 3em;
      border: 1px solid red;
    }
</style>
<div class="uploadResult"></div>
<script>
    window.history.forward();
    function noBack(){
        window.history.forward();
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
    const uploadReult = document.querySelector(".uploadResult");
    
    
    uploadReult.addEventListener("click", (e) =>{
      if(e.target.getAttribute("class").indexOf("delBtn") < 0 ){
        return;
      }
      
      const btn = e.target;
      const link = btn.getAttribute("data-link");
      
      // alert(link);
      deleteToServer(link).then(result =>{
        btn.closest("div").remove();
      });
    },false);
    
    
    
    document.querySelector(".uploadBtn").addEventListener("click", (e) =>{
      const formObj = new FormData();
      
      const fileInput = document.querySelector(".uploadFile");
      console.log(fileInput.files);

      const cloneText = document.querySelector(".uploadFile").cloneNode();

      console.log()
      
      const files =  fileInput.files;
      
      for(let i =0; i<files.length; i++){
          console.log(files[i]);
          formObj.append("files", files[i]);
      }
      uploadToServer(formObj).then(resultArr =>{
           uploadReult.innerHTML +=  resultArr.map(result => `<div>
                <img src='/view?fileName=\${result.thumbnail}'/>
                <button data-link='\${result.link}' class="delBtn">x</button>
                \${result.orgFileName}</div>`).join(" ");
      });
    },false);
    
  async function deleteToServer(fileName){
    const options = {headers: {"Content-Type" :"application/x-www-sorm-urlencoded"}};
    const res = await axios.post("/delete", "fileName="+fileName, options);
    
    console.log(res.data);
  }
  async function uploadToServer(formObj){
     console.log("upload to server-----");
     console.log(formObj);

     const response = await axios({
        method : 'post',
        url :'/upload1',
        data : formObj,
        headers:{
          'content-Type' : 'multipart/form-data',
        }
     });
     return response.data;
  };
   
   
</script>
</body>
</html>
