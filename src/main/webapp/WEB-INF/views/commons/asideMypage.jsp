<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  .list-group-item>img {
    width: 20px;
    height: 20px;
    margin-right: 10px;
  }

  .list-group.listHome>.list-group-item {
    background-color: RGBA(244,126,81,0.2);;
  }

  .list-group.listHome>.list-group-item:hover {
    background-color: RGBA(244,126,81,0.5);
  }
</style>
<div class="list-group listHome">
  <c:if test='${sessionLoginedData == null}'>
    <a href="${pageContext.request.contextPath}/loginForm" class="list-group-item"> <img src="${pageContext.request.contextPath}/resources/img/sidebar/32399bentobox_98893.svg"
        alt="">로그인</a>
  </c:if>
  <c:if test='${sessionLoginedData != null}'>
    <a href="${pageContext.request.contextPath}/actionLogout" class="list-group-item"> <img src="${pageContext.request.contextPath}/resources/img/sidebar/32399bentobox_98893.svg"
        alt="">로그아웃</a>
    <a href="${pageContext.request.contextPath}/seller/convenienceMyList" class="list-group-item"> <img src="${pageContext.request.contextPath}/resources/img/sidebar/Coffee_to_Go_icon-icons.com_68763.svg"
        alt="">내 편의점 정보</a>
    <a href="${pageContext.request.contextPath}/seller/sellProductMyList" class="list-group-item"> <img src="${pageContext.request.contextPath}/resources/img/sidebar/Porridge_icon-icons.com_68703.svg"
        alt="">내 물품 정보</a>
  </c:if>
</div>