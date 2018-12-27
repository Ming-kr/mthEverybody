<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	.list-group-item>img{
		width: 20px;
		height: 20px;
		margin-right: 10px;
	}

	.list-group.listHome>.list-group-item {
    background-color: #f1f1f1;
  }

  .list-group.listHome>.list-group-item:hover {
    background-color: #fffcfc;
  }

  
</style>
<div class="list-group listHome">
		<c:if test='${sessionLoginedData == null}'>
          <a href="${pageContext.request.contextPath}/loginForm" class="list-group-item"> <img src="${pageContext.request.contextPath}/resources/img/sidebar/32399bentobox_98893.svg" alt="">로그인</a>
        </c:if>
        <c:if test='${sessionLoginedData != null}'>
          <a href="${pageContext.request.contextPath}/actionLogout" class="list-group-item"> <img src="${pageContext.request.contextPath}/resources/img/sidebar/32399bentobox_98893.svg" alt="">로그아웃</a>
          <a href="${pageContext.request.contextPath}/memberMyInfo" class="list-group-item"> <img src="${pageContext.request.contextPath}/resources/img/sidebar/Cake_ChocolateCake_26374.png" alt="">내 정보</a>
        </c:if>
          <a href="${pageContext.request.contextPath}/aroundConvenience" class="list-group-item"> <img src="${pageContext.request.contextPath}/resources/img/sidebar/Coffee_to_Go_icon-icons.com_68763.svg" alt="">내 주변 편의점</a>
          
      	<c:if test="${sessionLoginedData != null && sessionLoginedData.member_code=='CUSTOMER'}">
        	<a class="list-group-item" style="cursor: pointer;" onclick="checkPassword('${pageContext.request.contextPath}/requestSellerForm')" > <img src="${pageContext.request.contextPath}/resources/img/sidebar/Porridge_icon-icons.com_68703.svg" alt="">판매자 회원 신청</a>
       	</c:if>
       	<c:if test="${sessionLoginedData != null && sessionLoginedData.member_code=='REQ_SELLER'}">
        	<a class="list-group-item" style="color:grey; cursor:default;"> <img src="${pageContext.request.contextPath}/resources/img/sidebar/Porridge_icon-icons.com_68703.svg" alt="">판매자 회원 승인중</a>
       	</c:if>
       	<c:if test='${sessionLoginedData != null && sessionLoginedData.member_code == "SELLER"}'>
        	<a href="${pageContext.request.contextPath}/seller/sellProductMyList" class="list-group-item"> <img src="${pageContext.request.contextPath}/resources/img/sidebar/Porridge_icon-icons.com_68703.svg" alt="">판매자 페이지</a>
        </c:if>
        <c:if test='${sessionLoginedData != null && sessionLoginedData.member_code == "ADMIN"}'>
        	<a href="${pageContext.request.contextPath}/admin/memberList" class="list-group-item"> <img src="${pageContext.request.contextPath}/resources/img/sidebar/Porridge_icon-icons.com_68703.svg" alt="">관리자 페이지</a>
		</c:if>
		<a href="#" class="list-group-item line" style="text-align: center;">- 게시판 -</a>
		<a href="${pageContext.request.contextPath}/yummyboard/yummyBoard" class="list-group-item"> <img src="${pageContext.request.contextPath}/resources/img/sidebar/French_Fries_icon-icons.com_68745.svg" alt="">맛있는 편의점</a>
          <a href="${pageContext.request.contextPath}/freeboard/freeBoard" class="list-group-item"><img src="${pageContext.request.contextPath}/resources/img/sidebar/Hamburger_icon-icons.com_68741.svg" alt="">자유게시판 </a>
</div>

<div class="list-group">
	<c:if test='${sessionLoginedData != null }'>
		<h4>button for testing</h4>
		<a href="${pageContext.request.contextPath}/updateCustomer"
			class="list-group-item">구매자로 변경</a>
		<a href="${pageContext.request.contextPath}/updateSeller"
			class="list-group-item">판매자로 변경</a>
		<a href="${pageContext.request.contextPath}/updateAdmin"
			class="list-group-item">관리자로 변경</a>
	</c:if>
</div>

<script type="text/javascript">
//비밀번호 체크 후 페이지 이동
function checkPassword(url)
{
		//프롬트 창으로 입력값 받아와서 저장
    	var inputPassword = prompt("패스워드를 입력해주세요.","");
    	
		//비밀번호 확인 Ajax
    	var xmlhttp = new XMLHttpRequest();
    	
    	xmlhttp.onreadystatechange = function(){
    		
    		if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
    		{
    			//true: 회원 비밀번호 일치
    			var result = xmlhttp.responseText;
    			if(result == "true") { location.href=url; }
    			else { alert('비밀번호가 올바르지 않습니다.\n비밀번호를 확인해주세요.'); return false; }
    		}
    	};
    	
    	xmlhttp.open("POST","${pageContext.request.contextPath}/checkPassword",true);
    	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    	xmlhttp.send("member_pw="+inputPassword);
    }
</script>