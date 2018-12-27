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
<script>
//탈퇴 버튼 onclick시 호출
function submitdeleteCustomerInfo()
{
	//회원 비밀번호 확인 Ajax
	var inputPwVlaue = document.getElementById('input-pw').value;
	var xmlhttp = new XMLHttpRequest();
	
	xmlhttp.onreadystatechange = function(){
		
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
		{
			//true: 회원 비밀번호 일치
			var result = xmlhttp.responseText;
			if(result == "true")
			{
				document.getElementById('deleteMemberForm').action = "${pageContext.request.contextPath}/actionUnsubscribeMember";
				document.getElementById('deleteMemberForm').submit();
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

        <div id="joinmember-input-box_delete">
          <div class="row">
            <h3>회원탈퇴</h3><br />
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
              <!-- Start form -->
              <div id='joinmember-form-wrap_delete'>
                <form id="deleteMemberForm" method="post" autocomplete="off">

                  <div class="form-group">
                    <label for="input-pw">패스워드</label>
                    <input type="password" class="form-control" name="member_pw" id="input-pw" placeholder="패스워드">
                  </div>
                  <div class="form-check">
                   		<button type="button" class="btn btn-primary" onclick="location.href='#'">취소</button>
                    	<button type="button" class="btn btn-primary" onclick="submitdeleteCustomerInfo()">탈퇴</button>
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