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

var checkAllValue;
var checkNickValue = true;
var checkPhoneValue = true;

//닉네임 체크(onkeyup, onblur)
function checkUpdateNick(){
	
	var inputNick = document.getElementById('update-input-nick');
	
	if(inputNick.value.length < 3)
	{
		document.getElementById('updateMember-nick-chk').innerHTML = '닉네임의 길이(3자 이상)를 확인해주세요.';
		document.getElementById('updateMember-nick-chk').style.color = "red";
		checkNickValue = false;
	}
	else
	{
		document.getElementById('updateMember-nick-chk').innerHTML = '사용가능한 닉네임입니다.';
		document.getElementById('updateMember-nick-chk').style.color = "#00a000";
		checkNickValue = true;	
	}	
}

//핸드폰 체크(onkeyup, onblur)
function checkUpdatePhone(){
	
	var inputPhone = document.getElementById('update-input-phone');
	var exp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
	
	if(!exp.test(inputPhone.value))
	{
		document.getElementById('updateMember-phone-chk').innerHTML = '유효한 핸드폰번호를 입력해주세요.';
		document.getElementById('updateMember-phone-chk').style.color = "red";
		checkPhoneValue = false;
	}
	else
	{
		document.getElementById('updateMember-phone-chk').innerHTML = '유효한 형식의 연락처입니다.';
		document.getElementById('updateMember-phone-chk').style.color = "#00a000";
		checkPhoneValue = true;
	}
}

//submit 수행
function submitUpdateCustomerInfo() {
	
	var member_code = '${sessionLoginedData.member_code}';
	if(member_code == 'SELLER' || member_code =='CUSTOMER') { checkPhoneValue = true; }
	checkAllValue = checkNickValue && checkPhoneValue;
	
	if(checkAllValue == false)
	{
		alert('유효한 값을 입력해주세요.');
		return false;
	}
	else
	{
		document.getElementById('update-customerInfo-form').action="${pageContext.request.contextPath}/actionUpdateMemberMyInfo";
		document.getElementById('update-customerInfo-form').submit();		
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

        <div id="joinmember-input-box_update">
          <div class="row">
            <h3>회원정보 수정</h3><br />
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
              <!-- Start form -->
              <div id='joinmember-form-wrap_update'>
                <form id="update-customerInfo-form" method="post" autocomplete="off">
                	<div class="form-group">
						<label for="input-id">아이디</label>
						<input type="text" class="form-control" name="member_id" id="input-id" value="${memberInfo.member_id}" readonly>
					</div>
					<div class="form-group">
						<label for="input-nick">닉네임</label>
						<input type="text" class="form-control" name="member_nick" id="update-input-nick" value="${memberInfo.member_nick}"  onkeyup="checkUpdateNick()" onblur="checkUpdateNick()">
						<div class="view-sys-msg"><span id="updateMember-nick-chk"></span></div>
					</div>
					<div class="form-group">
						<label for="input-email">이메일</label>
						<input type="email" class="form-control" name="member_email" id="input-email"  value="${memberInfo.member_email}" readonly>
					</div>
					<!-- 판매자/관리자일때 핸드폰 번호 변경 가능하게.. -->
					<c:if test='${sessionLoginedData.member_code =="ADMIN" || sessionLoginedData.member_code =="SELLER"}'>
						<div class="form-group">
							<label for="input-phone">연락처</label> <input type="text"
									class="form-control" name="member_phone" id="update-input-phone"
									value="${memberInfo.member_phone}" onkeyup="checkUpdatePhone()" onblur="checkUpdatePhone()">
							<div class="view-sys-msg"><span id="updateMember-phone-chk"></span></div>
						</div>
					</c:if>
                  	<div class="form-check">
                  		<button type="button" class="btn btn-primary" onclick="location.href='#'">취소</button>
                    	<button type="button" class="btn btn-primary" onclick="submitUpdateCustomerInfo()">수정</button>
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