var sumbitFlag;
 
function joinMemberFormCheck()
{
	sumbitFlag = true;
	var inputId = document.getElementById('input-id');
	var inputPw = document.getElementById('input-pw');
	var inputPwChk = document.getElementById('input-pw-chk');
	var inputNick = document.getElementById('input-nick');
	var inputEmail = document.getElementById('input-email');
	var agreeJoinChk = document.getElementById('agree-join');
	
	if(inputId.value.length <5)
	{
		document.getElementById('joinMember-id-chk').innerHTML = '아이디의 길이(5자 이상)를 확인해주세요.';
		inputId.value="";
		sumbitFlag = false;
	}
	
	if(inputPw.value.length <8)
	{
		document.getElementById('joinMember-pw-chk1').innerHTML = '패스워드의 길이(8자 이상)를 확인해주세요.';
		inputPw.value="";
		sumbitFlag = false;
	}
	
	if(inputPw.value != inputPwChk.value)
	{
		document.getElementById('joinMember-pw-chk2').innerHTML = '패스워드가 동일하지 않습니다.';
		inputPwChk.value="";
		sumbitFlag = false;
	}
	
	if(inputNick.value.length <3)
	{
		document.getElementById('joinMember-nick-chk').innerHTML = '닉네임의 길이(3자 이상)를 확인해주세요.';
		inputNick.value="";
		sumbitFlag = false;
	}
	
	if(inputEmail.value =="" || inputEmail.value ==null)
	{
		document.getElementById('joinMember-email-chk').innerHTML = '이메일을 입력해주세요.';
		sumbitFlag = false;
	}
	
	if(!agreeJoinChk.checked)
	{
		alert('약관에 동의해주세요.');
		sumbitFlag = false;
		return false;
	}
	
	joinMemberFormSubmit();
}