<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .list-group-item>img{
      width: 20px;
      height: 20px;
      margin-right: 10px;
    }

    .list-group.listHome>.list-group-item{
      background-color: RGBA(59,92,160,0.4);
      color: white;
    }
    .list-group.listHome>.list-group-item:hover{
      background-color: RGBA(59,92,160,0.7);
    }
  </style>
<div class="list-group listHome">
		<c:if test='${sessionLoginedData == null}'>
          <a href="${pageContext.request.contextPath}/loginForm" class="list-group-item"> <img src="${pageContext.request.contextPath}/resources/img/sidebar/32399bentobox_98893.svg" alt=""> 로그인</a>
        </c:if>
        <c:if test='${sessionLoginedData != null}'>
          <a href="${pageContext.request.contextPath}/actionLogout" class="list-group-item"><img src="${pageContext.request.contextPath}/resources/img/sidebar/32399bentobox_98893.svg" alt=""> 로그아웃 </a>
          <a href="${pageContext.request.contextPath}/admin/memberList" class="list-group-item"> <img src="${pageContext.request.contextPath}/resources/img/sidebar/Coffee_to_Go_icon-icons.com_68763.svg" alt=""> 회원리스트</a>
          <a href="${pageContext.request.contextPath}/admin/convenienceList" class="list-group-item"> <img src="${pageContext.request.contextPath}/resources/img/sidebar/Porridge_icon-icons.com_68703.svg" alt=""> 편의점리스트</a>
          <a href="${pageContext.request.contextPath}/admin/itemList" class="list-group-item"> <img src="${pageContext.request.contextPath}/resources/img/sidebar/Choc_bar_44350.png" alt=""> 관리자 물품 리스트</a>
          <a href="${pageContext.request.contextPath}/admin/sellProductList" class="list-group-item"> <img src="${pageContext.request.contextPath}/resources/img/sidebar/donut_icon-icons.com_54401.svg" alt=""> 떨이물품 리스트</a>
          <a href="${pageContext.request.contextPath}/admin/reportList" class="list-group-item"><img src="${pageContext.request.contextPath}/resources/img/sidebar/if-food-c207-2427857_85711.svg" alt=""> 신고글 리스트 </a>	
        </c:if>
</div>