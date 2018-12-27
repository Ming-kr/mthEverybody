<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
    <style>
        .popup {
      width: 50%;
    }

    .wrapper.joinmember{
      width: 100%;
    }

    .col-lg-9.joinmember {
      flex: 0 0 100%;
      max-width: 100%;
    }

  @media(max-width: 992px) {
    .popup {
      width: 60%;
    }

    .wrapper.joinmember{
      width: 100%;
    }

    .col-lg-9.joinmember {
      flex: 0 0 100%;
      max-width: 100%;
    }
  }

  @media(max-width: 768px) {
    .popup {
      width: 80%;
    }
    .wrapper.joinmember{
      width: 100%;
    }

    .col-lg-9.joinmember {
      flex: 0 0 100%;
      max-width: 100%;
    }
  }

  @media(max-width: 576px) {
    .popup {
      width: 100%;
    }
    .wrapper.joinmember{
      width: 100%;
    }

    .col-lg-9.joinmember {
      flex: 0 0 100%;
      max-width: 100%;
    }
  }
</style>
<!-- 유효성검사 로직은 updatePwInfoPopup.js에 분리 -->
<script src="${pageContext.request.contextPath}/resources/js/updatePwInfoPopup.js"></script>
<script>
//비밀번호 변경 버튼 onclick시 호출
function submitUpdatePassword()
{
	//변경할 비밀번호 유효성검사 통과해야지 기존 비밀번호 확인 후 actionUpdateMemberPw 수행
	if(updatePwValuedCheck == false || samePwAsPwchkCheck == false) { 
		alert('변경할 비밀번호를 다시 확인해주세요.'); return false; 
	}
	else { 
		//회원 기존 비밀번호 확인 Ajax
		var inputPwVlaue = document.getElementById('input-pw_originalPw').value;
		var xmlhttp = new XMLHttpRequest();
    	
    	xmlhttp.onreadystatechange = function(){
    		
    		if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
    		{
    			//true: 회원 비밀번호 일치
    			var result = xmlhttp.responseText;
    			if(result == "true")
    			{
    				document.getElementById('update-memberPw-form').action = "${pageContext.request.contextPath}/actionUpdateMemberPw";
    				document.getElementById('update-memberPw-form').submit();
    			}
    			else
    			{
    				alert('비밀번호가 올바르지 않습니다.\n비밀번호를 확인해주세요.');
    				return false;
    			}
    		}
    	};
    	xmlhttp.open("POST","${pageContext.request.contextPath}/checkPassword",true);
    	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    	xmlhttp.send("member_pw="+inputPwVlaue);
    }
}
</script>
</head>

<body>

    <!-- wrapper 시작 -->
    <div class="wrapper joinmember">
        <!-- row 시작 -->
        <div class="row">
            <!-- /.col-lg-3 끝 -->
            <!-- col-lg-9 시작 -->
            <div class="col-lg-9 joinmember">
                <!-- 내용 넣는 공간 -->

                <div id="joinmember-input-box_updatePw">
                    <div class="row">
                        <h3>비밀번호 변경</h3><br />
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <!-- Start form -->
                            <div id='joinmember-form-wrap_updatePw'>
                                <form id="update-memberPw-form" method="post" autocomplete="off">
                                    <div class="form-group">
                                        <label for="input-pw">패스워드</label>
                                        <input type="password" class="form-control" id="input-pw_originalPw"
                                            placeholder="패스워드">
                                        <div class="view-sys-msg"><span id="joinMember-pw-chk1"></span></div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input-pw">새로운 패스워드</label>
                                        <input type="password" class="form-control" name="member_pw" id="input-pw_new_updatePw"
                                            placeholder="패스워드" onkeyup="checkUpdatePw()" onblur="checkUpdatePw()">
                                        <div class="view-sys-msg"><span id="updateMember-pw-chk1"></span></div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input-pw">새로운 패스워드 확인</label>
                                        <input type="password" class="form-control" id="input-pw_new_updatePw_confirm" placeholder="패스워드 확인" onkeyup="checkPwAndPwchk()" onblur="checkPwAndPwchk()">
                                        <div class="view-sys-msg"><span id="updateMember-pw-chk2"></span></div>
                                    </div>
                                    <div class="form-check">
                                        <button type="button" class="btn btn-primary" onclick="location.href='#'">취소</button>
                    					<button type="button" class="btn btn-primary" onclick="submitUpdatePassword()">비밀번호 변경</button>
                                    </div>
                                </form>
                            </div>
                            <!-- end form -->
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.col-lg-9 끝 -->
        </div>
        <!-- /.row 끝 -->
    </div>
    <!-- /.wrapper 끝 -->
</body>

</html>