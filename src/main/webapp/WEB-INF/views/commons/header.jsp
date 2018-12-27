<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="wrapper">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/mainPage"><img src="${pageContext.request.contextPath}/resources/img/logo/logo.png" alt="로고이미지"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item active">
          <a class="nav-link" href="${pageContext.request.contextPath}/mainPage">Home
              <span class="sr-only">(current)</span>
            </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/commons/about">About</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/commons/services">Services</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/commons/contact">Contact</a>
        </li>
      </ul>
    </div>
  </div>