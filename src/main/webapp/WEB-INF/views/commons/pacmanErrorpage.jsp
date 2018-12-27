<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>페이지가 존재하지 않습니다.</title>
    
    <style type="text/css">
      @font-face {
	    font-family: 'BDCartoonShoutRegular';
        src: url('${pageContext.request.contextPath}/resources/pacman-master/BD_Cartoon_Shout-webfont.ttf') format('truetype');
	    font-weight: normal;
	    font-style: normal;
      }
      #pacman {
        height:450px;
        width:342px;
        margin:20px auto;
      }
      #shim { 
        font-family: BDCartoonShoutRegular; 
        position:absolute;
        visibility:hidden
      }
      h1 { font-family: BDCartoonShoutRegular; text-align:center; }
      body { width:342px; margin:0px auto; font-family:sans-serif; }
      a { text-decoration:none; }
    </style>

</head>

<body>
  
  <div id="shim">shim for font face</div>

  <h1>This page is not exist.</h1>

  <a href="${pageContext.request.contextPath}/mainPage">메인페이지로</a>

  <div id="pacman"></div>
  <script src="${pageContext.request.contextPath}/resources/pacman-master/pacman.js"></script>
  <script src="${pageContext.request.contextPath}/resources/pacman-master/modernizr-1.5.min.js"></script>

  <script>

    var el = document.getElementById("pacman");

    if (Modernizr.canvas && Modernizr.localstorage && 
        Modernizr.audio && (Modernizr.audio.ogg || Modernizr.audio.mp3)) {
      window.setTimeout(function () { PACMAN.init(el, "./"); }, 0);
    } else { 
      el.innerHTML = "Sorry, needs a decent browser<br /><small>" + 
        "(firefox 3.6+, Chrome 4+, Opera 10+ and Safari 4+)</small>";
    }
  </script>

</body>
</html>