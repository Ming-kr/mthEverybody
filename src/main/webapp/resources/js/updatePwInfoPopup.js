//유효성 체크할 변수
var updatePwValuedCheck = false;
var samePwAsPwchkCheck = false;

//변경할 비밀번호 체크(onkeyup, onblur)
function checkUpdatePw(){
	var inputPw = document.getElementById('input-pw_new_updatePw');
	
	if(inputPw.value.length < 8)
	{
		document.getElementById('updateMember-pw-chk1').innerHTML = '패스워드의 길이(8자 이상)를 확인해주세요.';
		document.getElementById('updateMember-pw-chk1').style.color = "red";
		updatePwValuedCheck = false;
	}
	else if(inputPw.value == document.getElementById('input-pw_originalPw').value)
	{
		document.getElementById('updateMember-pw-chk1').innerHTML = '기존과 동일한 패스워드로 변경할 수 없습니다.';
		document.getElementById('updateMember-pw-chk1').style.color = "red";
		updatePwValuedCheck = false;
	}
	else
	{
		document.getElementById('updateMember-pw-chk1').innerHTML = '사용가능한 패스워드입니다.';
		document.getElementById('updateMember-pw-chk1').style.color = "#00a000";
		updatePwValuedCheck = true;
	}
}

//변경할 비밀번호 확인 체크(onkeyup, onblur)
function checkPwAndPwchk(){
	var inputPw = document.getElementById('input-pw_new_updatePw');
	var inputPwChk = document.getElementById('input-pw_new_updatePw_confirm');
	
	if(inputPw.value != inputPwChk.value)
	{
		document.getElementById('updateMember-pw-chk2').innerHTML = '패스워드가 동일하지 않습니다.';
		document.getElementById('updateMember-pw-chk2').style.color = "red";
		samePwAsPwchkCheck = false;
	}
	else
	{
		document.getElementById('updateMember-pw-chk2').innerHTML = '패스워드 확인을 완료했습니다.';
		document.getElementById('updateMember-pw-chk2').style.color = "#00a000";
		samePwAsPwchkCheck = true;
	}
}